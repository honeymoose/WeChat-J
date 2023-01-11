package com.ossez.wechat.oa.util.requestexecuter.material;

import com.google.common.collect.ImmutableMap;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.bean.material.WxMpMaterialNews;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import okhttp3.*;

import java.io.IOException;

/**
 * .
 *
 * @author ecoolper
 * created on  2017/5/5
 */
@Slf4j
public class MaterialNewsInfoOkhttpRequestExecutor extends MaterialNewsInfoRequestExecutor<OkHttpClient, OkHttpProxyInfo> {
  public MaterialNewsInfoOkhttpRequestExecutor(RequestHttp requestHttp) {
    super(requestHttp);
  }

  @Override
  public WxMpMaterialNews execute(String uri, String materialId, WxType wxType) throws WxErrorException, IOException {
    RequestBody requestBody = RequestBody.create(MediaType.parse("application/json"),
      WxGsonBuilder.create().toJson(ImmutableMap.of("media_id", materialId)));
    Request request = new Request.Builder().url(uri).post(requestBody).build();

    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String responseContent = response.body().string();
    log.debug("响应原始数据：{}", responseContent);

    WxError error = WxError.fromJson(responseContent, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    } else {
      return WxMpGsonBuilder.create().fromJson(responseContent, WxMpMaterialNews.class);
    }
  }
}
