package com.ossez.wechat.oa.util.requestexecuter.material;

import jodd.http.HttpConnectionProvider;
import jodd.http.HttpRequest;
import jodd.http.HttpResponse;
import jodd.http.ProxyInfo;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.bean.material.WxMpMaterial;
import com.ossez.wechat.oa.bean.material.WxMpMaterialUploadResult;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Map;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class MaterialUploadJoddHttpRequestExecutor extends MaterialUploadRequestExecutor<HttpConnectionProvider, ProxyInfo> {
  public MaterialUploadJoddHttpRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material, WxType wxType) throws WxErrorException, IOException {
    HttpRequest request = HttpRequest.post(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      requestHttp.getRequestHttpClient().useProxy(requestHttp.getRequestHttpProxy());
    }
    request.withConnectionProvider(requestHttp.getRequestHttpClient());

    if (material == null) {
      throw new WxErrorException("非法请求，material参数为空");
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }
    request.form("media", file);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      request.form("description", WxGsonBuilder.create().toJson(form));
    }

    HttpResponse response = request.send();
    response.charset(StandardCharsets.UTF_8.name());
    String responseContent = response.bodyText();
    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpMaterialUploadResult.fromJson(responseContent);
    }
  }
}
