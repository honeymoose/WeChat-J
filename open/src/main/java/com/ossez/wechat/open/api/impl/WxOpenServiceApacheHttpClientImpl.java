package com.ossez.wechat.open.api.impl;

import com.ossez.wechat.common.bean.result.WxMinishopImageUploadResult;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.MinishopUploadRequestExecutor;
import com.ossez.wechat.common.util.http.SimpleGetRequestExecutor;
import com.ossez.wechat.common.util.http.SimplePostRequestExecutor;
import com.ossez.wechat.common.util.http.apache.ApacheHttpClientBuilder;
import com.ossez.wechat.common.util.http.apache.DefaultApacheHttpClientBuilder;
import com.ossez.wechat.open.api.WxOpenConfigStorage;
import org.apache.http.HttpHost;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;

/**
 * apache-http方式实现
 *
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenServiceApacheHttpClientImpl extends WxOpenServiceAbstractImpl<CloseableHttpClient, HttpHost> {
  private CloseableHttpClient httpClient;
  private HttpHost httpProxy;

  @Override
  public void initHttp() {
    WxOpenConfigStorage configStorage = this.getWxOpenConfigStorage();
    ApacheHttpClientBuilder apacheHttpClientBuilder = configStorage.getApacheHttpClientBuilder();
    if (null == apacheHttpClientBuilder) {
      apacheHttpClientBuilder = DefaultApacheHttpClientBuilder.get();
    }

    apacheHttpClientBuilder.httpProxyHost(configStorage.getHttpProxyHost())
      .httpProxyPort(configStorage.getHttpProxyPort())
      .httpProxyUsername(configStorage.getHttpProxyUsername())
      .httpProxyPassword(configStorage.getHttpProxyPassword());

    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      this.httpProxy = new HttpHost(configStorage.getHttpProxyHost(), configStorage.getHttpProxyPort());
    }

    this.httpClient = apacheHttpClientBuilder.build();

  }

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
  public String get(String url, String queryParam) throws WxErrorException {
    return execute(SimpleGetRequestExecutor.create(this), url, queryParam);
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return execute(SimplePostRequestExecutor.create(this), url, postData);
  }

  @Override
  public WxMinishopImageUploadResult uploadMinishopMediaFile(String url, File file) throws WxErrorException {
    return execute(MinishopUploadRequestExecutor.create(this), url, file);
  }
}
