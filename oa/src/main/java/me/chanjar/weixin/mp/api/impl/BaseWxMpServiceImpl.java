package me.chanjar.weixin.mp.api.impl;

import com.google.common.collect.ImmutableMap;
import com.google.common.collect.Maps;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.api.WxConsts;
import me.chanjar.weixin.common.bean.ToJson;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxJsapiSignature;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.enums.TicketType;
import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.error.WxRuntimeException;
import me.chanjar.weixin.common.service.WxImgProcService;
import me.chanjar.weixin.common.service.WxOAuth2Service;
import me.chanjar.weixin.common.service.WxOcrService;
import me.chanjar.weixin.common.session.StandardSessionManager;
import me.chanjar.weixin.common.session.WxSessionManager;
import me.chanjar.weixin.common.util.DataUtils;
import me.chanjar.weixin.common.util.RandomUtils;
import me.chanjar.weixin.common.util.crypto.SHA1;
import me.chanjar.weixin.common.util.http.*;
import me.chanjar.weixin.common.util.json.GsonParser;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;
import me.chanjar.weixin.mp.api.*;
import me.chanjar.weixin.mp.bean.WxMpSemanticQuery;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import me.chanjar.weixin.mp.bean.result.WxMpSemanticQueryResult;
import me.chanjar.weixin.mp.bean.result.WxMpShortKeyResult;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.enums.WxMpApiUrl;
import me.chanjar.weixin.mp.util.WxMpConfigStorageHolder;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.locks.Lock;

import static me.chanjar.weixin.mp.enums.WxMpApiUrl.Other.*;

/**
 * 基础实现类.
 *
 * @author someone
 */
@Slf4j
public abstract class BaseWxMpServiceImpl<H, P> implements WxMpService, RequestHttp<H, P> {
  protected WxSessionManager sessionManager = new StandardSessionManager();
  @Getter
  @Setter
  private WxMpKefuService kefuService = new WxMpKefuServiceImpl(this);
  @Getter
  @Setter
  private WxMpMaterialService materialService = new WxMpMaterialServiceImpl(this);
  @Getter
  @Setter
  private WxMpMenuService menuService = new WxMpMenuServiceImpl(this);
  @Getter
  @Setter
  private WxMpUserService userService = new WxMpUserServiceImpl(this);
  @Getter
  @Setter
  private WxMpUserTagService userTagService = new WxMpUserTagServiceImpl(this);
  @Getter
  @Setter
  private WxMpQrcodeService qrcodeService = new WxMpQrcodeServiceImpl(this);
  @Getter
  @Setter
  private WxMpCardService cardService = new WxMpCardServiceImpl(this);
  @Getter
  @Setter
  private WxMpStoreService storeService = new WxMpStoreServiceImpl(this);
  @Getter
  @Setter
  private WxMpDataCubeService dataCubeService = new WxMpDataCubeServiceImpl(this);
  @Getter
  @Setter
  private WxMpUserBlacklistService blackListService = new WxMpUserBlacklistServiceImpl(this);
  @Getter
  @Setter
  private WxMpTemplateMsgService templateMsgService = new WxMpTemplateMsgServiceImpl(this);
  @Getter
  @Setter
  private WxMpSubscribeMsgService subscribeMsgService = new WxMpSubscribeMsgServiceImpl(this);
  @Getter
  @Setter
  private WxMpDeviceService deviceService = new WxMpDeviceServiceImpl(this);
  @Getter
  @Setter
  private WxMpShakeService shakeService = new WxMpShakeServiceImpl(this);
  @Getter
  @Setter
  private WxMpMemberCardService memberCardService = new WxMpMemberCardServiceImpl(this);
  @Getter
  @Setter
  private WxMpMassMessageService massMessageService = new WxMpMassMessageServiceImpl(this);
  @Getter
  @Setter
  private WxMpAiOpenService aiOpenService = new WxMpAiOpenServiceImpl(this);
  @Getter
  @Setter
  private WxMpWifiService wifiService = new WxMpWifiServiceImpl(this);
  @Getter
  @Setter
  private WxMpMarketingService marketingService = new WxMpMarketingServiceImpl(this);
  @Getter
  @Setter
  private WxMpCommentService commentService = new WxMpCommentServiceImpl(this);
  @Getter
  @Setter
  private WxOcrService ocrService = new WxMpOcrServiceImpl(this);
  @Getter
  @Setter
  private WxImgProcService imgProcService = new WxMpImgProcServiceImpl(this);
  @Getter
  @Setter
  private WxMpMerchantInvoiceService merchantInvoiceService = new WxMpMerchantInvoiceServiceImpl(this, this.cardService);

  @Getter
  @Setter
  private WxMpGuideService guideService = new WxMpGuideServiceImpl(this);
  @Getter
  @Setter
  private WxMpGuideBuyerService guideBuyerService = new WxMpGuideBuyerServiceImpl(this);
  @Getter
  @Setter
  private WxMpGuideTagService guideTagService = new WxMpGuideTagServiceImpl(this);
  @Getter
  @Setter
  private WxMpGuideMassedJobService guideMassedJobService = new WxMpGuideMassedJobServiceImpl(this);
  @Getter
  @Setter
  private WxMpGuideMaterialService guideMaterialService = new WxMpGuideMaterialServiceImpl(this);

  @Getter
  @Setter
  private WxOAuth2Service oAuth2Service = new WxMpOAuth2ServiceImpl(this);

  @Getter
  @Setter
  private WxMpReimburseInvoiceService reimburseInvoiceService = new WxMpReimburseInvoiceServiceImpl(this);

  @Getter
  @Setter
  private WxMpDraftService draftService = new WxMpDraftServiceImpl(this);

  @Getter
  @Setter
  private WxMpFreePublishService freePublishService = new WxMpFreePublishServiceImpl(this);

  private Map<String, WxMpConfigStorage> configStorageMap;

  private int retrySleepMillis = 1000;
  private int maxRetryTimes = 5;

  @Override
  public String genShorten(String longData, Integer expireSeconds) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("long_data", longData);
    param.addProperty("expire_seconds", expireSeconds);
    String responseContent = this.post(GEN_SHORTEN_URL, param.toString());
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    return tmpJsonObject.get("short_key").getAsString();
  }

  @Override
  public WxMpShortKeyResult fetchShorten(String shortKey) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("short_key", shortKey);
    String responseContent = this.post(FETCH_SHORTEN_URL, param.toString());
    return WxMpShortKeyResult.fromJson(responseContent);
  }

  @Override
  public boolean checkSignature(String timestamp, String nonce, String signature) {
    try {
      return SHA1.gen(this.getWxMpConfigStorage().getToken(), timestamp, nonce)
        .equals(signature);
    } catch (Exception e) {
      log.error("Checking signature failed, and the reason is :" + e.getMessage());
      return false;
    }
  }

  @Override
  public String getTicket(TicketType type) throws WxErrorException {
    return this.getTicket(type, false);
  }

  @Override
  public String getTicket(TicketType type, boolean forceRefresh) throws WxErrorException {

    if (forceRefresh) {
      this.getWxMpConfigStorage().expireTicket(type);
    }

    if (this.getWxMpConfigStorage().isTicketExpired(type)) {
      Lock lock = this.getWxMpConfigStorage().getTicketLock(type);
      lock.lock();
      try {
        if (this.getWxMpConfigStorage().isTicketExpired(type)) {
          String responseContent = execute(SimpleGetRequestExecutor.create(this),
            GET_TICKET_URL.getUrl(this.getWxMpConfigStorage()) + type.getCode(), null);
          JsonObject tmpJsonObject = GsonParser.parse(responseContent);
          String jsapiTicket = tmpJsonObject.get("ticket").getAsString();
          int expiresInSeconds = tmpJsonObject.get("expires_in").getAsInt();
          this.getWxMpConfigStorage().updateTicket(type, jsapiTicket, expiresInSeconds);
        }
      } finally {
        lock.unlock();
      }
    }

    return this.getWxMpConfigStorage().getTicket(type);
  }

  @Override
  public String getJsapiTicket() throws WxErrorException {
    return this.getJsapiTicket(false);
  }

  @Override
  public String getJsapiTicket(boolean forceRefresh) throws WxErrorException {
    return this.getTicket(TicketType.JSAPI, forceRefresh);
  }

  @Override
  public WxJsapiSignature createJsapiSignature(String url) throws WxErrorException {
    long timestamp = System.currentTimeMillis() / 1000;
    String randomStr = RandomUtils.getRandomStr();
    String jsapiTicket = getJsapiTicket(false);
    String signature = SHA1.genWithAmple("jsapi_ticket=" + jsapiTicket,
      "noncestr=" + randomStr, "timestamp=" + timestamp, "url=" + url);
    WxJsapiSignature jsapiSignature = new WxJsapiSignature();
    jsapiSignature.setAppId(this.getWxMpConfigStorage().getAppId());
    jsapiSignature.setTimestamp(timestamp);
    jsapiSignature.setNonceStr(randomStr);
    jsapiSignature.setUrl(url);
    jsapiSignature.setSignature(signature);
    return jsapiSignature;
  }

  @Override
  public String getAccessToken() throws WxErrorException {
    return getAccessToken(false);
  }

  @Override
  public String shortUrl(String longUrl) throws WxErrorException {
    if (longUrl.contains("&access_token=")) {
      throw new WxErrorException("要转换的网址中存在非法字符｛&access_token=｝，" +
        "会导致微信接口报错，属于微信bug，请调整地址，否则不建议使用此方法！");
    }

    JsonObject o = new JsonObject();
    o.addProperty("action", "long2short");
    o.addProperty("long_url", longUrl);
    String responseContent = this.post(SHORTURL_API_URL, o.toString());
    return GsonParser.parse(responseContent).get("short_url").getAsString();
  }

  @Override
  public WxMpSemanticQueryResult semanticQuery(WxMpSemanticQuery semanticQuery) throws WxErrorException {
    String responseContent = this.post(SEMANTIC_SEMPROXY_SEARCH_URL, semanticQuery.toJson());
    return WxMpSemanticQueryResult.fromJson(responseContent);
  }

  @Override
  public String buildQrConnectUrl(String redirectUri, String scope, String state) {
    return String.format(QRCONNECT_URL.getUrl(this.getWxMpConfigStorage()), this.getWxMpConfigStorage().getAppId(),
      URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
  }

  @Override
  public String[] getCallbackIP() throws WxErrorException {
    String responseContent = this.get(GET_CALLBACK_IP_URL, null);
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    JsonArray ipList = tmpJsonObject.get("ip_list").getAsJsonArray();
    String[] ipArray = new String[ipList.size()];
    for (int i = 0; i < ipList.size(); i++) {
      ipArray[i] = ipList.get(i).getAsString();
    }
    return ipArray;
  }

  @Override
  public WxNetCheckResult netCheck(String action, String operator) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("action", action);
    o.addProperty("check_operator", operator);
    String responseContent = this.post(NETCHECK_URL, o.toString());
    return WxNetCheckResult.fromJson(responseContent);
  }

  @Override
  public WxMpCurrentAutoReplyInfo getCurrentAutoReplyInfo() throws WxErrorException {
    return WxMpCurrentAutoReplyInfo.fromJson(this.get(GET_CURRENT_AUTOREPLY_INFO_URL, null));
  }

  @Override
  public void clearQuota(String appid) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("appid", appid);
    this.post(CLEAR_QUOTA_URL, o.toString());
  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String get(WxMpApiUrl url, String queryParam) throws WxErrorException {
    return this.get(url.getUrl(this.getWxMpConfigStorage()), queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public String post(WxMpApiUrl url, String postData) throws WxErrorException {
    return this.post(url.getUrl(this.getWxMpConfigStorage()), postData);
  }

  @Override
  public String post(WxMpApiUrl url, Object obj) throws WxErrorException {
    return this.execute(SimplePostRequestExecutor.create(this), url, WxGsonBuilder.create().toJson(obj));
  }

  @Override
  public String post(WxMpApiUrl url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url.getUrl(this.getWxMpConfigStorage()), jsonObject.toString());
  }

  @Override
  public String post(String url, ToJson obj) throws WxErrorException {
    return this.post(url, obj.toJson());
  }

  @Override
  public String post(String url, JsonObject jsonObject) throws WxErrorException {
    return this.post(url, jsonObject.toString());
  }

  @Override
  public String post(String url, Object obj) throws WxErrorException {
    return this.execute(SimplePostRequestExecutor.create(this), url, WxGsonBuilder.create().toJson(obj));
  }

  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, WxMpApiUrl url, E data) throws WxErrorException {
    return this.execute(executor, url.getUrl(this.getWxMpConfigStorage()), data);
  }

  /**
   * 向微信端发送请求，在这里执行的策略是当发生access_token过期时才去刷新，然后重新执行请求，而不是全局定时请求.
   */
  @Override
  public <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    int retryTimes = 0;
    do {
      try {
        return this.executeInternal(executor, uri, data, false);
      } catch (WxErrorException e) {
        WxError error = e.getError();
        // -1 系统繁忙, 1000ms后重试
        if (error.getErrorCode() == -1) {
          // 判断是否已经超了最大重试次数
          if (retryTimes + 1 > this.maxRetryTimes) {
            log.warn("重试达到最大次数【{}】", maxRetryTimes);
            //最后一次重试失败后，直接抛出异常，不再等待
            throw new WxRuntimeException("微信服务端异常，超出重试次数");
          }

          int sleepMillis = this.retrySleepMillis * (1 << retryTimes);
          try {
            log.warn("微信系统繁忙，{} ms 后重试(第{}次)", sleepMillis, retryTimes + 1);
            Thread.sleep(sleepMillis);
          } catch (InterruptedException e1) {
            throw new WxRuntimeException(e1);
          }
        } else {
          throw e;
        }
      }
    } while (retryTimes++ < this.maxRetryTimes);

    log.warn("重试达到最大次数【{}】", this.maxRetryTimes);
    throw new WxRuntimeException("微信服务端异常，超出重试次数");
  }

  protected <T, E> T executeInternal(RequestExecutor<T, E> executor, String uri, E data, boolean doNotAutoRefresh) throws WxErrorException {
    E dataForLog = DataUtils.handleDataWithSecret(data);

    if (uri.contains("access_token=")) {
      throw new IllegalArgumentException("uri参数中不允许有access_token: " + uri);
    }

    String accessToken = getAccessToken(false);
    String uriWithAccessToken = uri + (uri.contains("?") ? "&" : "?") + "access_token=" + accessToken;

    try {
      T result = executor.execute(uriWithAccessToken, data, WxType.MP);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uriWithAccessToken, dataForLog, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (WxConsts.ACCESS_TOKEN_ERROR_CODES.contains(error.getErrorCode())) {
        // 强制设置wxMpConfigStorage它的access token过期了，这样在下一次请求里就会刷新access token
        Lock lock = this.getWxMpConfigStorage().getAccessTokenLock();
        lock.lock();
        try {
          if (StringUtils.equals(this.getWxMpConfigStorage().getAccessToken(), accessToken)) {
            this.getWxMpConfigStorage().expireAccessToken();
          }
        } catch (Exception ex) {
          this.getWxMpConfigStorage().expireAccessToken();
        } finally {
          lock.unlock();
        }
        if (this.getWxMpConfigStorage().autoRefreshToken() && !doNotAutoRefresh) {
          log.warn("即将重新获取新的access_token，错误代码：{}，错误信息：{}", error.getErrorCode(), error.getErrorMsg());
          //下一次不再自动重试
          //当小程序误调用第三方平台专属接口时,第三方无法使用小程序的access token,如果可以继续自动获取token会导致无限循环重试,直到栈溢出
          return this.executeInternal(executor, uri, data, true);
        }
      }

      if (error.getErrorCode() != 0) {
        log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uriWithAccessToken, dataForLog, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uriWithAccessToken, dataForLog, e.getMessage());
      throw new WxErrorException(e);
    }
  }

  @Override
  public WxMpConfigStorage getWxMpConfigStorage() {
    if (this.configStorageMap.size() == 1) {
      // 只有一个公众号，直接返回其配置即可
      return this.configStorageMap.values().iterator().next();
    }

    return this.configStorageMap.get(WxMpConfigStorageHolder.get());
  }

  protected String extractAccessToken(String resultContent) throws WxErrorException {
    WxMpConfigStorage config = this.getWxMpConfigStorage();
    WxError error = WxError.fromJson(resultContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }
    WxAccessToken accessToken = WxAccessToken.fromJson(resultContent);
    config.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return config.getAccessToken();
  }

  @Override
  public void setWxMpConfigStorage(WxMpConfigStorage wxConfigProvider) {
    final String defaultMpId = wxConfigProvider.getAppId();
    if (defaultMpId == null) {
      throw new WxRuntimeException("appid不能设置为null");
    }

    this.setMultiConfigStorages(ImmutableMap.of(defaultMpId, wxConfigProvider), defaultMpId);
  }

  @Override
  public void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages) {
    this.setMultiConfigStorages(configStorages, configStorages.keySet().iterator().next());
  }

  @Override
  public void setMultiConfigStorages(Map<String, WxMpConfigStorage> configStorages, String defaultMpId) {
    this.configStorageMap = Maps.newHashMap(configStorages);
    WxMpConfigStorageHolder.set(defaultMpId);
    this.initHttp();
  }

  @Override
  public void addConfigStorage(String mpId, WxMpConfigStorage configStorages) {
    synchronized (this) {
      if (this.configStorageMap == null) {
        this.setWxMpConfigStorage(configStorages);
      } else {
        WxMpConfigStorageHolder.set(mpId);
        this.configStorageMap.put(mpId, configStorages);
      }
    }
  }

  @Override
  public void removeConfigStorage(String mpId) {
    synchronized (this) {
      if (this.configStorageMap.size() == 1) {
        this.configStorageMap.remove(mpId);
        log.warn("已删除最后一个公众号配置：{}，须立即使用setWxMpConfigStorage或setMultiConfigStorages添加配置", mpId);
        return;
      }
      if (WxMpConfigStorageHolder.get().equals(mpId)) {
        this.configStorageMap.remove(mpId);
        final String defaultMpId = this.configStorageMap.keySet().iterator().next();
        WxMpConfigStorageHolder.set(defaultMpId);
        log.warn("已删除默认公众号配置，公众号【{}】被设为默认配置", defaultMpId);
        return;
      }
      this.configStorageMap.remove(mpId);
    }
  }

  @Override
  public WxMpService switchoverTo(String mpId) {
    if (this.configStorageMap.containsKey(mpId)) {
      WxMpConfigStorageHolder.set(mpId);
      return this;
    }

    throw new WxRuntimeException(String.format("无法找到对应【%s】的公众号配置信息，请核实！", mpId));
  }

  @Override
  public boolean switchover(String mpId) {
    if (this.configStorageMap.containsKey(mpId)) {
      WxMpConfigStorageHolder.set(mpId);
      return true;
    }

    log.error("无法找到对应【{}】的公众号配置信息，请核实！", mpId);
    return false;
  }

  @Override
  public void setRetrySleepMillis(int retrySleepMillis) {
    this.retrySleepMillis = retrySleepMillis;
  }

  @Override
  public void setMaxRetryTimes(int maxRetryTimes) {
    this.maxRetryTimes = maxRetryTimes;
  }

  @Override
  public RequestHttp getRequestHttp() {
    return this;
  }

  @Override
  public WxMpGuideService getGuideService() {
    return this.guideService;
  }

  @Override
  public void setGuideService(WxMpGuideService guideService) {
    this.guideService = guideService;
  }
}
