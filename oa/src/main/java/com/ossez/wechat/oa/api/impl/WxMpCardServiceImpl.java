package com.ossez.wechat.oa.api.impl;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.oa.bean.card.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.bean.WxCardApiSignature;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.SimpleGetRequestExecutor;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.api.WxMpCardService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.enums.TicketType;
import com.ossez.wechat.common.enums.WxMpApiUrl;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.locks.Lock;

/**
 * Created by Binary Wang on 2016/7/27.
 *
 * @author BinaryWang
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpCardServiceImpl implements WxMpCardService {
  private static final Gson GSON = WxMpGsonBuilder.create();
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  public WeChatOfficialAccountService getWeChatOfficialAccountService() {
    return this.weChatOfficialAccountService;
  }

  @Override
  public String getCardApiTicket() throws WxErrorException {
    return getCardApiTicket(false);
  }

  @Override
  public String getCardApiTicket(boolean forceRefresh) throws WxErrorException {
    final TicketType type = TicketType.WX_CARD;

    if (forceRefresh) {
      this.getWeChatOfficialAccountService().getWxMpConfigStorage().expireTicket(type);
    }

    if (this.getWeChatOfficialAccountService().getWxMpConfigStorage().isTicketExpired(type)) {
      Lock lock = getWeChatOfficialAccountService().getWxMpConfigStorage().getTicketLock(type);
      lock.lock();
      try {
        if (this.getWeChatOfficialAccountService().getWxMpConfigStorage().isTicketExpired(type)) {
          String responseContent = this.weChatOfficialAccountService.execute(SimpleGetRequestExecutor
            .create(this.getWeChatOfficialAccountService().getRequestHttp()), WxMpApiUrl.Card.CARD_GET_TICKET, null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          String cardApiTicket = tmpJsonObject.get("ticket").getAsString();
          int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
          this.getWeChatOfficialAccountService().getWxMpConfigStorage().updateTicket(type, cardApiTicket, expiresInSeconds);
        }
      } finally {
        lock.unlock();
      }
    }
    return this.getWeChatOfficialAccountService().getWxMpConfigStorage().getTicket(type);
  }

  @Override
  public WxCardApiSignature createCardApiSignature(String... optionalSignParam) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String nonceStr = RandomStringUtils.randomAlphanumeric(16);
    String cardApiTicket = getCardApiTicket(false);

    String[] signParams = Arrays.copyOf(optionalSignParam, optionalSignParam.length + 3);
    signParams[optionalSignParam.length] = String.valueOf(timestamp);
    signParams[optionalSignParam.length + 1] = nonceStr;
    signParams[optionalSignParam.length + 2] = cardApiTicket;
    StringBuilder sb = new StringBuilder();
    Arrays.sort(signParams);
    for (String a : signParams) {
      sb.append(a);
    }
    String signature = DigestUtils.sha1Hex(sb.toString());

    WxCardApiSignature cardApiSignature = new WxCardApiSignature();
    cardApiSignature.setTimestamp(timestamp);
    cardApiSignature.setNonceStr(nonceStr);
    cardApiSignature.setSignature(signature);
    return cardApiSignature;
  }

  @Override
  public String decryptCardCode(String encryptCode) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("encrypt_code", encryptCode);
    String responseContent = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_DECRYPT, param.toString());
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    JsonPrimitive jsonPrimitive = tmpJsonObject.getAsJsonPrimitive("code");
    return jsonPrimitive.getAsString();
  }

  @Override
  public WxMpCardResult queryCardCode(String cardId, String code, boolean checkConsume) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.addProperty("code", code);
    param.addProperty("check_consume", checkConsume);
    String responseContent = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_GET, param.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    return WxMpGsonBuilder.create().fromJson(tmpJsonElement,
      new TypeToken<WxMpCardResult>() {
      }.getType());
  }

  @Override
  public String consumeCardCode(String code) throws WxErrorException {
    return consumeCardCode(code, null);
  }

  @Override
  public String consumeCardCode(String code, String cardId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("code", code);

    if (cardId != null && !"".equals(cardId)) {
      param.addProperty("card_id", cardId);
    }

    return this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_CONSUME, param.toString());
  }

  @Override
  public void markCardCode(String code, String cardId, String openId, boolean isMark) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("code", code);
    param.addProperty("card_id", cardId);
    param.addProperty("openid", openId);
    param.addProperty("is_mark", isMark);
    String responseContent = this.getWeChatOfficialAccountService().post(WxMpApiUrl.Card.CARD_CODE_MARK, param.toString());
    JsonElement tmpJsonElement = new JsonParser().parse(responseContent);
    WxMpCardResult cardResult = WxMpGsonBuilder.create().fromJson(tmpJsonElement,
      new TypeToken<WxMpCardResult>() {
      }.getType());
    if (!"0".equals(cardResult.getErrorCode())) {
      log.warn("朋友的券mark失败：{}", cardResult.getErrorMsg());
    }
  }

  @Override
  public String getCardDetail(String cardId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    String responseContent = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_GET, param.toString());

    // 判断返回值
    JsonObject json = GsonParser.parse(responseContent);
    String errcode = json.get("errcode").getAsString();
    if (!"0".equals(errcode)) {
      String errmsg = json.get("errmsg").getAsString();
      throw new WxErrorException(WxError.builder()
        .errorCode(Integer.valueOf(errcode)).errorMsg(errmsg)
        .build());
    }

    return responseContent;
  }

  @Override
  public String addTestWhiteList(String openid) throws WxErrorException {
    JsonArray array = new JsonArray();
    array.add(openid);
    JsonObject jsonObject = new JsonObject();
    jsonObject.add("openid", array);
    return this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_TEST_WHITELIST, GSON.toJson(jsonObject));
  }

  @Override
  public WxMpCardCreateResult createCard(WxMpCardCreateRequest cardCreateMessage) throws WxErrorException {
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CREATE, GSON.toJson(cardCreateMessage));
    return WxMpCardCreateResult.fromJson(response);
  }

  @Override
  public WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr) throws WxErrorException {
    return this.createQrcodeCard(cardId, outerStr, 0);
  }

  @Override
  public WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn) throws WxErrorException {
    return this.createQrcodeCard(cardId, outerStr, expiresIn, null, null, false);
  }

  @Override
  public WxMpCardQrcodeCreateResult createQrcodeCard(String cardId, String outerStr, int expiresIn, String openid,
                                                     String code, boolean isUniqueCode) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("action_name", "QR_CARD");
    if (expiresIn > 0) {
      jsonObject.addProperty("expire_seconds", expiresIn);
    }

    JsonObject actionInfoJson = new JsonObject();
    JsonObject cardJson = new JsonObject();
    if (openid != null) {
      cardJson.addProperty("openid", openid);
    }

    if (code != null) {
      cardJson.addProperty("code", code);
    }

    cardJson.addProperty("is_unique_code", isUniqueCode);
    cardJson.addProperty("card_id", cardId);
    cardJson.addProperty("outer_str", outerStr);
    actionInfoJson.add("card", cardJson);
    jsonObject.add("action_info", actionInfoJson);

    return WxMpCardQrcodeCreateResult.fromJson(this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_QRCODE_CREATE, GSON.toJson(jsonObject)));
  }

  @Override
  public WxMpCardLandingPageCreateResult createLandingPage(WxMpCardLandingPageCreateRequest request) throws WxErrorException {
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_LANDING_PAGE_CREATE, GSON.toJson(request));
    return WxMpCardLandingPageCreateResult.fromJson(response);
  }

  @Override
  public String unavailableCardCode(String cardId, String code, String reason) throws WxErrorException {
    if (StringUtils.isAnyBlank(cardId, code, reason)) {
      throw new WxErrorException(WxError.builder().errorCode(41012).errorMsg("参数不完整").build());
    }
    JsonObject jsonRequest = new JsonObject();
    jsonRequest.addProperty("card_id", cardId);
    jsonRequest.addProperty("code", code);
    jsonRequest.addProperty("reason", reason);
    return this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_UNAVAILABLE, GSON.toJson(jsonRequest));
  }

  @Override
  public WxMpCardDeleteResult deleteCard(String cardId) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_DELETE, param.toString());
    return WxMpCardDeleteResult.fromJson(response);
  }


  @Override
  public WxMpCardCodeDepositResult cardCodeDeposit(String cardId, List<String> codeList) throws WxErrorException {
    checkCardId(cardId);
    if (codeList.size() == 0 || codeList.size() > 100) {
      throw new WxErrorException(WxError.builder().errorCode(40109).errorMsg("code数量为0或者code数量超过100个").build());
    }
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.add("code",
      WxGsonBuilder.create().toJsonTree(codeList, new TypeToken<List<String>>() {
      }.getType()).getAsJsonArray());
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_DEPOSIT, param.toString());
    return WxMpCardCodeDepositResult.fromJson(response);
  }


  @Override
  public WxMpCardCodeDepositCountResult cardCodeDepositCount(String cardId) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_DEPOSIT_COUNT, param.toString());
    return WxMpCardCodeDepositCountResult.fromJson(response);
  }


  @Override
  public WxMpCardCodeCheckcodeResult cardCodeCheckcode(String cardId, List<String> codeList) throws WxErrorException {
    checkCardId(cardId);
    if (codeList.size() == 0 || codeList.size() > 100) {
      throw new WxErrorException(WxError.builder().errorCode(40109).errorMsg("code数量为0或者code数量超过100个").build());
    }
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.add("code",
      WxGsonBuilder.create().toJsonTree(codeList, new TypeToken<List<String>>() {
      }.getType()).getAsJsonArray());
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_CHECKCODE, param.toString());
    return WxMpCardCodeCheckcodeResult.fromJson(response);
  }


  @Override
  public WxMpCardMpnewsGethtmlResult cardMpnewsGethtml(String cardId) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_MPNEWS_GETHTML, param.toString());
    return WxMpCardMpnewsGethtmlResult.fromJson(response);
  }


  @Override
  public void cardModifyStock(String cardId, Integer changeValue) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    if (changeValue > 0) {
      param.addProperty("increase_stock_value", changeValue);
    } else {
      param.addProperty("reduce_stock_value", Math.abs(changeValue));
    }
    this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_MODIFY_STOCK, param.toString());
  }


  @Override
  public void cardCodeUpdate(String cardId, String oldCode, String newCode) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.addProperty("code", oldCode);
    param.addProperty("new_code", newCode);
    this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_CODE_UPDATE, param.toString());
  }


  @Override
  public void cardPaycellSet(String cardId, Boolean isOpen) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.addProperty("is_open", isOpen);
    this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_PAYCELL_SET, param.toString());
  }


  @Override
  public void cardSelfConsumeCellSet(String cardId, Boolean isOpen,
                                     Boolean needVerifyCod, Boolean needRemarkAmount) throws WxErrorException {
    checkCardId(cardId);
    JsonObject param = new JsonObject();
    param.addProperty("card_id", cardId);
    param.addProperty("is_open", isOpen);
    param.addProperty("need_verify_cod", needVerifyCod);
    param.addProperty("need_remark_amount", needRemarkAmount);
    this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_SELF_CONSUME_CELL_SET, param.toString());
  }


  @Override
  public WxUserCardListResult getUserCardList(String openId, String cardId) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("openid", openId);
    param.addProperty("card_id", cardId);
    String response = this.weChatOfficialAccountService.post(WxMpApiUrl.Card.CARD_USER_CARD_LIST, param.toString());
    return WxUserCardListResult.fromJson(response);
  }


  private void checkCardId(String cardId) throws WxErrorException {
    if (StringUtils.isEmpty(cardId)) {
      throw new WxErrorException(WxError.builder().errorCode(41012).errorMsg("cardId不能为空").build());
    }
  }
}
