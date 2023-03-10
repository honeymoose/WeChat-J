package cn.binarywang.wx.miniapp.executor;

import cn.binarywang.wx.miniapp.bean.AbstractWxMaQrcodeWrapper;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import okhttp3.*;
import org.apache.commons.io.IOUtils;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author wenqiang
 * @since 2020/12/25
 */
public class OkHttpQrcodeBytesRequestExecutor extends QrcodeBytesRequestExecutor<OkHttpClient, OkHttpProxyInfo> {


  public OkHttpQrcodeBytesRequestExecutor(RequestHttp<OkHttpClient, OkHttpProxyInfo> requestHttp) {
    super(requestHttp);
  }

  /**
   * 执行http请求.
   *
   * @param uri           uri
   * @param qrcodeWrapper 数据
   * @param wxType        微信模块类型
   * @return 响应结果
   * @throws WxErrorException 自定义异常
   * @throws IOException      io异常
   */
  @Override
  public byte[] execute(String uri, AbstractWxMaQrcodeWrapper qrcodeWrapper, WxType wxType) throws WxErrorException, IOException {
    RequestBody body = RequestBody.create(qrcodeWrapper.toJson(), String.valueOf(MediaType.parse("application/json; charset=utf-8")));
    Request request = new Request.Builder().url(uri).post(body).build();
    Response response = requestHttp.getRequestHttpClient().newCall(request).execute();
    String contentTypeHeader = response.header("Content-Type");
    if (null != contentTypeHeader && contentTypeHeader.startsWith("application/json")) {
      String responseContent = response.body().string();
      throw new WxErrorException(WxError.fromJson(responseContent, wxType));
    }

    try (InputStream inputStream = response.body().byteStream()) {
      return IOUtils.toByteArray(inputStream);
    }
  }
}
