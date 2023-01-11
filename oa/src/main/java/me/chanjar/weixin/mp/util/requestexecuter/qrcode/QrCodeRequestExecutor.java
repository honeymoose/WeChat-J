package me.chanjar.weixin.mp.util.requestexecuter.qrcode;

import java.io.File;
import java.io.IOException;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;
import me.chanjar.weixin.mp.bean.result.WxMpQrCodeTicket;

/**
 * 获得QrCode图片 请求执行器.
 *
 * @author chanjarster
 */
public abstract class QrCodeRequestExecutor<H, P> implements RequestExecutor<File, WxMpQrCodeTicket> {
  protected RequestHttp<H, P> requestHttp;

  public QrCodeRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, WxMpQrCodeTicket data, ResponseHandler<File> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<File, WxMpQrCodeTicket> create(RequestHttp requestHttp) throws WxErrorException {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new QrCodeApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new QrCodeJoddHttpRequestExecutor(requestHttp);
      case OK_HTTP:
        return new QrCodeOkhttpRequestExecutor(requestHttp);
      default:
        throw new WxErrorException("不支持的http框架");
    }
  }

}
