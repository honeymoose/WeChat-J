package com.ossez.wechat.common.util;

import com.ossez.wechat.common.exception.WxErrorException;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.api.WxErrorExceptionHandler;

/**
 * @author Daniel Qian
 */
@Slf4j
public class LogExceptionHandler implements WxErrorExceptionHandler {
  @Override
  public void handle(WxErrorException e) {
    log.error("Error happens", e);
  }

}
