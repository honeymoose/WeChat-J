package com.ossez.wechat.oa.util.requestexecuter.material;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.apache.Utf8ResponseHandler;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.bean.material.WxMpMaterial;
import com.ossez.wechat.oa.bean.material.WxMpMaterialUploadResult;
import org.apache.http.Consts;
import org.apache.http.HttpHost;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.ContentType;
import org.apache.http.entity.mime.HttpMultipartMode;
import org.apache.http.entity.mime.MultipartEntityBuilder;
import org.apache.http.entity.mime.content.StringBody;
import org.apache.http.impl.client.CloseableHttpClient;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Map;

/**
 * Created by ecoolper on 2017/5/5.
 */
public class MaterialUploadApacheHttpRequestExecutor extends MaterialUploadRequestExecutor<CloseableHttpClient, HttpHost> {
  public MaterialUploadApacheHttpRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialUploadResult execute(String uri, WxMpMaterial material, WxType wxType) throws WxErrorException, IOException {
    HttpPost httpPost = new HttpPost(uri);
    if (requestHttp.getRequestHttpProxy() != null) {
      RequestConfig response = RequestConfig.custom().setProxy(requestHttp.getRequestHttpProxy()).build();
      httpPost.setConfig(response);
    }

    if (material == null) {
      throw new WxErrorException("非法请求，material参数为空");
    }

    File file = material.getFile();
    if (file == null || !file.exists()) {
      throw new FileNotFoundException();
    }

    MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create();
    multipartEntityBuilder
      .addBinaryBody("media", file)
      .setMode(HttpMultipartMode.RFC6532);
    Map<String, String> form = material.getForm();
    if (material.getForm() != null) {
      multipartEntityBuilder.addPart("description",
        new StringBody(WxGsonBuilder.create().toJson(form), ContentType.create("text/plain", Consts.UTF_8)));
    }

    httpPost.setEntity(multipartEntityBuilder.build());
    httpPost.setHeader("Content-Type", ContentType.MULTIPART_FORM_DATA.toString());

    try (CloseableHttpResponse response = requestHttp.getRequestHttpClient().execute(httpPost)) {
      String responseContent = Utf8ResponseHandler.INSTANCE.handleResponse(response);
      WxError error = WxError.fromJson(responseContent, WxType.MP);
      if (error.getErrorCode() != 0) {
        throw new WxErrorException(error);
      } else {
        return WxMpMaterialUploadResult.fromJson(responseContent);
      }
    } finally {
      httpPost.releaseConnection();
    }
  }
}
