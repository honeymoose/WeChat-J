package me.chanjar.weixin.mp.util.requestexecuter.voice;

import java.io.File;
import java.io.IOException;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/9.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public abstract class VoiceUploadRequestExecutor<H, P> implements RequestExecutor<Boolean, File> {
  protected RequestHttp<H, P> requestHttp;

  public VoiceUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<Boolean> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<Boolean, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new VoiceUploadApacheHttpRequestExecutor(requestHttp);
      case JODD_HTTP:
      case OK_HTTP:
      default:
        return null;
    }
  }

}
