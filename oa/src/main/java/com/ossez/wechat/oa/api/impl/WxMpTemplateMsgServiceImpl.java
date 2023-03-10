package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.WxMpTemplateMsgService;
import com.ossez.wechat.oa.bean.template.WxMpTemplate;
import com.ossez.wechat.oa.bean.template.WxMpTemplateIndustry;
import com.ossez.wechat.oa.bean.template.WxMpTemplateMessage;

import java.util.List;

import static com.ossez.wechat.common.enums.WxMpApiUrl.TemplateMsg.*;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMpTemplateMsgServiceImpl implements WxMpTemplateMsgService {


  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public String sendTemplateMsg(WxMpTemplateMessage templateMessage) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MESSAGE_TEMPLATE_SEND, templateMessage.toJson());
    final JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get("errcode").getAsInt() == 0) {
      return jsonObject.get("msgid").getAsString();
    }
    throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
  }

  @Override
  public boolean setIndustry(WxMpTemplateIndustry wxMpIndustry) throws WxErrorException {
    if (null == wxMpIndustry.getPrimaryIndustry() || null == wxMpIndustry.getPrimaryIndustry().getCode()
      || null == wxMpIndustry.getSecondIndustry() || null == wxMpIndustry.getSecondIndustry().getCode()) {
      throw new IllegalArgumentException("行业Id不能为空，请核实");
    }

    this.weChatOfficialAccountService.post(TEMPLATE_API_SET_INDUSTRY, wxMpIndustry.toJson());
    return true;
  }

  @Override
  public WxMpTemplateIndustry getIndustry() throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(TEMPLATE_GET_INDUSTRY, null);
    return WxMpTemplateIndustry.fromJson(responseContent);
  }

  @Override
  public String addTemplate(String shortTemplateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id_short", shortTemplateId);
    String responseContent = this.weChatOfficialAccountService.post(TEMPLATE_API_ADD_TEMPLATE, jsonObject.toString());
    final JsonObject result = GsonParser.parse(responseContent);
    if (result.get("errcode").getAsInt() == 0) {
      return result.get("template_id").getAsString();
    }

    throw new WxErrorException(WxError.fromJson(responseContent, WxType.MP));
  }

  @Override
  public List<WxMpTemplate> getAllPrivateTemplate() throws WxErrorException {
    return WxMpTemplate.fromJson(this.weChatOfficialAccountService.get(TEMPLATE_GET_ALL_PRIVATE_TEMPLATE, null));
  }

  @Override
  public boolean delPrivateTemplate(String templateId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("template_id", templateId);
    String responseContent = this.weChatOfficialAccountService.post(TEMPLATE_DEL_PRIVATE_TEMPLATE, jsonObject.toString());
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() == 0) {
      return true;
    }

    throw new WxErrorException(error);
  }

}
