package com.ossez.wechat.oa.util.requestexecuter.material;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestHttp;

import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class MaterialDeleteJoddHttpRequestExecutor extends MaterialDeleteRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public MaterialDeleteJoddHttpRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public Boolean execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    request.query("media_id", materialId);
    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return true;
    }
  }
}
