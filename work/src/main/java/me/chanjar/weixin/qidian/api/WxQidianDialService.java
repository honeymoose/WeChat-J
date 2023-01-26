package me.chanjar.weixin.qidian.api;

import com.ossez.wechat.common.exception.WxErrorException;
import me.chanjar.weixin.qidian.bean.dial.IVRDialRequest;
import me.chanjar.weixin.qidian.bean.dial.IVRDialResponse;
import me.chanjar.weixin.qidian.bean.dial.IVRListResponse;

/**
 * 基础话务相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianDialService {
  IVRDialResponse ivrDial(IVRDialRequest ivrDial) throws WxErrorException;

  IVRListResponse getIVRList() throws WxErrorException;

}
