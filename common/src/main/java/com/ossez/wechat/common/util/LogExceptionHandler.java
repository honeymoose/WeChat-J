package com.ossez.wechat.common.util;

import com.ossez.wechat.common.api.WxErrorExceptionHandler;
import com.ossez.wechat.common.exception.WxErrorException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class LogExceptionHandler implements WxErrorExceptionHandler {
    private final Logger log = LoggerFactory.getLogger(LogExceptionHandler.class);

    @Override
    public void handle(WxErrorException e) {
        log.error("App Error", e);
    }

}
