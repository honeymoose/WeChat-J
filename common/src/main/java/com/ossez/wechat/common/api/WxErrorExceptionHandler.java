package com.ossez.wechat.common.api;

import com.ossez.wechat.common.exception.WxErrorException;

/**
 * WxErrorException处理器.
 *
 * @author Daniel Qian
 */
public interface WxErrorExceptionHandler {

  void handle(WxErrorException e);

}
