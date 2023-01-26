package com.ossez.wechat.wecom.api.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.WxCpMessageService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.message.*;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;

/**
 * 消息推送接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-30
 */
@RequiredArgsConstructor
public class WxCpMessageServiceImpl implements WxCpMessageService {
  private final WxCpService cpService;

  @Override
  public WxCpMessageSendResult send(WxCpMessage message) throws WxErrorException {
    Integer agentId = message.getAgentId();
    if (null == agentId) {
      message.setAgentId(this.cpService.getWxCpConfigStorage().getAgentId());
    }

    return WxCpMessageSendResult.fromJson(this.cpService.post(this.cpService.getWxCpConfigStorage()
      .getApiUrl(WxCpApiPathConsts.Message.MESSAGE_SEND), message.toJson()));
  }

  @Override
  public WxCpMessageSendStatistics getStatistics(int timeType) throws WxErrorException {
    return WxCpMessageSendStatistics.fromJson(this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Message.GET_STATISTICS),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("time_type", timeType))));
  }

  @Override
  public WxCpLinkedCorpMessageSendResult sendLinkedCorpMessage(WxCpLinkedCorpMessage message) throws WxErrorException {
    Integer agentId = message.getAgentId();
    if (null == agentId) {
      message.setAgentId(this.cpService.getWxCpConfigStorage().getAgentId());
    }

    return WxCpLinkedCorpMessageSendResult.fromJson(this.cpService.post(this.cpService.getWxCpConfigStorage()
      .getApiUrl(WxCpApiPathConsts.Message.LINKEDCORP_MESSAGE_SEND), message.toJson()));
  }

  @Override
  public WxCpSchoolContactMessageSendResult sendSchoolContactMessage(WxCpSchoolContactMessage message) throws WxErrorException {
    if (null == message.getAgentId()) {
      message.setAgentId(this.cpService.getWxCpConfigStorage().getAgentId());
    }

    return WxCpSchoolContactMessageSendResult.fromJson(this.cpService.post(this.cpService.getWxCpConfigStorage()
      .getApiUrl(WxCpApiPathConsts.Message.EXTERNAL_CONTACT_MESSAGE_SEND), message.toJson()));
  }

  @Override
  public void recall(String msgId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("msgid", msgId);
    String apiUrl = this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Message.MESSAGE_RECALL);
    this.cpService.post(apiUrl, jsonObject.toString());
  }

}
