package com.ossez.wechat.wecom.api.impl;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.wecom.config.WxCpConfigStorage;
import okhttp3.*;

import java.io.IOException;

import static com.ossez.wechat.wecom.constant.WxCpApiPathConsts.GET_TOKEN;

/**
 * The type Wx cp service ok http.
 *
 * @author someone
 */
@Slf4j
public class WxCpServiceOkHttpImpl extends BaseWxCpServiceImpl<OkHttpClient, OkHttpProxyInfo> {
  private OkHttpClient httpClient;
  private OkHttpProxyInfo httpProxy;

  @Override
  public OkHttpClient getRequestHttpClient() {
    return httpClient;
  }

  @Override
  public OkHttpProxyInfo getRequestHttpProxy() {
    return httpProxy;
  }

  @Override
  public HttpType getRequestType() {
    return HttpType.OK_HTTP;
  }

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }

    synchronized (this.globalAccessTokenRefreshLock) {
      //得到httpClient
      OkHttpClient client = getRequestHttpClient();
      //请求的request
      Request request = new Request.Builder()
        .url(String.format(this.configStorage.getApiUrl(GET_TOKEN), this.configStorage.getCorpId(),
          this.configStorage.getCorpSecret()))
        .get()
        .build();
      String resultContent = null;
      try {
        Response response = client.newCall(request).execute();
        resultContent = response.body().string();
      } catch (IOException e) {
        log.error(e.getMessage(), e);
      }

      WxError error = WxError.fromJson(resultContent, WxType.CP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      }
      WeChatAccessToken accessToken = WeChatAccessToken.fromJson(resultContent);
      this.configStorage.updateAccessToken(accessToken.getAccessToken(),
        accessToken.getExpiresIn());
    }
    return this.configStorage.getAccessToken();
  }

  @Override
  public void initHttp() {
    log.debug("WxCpServiceOkHttpImpl initHttp");
    //设置代理
    if (configStorage.getHttpProxyHost() != null && configStorage.getHttpProxyPort() > 0) {
      httpProxy = OkHttpProxyInfo.httpProxy(configStorage.getHttpProxyHost(),
        configStorage.getHttpProxyPort(),
        configStorage.getHttpProxyUsername(),
        configStorage.getHttpProxyPassword());
    }

    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
    if (httpProxy != null) {
      clientBuilder.proxy(getRequestHttpProxy().getProxy());

      //设置授权
      clientBuilder.authenticator(new Authenticator() {
        @Override
        public Request authenticate(Route route, Response response) throws IOException {
          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
          return response.request().newBuilder()
            .header("Authorization", credential)
            .build();
        }
      });
    }
    httpClient = clientBuilder.build();
  }

  @Override
  public WxCpConfigStorage getWxCpConfigStorage() {
    return this.configStorage;
  }
}
