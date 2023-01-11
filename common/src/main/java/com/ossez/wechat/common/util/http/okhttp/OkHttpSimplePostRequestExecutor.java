package com.ossez.wechat.common.util.http.okhttp;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.SimplePostRequestExecutor;
import okhttp3.*;

import java.io.IOException;
import java.util.Objects;

/**
 * .
 *
 * @author ecoolper
 * created on  2017/5/4
 */
@Slf4j
public class OkHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public OkHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity, WxType wxType) throws WxErrorException, IOException {
    RequestBody body = RequestBody.Companion.create(postEntity, MediaType.parse("text/plain; charset=utf-8"));
    Request request = new Request.Builder().url(uri).post(body).build();
    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    return this.handleResponse(wxType, Objects.requireNonNull(response.body()).string());
  }

}
