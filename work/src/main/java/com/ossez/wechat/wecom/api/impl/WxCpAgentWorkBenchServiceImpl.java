package com.ossez.wechat.wecom.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.WxCpAgentWorkBenchService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.WxCpAgentWorkBench;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import lombok.RequiredArgsConstructor;

/**
 * The type Wx cp agent work bench service.
 *
 * @author songshiyu  created on  : create in 11:24 2020/9/28
 * @description: 工作台自定义展示实现
 */
@RequiredArgsConstructor
public class WxCpAgentWorkBenchServiceImpl implements WxCpAgentWorkBenchService {
  private final WxCpService mainService;

  @Override
  public void setWorkBenchTemplate(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.WorkBench.WORKBENCH_TEMPLATE_SET));
    this.mainService.post(url, wxCpAgentWorkBench.toTemplateString());
  }

  @Override
  public String getWorkBenchTemplate(Long agentId) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.WorkBench.WORKBENCH_TEMPLATE_GET));
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("agentid", agentId);
    return this.mainService.post(url, jsonObject.toString());
  }

  @Override
  public void setWorkBenchData(WxCpAgentWorkBench wxCpAgentWorkBench) throws WxErrorException {
    final String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.WorkBench.WORKBENCH_DATA_SET));
    this.mainService.post(url, wxCpAgentWorkBench.toUserDataString());
  }
}
