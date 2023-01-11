package com.ossez.wechat.oa.util.requestexecuter.material;


import java.io.IOException;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;
import com.ossez.wechat.oa.bean.material.WxMpMaterialVideoInfoResult;

public abstract class MaterialVideoInfoRequestExecutor<H, P> implements RequestExecutor<WxMpMaterialVideoInfoResult, String> {
  protected RequestHttp<H, P> requestHttp;

  public MaterialVideoInfoRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, String data, ResponseHandler<WxMpMaterialVideoInfoResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMpMaterialVideoInfoResult, String> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaterialVideoInfoApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MaterialVideoInfoJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MaterialVideoInfoOkhttpRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
