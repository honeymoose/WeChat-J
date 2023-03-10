package com.ossez.wechat.wecom.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.api.WxCpAgentService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.WxCpAgent;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;

import java.util.List;

import static com.ossez.wechat.wecom.constant.WxCpApiPathConsts.Agent.*;


/**
 * <pre>
 *  管理企业号应用
 *  Created by huansinho on 2018/4/13.
 * </pre>
 *
 * @author <a href="https://github.com/huansinho">huansinho</a>
 */
@RequiredArgsConstructor
public class WxCpAgentServiceImpl implements WxCpAgentService {


  private final WxCpService mainService;

  @Override
  public WxCpAgent get(Integer agentId) throws WxErrorException {
    if (agentId == null) {
      throw new IllegalArgumentException("缺少agentid参数");
    }

    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_GET), agentId);
    return WxCpAgent.fromJson(this.mainService.get(url, null));
  }

  @Override
  public void set(WxCpAgent agentInfo) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_SET);
    String responseContent = this.mainService.post(url, agentInfo.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.CP));
    }
  }

  @Override
  public List<WxCpAgent> list() throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(AGENT_LIST);
    String responseContent = this.mainService.get(url, null);
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get("errcode").getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.CP));
    }

    return WxCpGsonBuilder.create().fromJson(jsonObject.get("agentlist").toString(), new TypeToken<List<WxCpAgent>>() {
    }.getType());
  }

}
