package com.ossez.wechat.qidian.api;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.qidian.bean.dial.IVRDialRequest;
import com.ossez.wechat.qidian.bean.dial.IVRDialResponse;
import com.ossez.wechat.qidian.bean.dial.IVRListResponse;

/**
 * 基础话务相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianDialService {
  IVRDialResponse ivrDial(IVRDialRequest ivrDial) throws WxErrorException;

  IVRListResponse getIVRList() throws WxErrorException;

}
