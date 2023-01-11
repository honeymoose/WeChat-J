package com.ossez.wechat.oa.util.requestexecuter.media;

import java.io.File;
import java.io.IOException;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;
import com.ossez.wechat.oa.bean.material.WxMediaImgUploadResult;

/**
 * @author miller
 */
public abstract class MediaImgUploadRequestExecutor<H, P> implements RequestExecutor<WxMediaImgUploadResult, File> {
  protected RequestHttp<H, P> requestHttp;

  public MediaImgUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMediaImgUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMediaImgUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MediaImgUploadApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MediaImgUploadHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MediaImgUploadOkhttpRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
