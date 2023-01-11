package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;

import java.io.IOException;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class QrcodeBytesRequestExecutor<H, P> implements RequestExecutor<byte[], AbstractWxMaQrcodeWrapper> {

  protected RequestHttp<H, P> requestHttp;

  public QrcodeBytesRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, AbstractWxMaQrcodeWrapper data, ResponseHandler<byte[]> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<byte[], AbstractWxMaQrcodeWrapper> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheQrcodeBytesRequestExecutor(requestHttp);
      case JODD_HTTP:
        return null;
      case OK_HTTP:
        return new OkHttpQrcodeBytesRequestExecutor(requestHttp);
      default:
        return null;
    }
  }
}
