package com.ossez.wechat.common.util.http.jodd;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.SimplePostRequestExecutor;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * .
 *
 * @author ecoolper
 * created on  2017/5/4
 */
public class JoddHttpSimplePostRequestExecutor extends SimplePostRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public JoddHttpSimplePostRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public String execute(String uri, String postEntity, WxType wxType) throws WxErrorException, IOException {
    HttpConnectionProvider provider = requestHttp.getRequestHttpClient();
    ProxyInfo proxyInfo = requestHttp.getRequestHttpProxy();

    HttpRequest request = HttpRequest.post(uri);
    if (proxyInfo != null) {
      provider.useProxy(proxyInfo);
    }
    request.withConnectionProvider(provider);
    if (postEntity != null) {
      request.bodyText(postEntity);
    }
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());

    return this.handleResponse(wxType, response.bodyText());
  }

}
