package com.ossez.wechat.qidian.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.qidian.config.WxQidianConfigStorage;
import com.ossez.wechat.qidian.enums.WxQidianApiUrl;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.ProxyInfo;
import jodd.http.net.SocketHttpConnectionProvider;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * jodd-http方式实现.
 *
 * @author someone
 */
public class WxQidianServiceJoddHttpImpl extends BaseWxQidianServiceImpl<HttpConnectionProvider, ProxyInfo> {
  private HttpConnectionProvider httpClient;
  private ProxyInfo httpProxy;

  @Override
  public HttpConnectionProvider getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public ProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.JODD_HTTP;
  }

  @Override
  public void initHttp() {

    WxQidianConfigStorage configStorage = this.getWxMpConfigStorage();

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = new ProxyInfo(ProxyInfo.ProxyType.HTTP, configStorage.getHttpProxyHost(),
          configStorage.getHttpProxyPort(), configStorage.getHttpProxyUsername(), configStorage.getHttpProxyPassword());
    }

    httpClient = new SocketHttpConnectionProvider();
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    final WxQidianConfigStorage config = this.getWxMpConfigStorage();
    if (!config.isAccessTokenExpired() && !forceRefresh) {
      return config.getAccessToken();
    }

    Lock lock = config.getAccessTokenLock();
    boolean locked = false;
    try {
      do {
        locked = lock.tryLock(100, TimeUnit.MILLISECONDS);
        if (!forceRefresh && !config.isAccessTokenExpired()) {
          return config.getAccessToken();
        }
      } while (!locked);
      String url = String.format(WxQidianApiUrl.Other.GET_ACCESS_TOKEN_URL.getUrl(config), config.getAppId(), config.getSecret());

      HttpRequest request = HttpRequest.get(url);
      if (this.getRequestHttpProxy() != null) {
        SocketHttpConnectionProvider provider = new SocketHttpConnectionProvider();
        provider.useProxy(getRequestHttpProxy());

        request.withConnectionProvider(provider);
      }

      return this.extractAccessToken(request.send().bodyText());
    } catch (InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

}
