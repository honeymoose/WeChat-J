package com.ossez.wechat.open.api.impl;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.api.WeChatOpenApi;
import com.ossez.wechat.common.bean.result.WxMinishopImageUploadResult;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.WeChatErrorInterceptor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.open.api.WxOpenComponentService;
import com.ossez.wechat.open.api.WxOpenConfigStorage;
import com.ossez.wechat.open.api.WeChatOpenService;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Slf4j
public class WeChatOpenServiceImpl<H, P> implements WeChatOpenService, RequestHttp<H, P> {


  WeChatOpenApi weChatOpenApi;
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

    ObjectMapper mapper = new ObjectMapper();
    mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
    mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
    mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

    OkHttpClient client = new OkHttpClient.Builder()
            .addInterceptor(new WeChatErrorInterceptor())
            .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS)).readTimeout(1000, TimeUnit.SECONDS).build();

    Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatConstant.ENDPOINT_WECHAT).client(client)
            .addConverterFactory(JacksonConverterFactory.create(mapper))
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build();

    weChatOpenApi = retrofit.create(WeChatOpenApi.class);

  }

  @Override
  public String get(String url, String queryParam) throws WxErrorException {
    return null;
  }

  @Override
  public String post(String url, String postData) throws WxErrorException {
    return null;
  }

  @Override
  public WxMinishopImageUploadResult uploadMinishopMediaFile(String url, File file) throws WxErrorException {
    return null;
  }

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

  @Override
  public H getRequestHttpClient() {
    return null;
  }

  @Override
  public P getRequestHttpProxy() {
    return null;
  }

  @Override
  public HttpType getRequestType() {
    return null;
  }
}
