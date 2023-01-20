package com.ossez.wechat.oa.api.impl.okhttp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.constant.WeChatApiUrl;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.ossez.wechat.oa.api.impl.BaseWeChatOfficialAccountServiceImpl;
import com.ossez.wechat.oa.config.WxMpConfigStorage;
import okhttp3.*;
import retrofit2.Response;
import retrofit2.Retrofit;

import java.io.IOException;
import java.util.Objects;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.Other.GET_ACCESS_TOKEN_URL;

/**
 * okhttp实现.
 *
 * @author someone
 */
public class WeChatOfficialAccountServiceOkHttp extends BaseWeChatOfficialAccountServiceImpl<OkHttpClient, OkHttpProxyInfo> {

  WeChatOfficialAccountApi weChatOfficialAccountApi;
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
    final WxMpConfigStorage config = this.getWxMpConfigStorage();
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
      String url = String.format(GET_ACCESS_TOKEN_URL.getUrl(config), config.getAppId(), config.getSecret());

      Response response = weChatOfficialAccountApi.getAccessToken("client_credential", config.getAppId(),config.getSecret()).execute();

      return "response";
//
      Request request = new Request.Builder().url(url).get().build();
      Response response = getRequestHttpClient().newCall(request).execute();
      return this.extractAccessToken(Objects.requireNonNull(response.body()).string());
    } catch (Exception e) {
      throw new WxRuntimeException(e);
    } finally {
      if (locked) {
        lock.unlock();
      }
    }
  }

  @Override
  public void initHttp() {

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new AuthenticationInterceptor(null))
            .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS))
            .readTimeout(1000, TimeUnit.SECONDS)
            .build();

    Retrofit retrofit = new Retrofit.Builder()
            .baseUrl(WeChatApiUrl.OFFICIAL_ACCOUNT)
            .client(client)
//            .addConverterFactory(JacksonConverterFactory.create(mapper))
//            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    this.weChatOfficialAccountApi = retrofit.create(WeChatOfficialAccountApi.class);


//    WxMpConfigStorage wxMpConfigStorage = getWxMpConfigStorage();
//    //设置代理
//    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
//      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(),
//        wxMpConfigStorage.getHttpProxyPort(),
//        wxMpConfigStorage.getHttpProxyUsername(),
//        wxMpConfigStorage.getHttpProxyPassword());
//    }
//
//    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//    if (httpProxy != null) {
//      clientBuilder.proxy(getRequestHttpProxy().getProxy());
//
//      //设置授权
//      clientBuilder.authenticator(new Authenticator() {
//        @Override
//        public Request authenticate(Route route, Response response) throws IOException {
//          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
//          return response.request().newBuilder()
//            .header("Authorization", credential)
//            .build();
//        }
//      });
//    }
//    httpClient = clientBuilder.build();
  }

}
