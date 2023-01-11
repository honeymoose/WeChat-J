package me.chanjar.weixin.open.executor;

import java.io.File;
import java.io.IOException;

import me.chanjar.weixin.common.enums.WxType;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.common.util.http.RequestExecutor;
import me.chanjar.weixin.common.util.http.RequestHttp;
import me.chanjar.weixin.common.util.http.ResponseHandler;
import me.chanjar.weixin.open.bean.ma.WxMaQrcodeParam;

/**
 * 获得小程序体验QrCode图片 请求执行器.
 *
 * @author yqx
 * created on  2018-09-13
 */
public abstract class MaQrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMaQrcodeParam> {
  protected RequestHttp<H, P> requestHttp;

  public MaQrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, WxMaQrcodeParam data, ResponseHandler<File> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<File, WxMaQrcodeParam> create(RequestHttp requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new MaQrCodeApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new MaQrCodeJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new MaQrCodeOkhttpRequestExecutor(requestHttp);
      default:
        throw new WxErrorException("不支持的http框架");
    }
  }

}
