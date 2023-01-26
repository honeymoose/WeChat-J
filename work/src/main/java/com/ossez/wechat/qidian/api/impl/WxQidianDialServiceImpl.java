package com.ossez.wechat.qidian.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.qidian.bean.dial.IVRDialRequest;
import com.ossez.wechat.qidian.bean.dial.IVRDialResponse;
import com.ossez.wechat.qidian.bean.dial.IVRListResponse;
import com.ossez.wechat.qidian.enums.WxQidianApiUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.qidian.api.WxQidianDialService;
import com.ossez.wechat.qidian.api.WxQidianService;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxQidianDialServiceImpl implements WxQidianDialService {
  private final WxQidianService wxQidianService;

  @Override
  public IVRDialResponse ivrDial(IVRDialRequest ivrDial) throws WxErrorException {
    String json = ivrDial.toJson();

    log.debug("IVR外呼：{}", json);

    String result = this.wxQidianService.post(WxQidianApiUrl.Dial.IVR_DIAL, json);
    log.debug("创建菜单：{},结果：{}", json, result);

    return IVRDialResponse.fromJson(result);
  }

  @Override
  public IVRListResponse getIVRList() throws WxErrorException {
    String result = this.wxQidianService.get(WxQidianApiUrl.Dial.GET_IVR_LIST, null);
    return IVRListResponse.fromJson(result);
  }

}
