package com.ossez.wechat.qidian.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.apache.ApacheHttpClientBuilder;
import com.ossez.wechat.common.util.http.apache.DefaultApacheHttpClientBuilder;
import com.ossez.wechat.qidian.config.WxQidianConfigStorage;
import com.ossez.wechat.qidian.enums.WxQidianApiUrl;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.BasicResponseHandler;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.IOException;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

/**
 * apache http client方式实现.
 *
 * @author someone
 */
public class WxQidianServiceHttpClientImpl extends BaseWxQidianServiceImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public CloseableHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public HttpHost getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.APACHE_HTTP;
  }

  @Override
  public void initHttp() {
    WxQidianConfigStorage configStorage = this.getWxMpConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
        .httpProxyPort(configStorage.getHttpProxyPort()).httpProxyUsername(configStorage.getHttpProxyUsername())
        .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();
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
      try {
        HttpGet httpGet = new HttpGet(url);
        if (this.getRequestHttpProxy() != null) {
          RequestConfig requestConfig = RequestConfig.custom().setProxy(this.getRequestHttpProxy()).build();
          httpGet.setConfig(requestConfig);
        }
        try (CloseableHttpResponse response = getRequestHttpClient().execute(httpGet)) {
          return this.extractAccessToken(new BasicResponseHandler().handleResponse(response));
        } finally {
          httpGet.releaseConnection();
        }
      } catch (IOException e) {
        throw new WxRuntimeException(e);
      }
    } catch (InterruptedException e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

}
