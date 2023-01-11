package com.ossez.wechat.open.api.impl;

import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.open.api.WxOpenComponentService;
import com.ossez.wechat.open.api.WxOpenConfigStorage;
import com.ossez.wechat.open.api.WxOpenService;

import java.io.IOException;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Slf4j
public abstract class WxOpenServiceAbstractImpl<H, P> implements WxOpenService, RequestHttp<H, P> {
  private WxOpenComponentService wxOpenComponentService = new WxOpenComponentServiceImpl(this);
  private WxOpenConfigStorage wxOpenConfigStorage;

  @Override
  public WxOpenComponentService getWxOpenComponentService() {
    return wxOpenComponentService;
  }

  @Override
  public WxOpenConfigStorage getWxOpenConfigStorage() {
    return wxOpenConfigStorage;
  }

  @Override
  public void setWxOpenConfigStorage(WxOpenConfigStorage wxOpenConfigStorage) {
    this.wxOpenConfigStorage = wxOpenConfigStorage;
    this.initHttp();
  }

  /**
   * 初始化 RequestHttp.
   */
  public abstract void initHttp();

  protected <T, E> T execute(RequestExecutor<T, E> executor, String uri, E data) throws WxErrorException {
    try {
      T result = executor.execute(uri, data, WxType.Open);
      log.debug("\n【请求地址】: {}\n【请求参数】：{}\n【响应数据】：{}", uri, data, result);
      return result;
    } catch (WxErrorException e) {
      WxError error = e.getError();
      if (error.getErrorCode() != 0) {
        log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【错误信息】：{}", uri, data, error);
        throw new WxErrorException(error, e);
      }
      return null;
    } catch (IOException e) {
      log.warn("\n【请求地址】: {}\n【请求参数】：{}\n【异常信息】：{}", uri, data, e.getMessage());
      throw new WxRuntimeException(e);
    }
  }
}
