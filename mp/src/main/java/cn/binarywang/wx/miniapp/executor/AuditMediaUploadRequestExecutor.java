package cn.binarywang.wx.miniapp.executor;

import java.io.File;
import java.io.IOException;

import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.common.util.http.ResponseHandler;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import cn.binarywang.wx.miniapp.bean.WxMaAuditMediaUploadResult;

/**
 * 小程序 提审素材上传接口
 * 上传媒体文件请求执行器.
 * 请求的参数是File, 返回的结果是String
 *
 * @author yangyh22
 * @since 2020/11/14
 */
public abstract class AuditMediaUploadRequestExecutor<H, P> implements RequestExecutor<WxMaAuditMediaUploadResult, File> {

  protected RequestHttp<H, P> requestHttp;

  public AuditMediaUploadRequestExecutor(RequestHttp requestHttp) {
    this.requestHttp = requestHttp;
  }

  @Override
  public void execute(String uri, File data, ResponseHandler<WxMaAuditMediaUploadResult> handler, WxType wxType) throws WxErrorException, IOException {
    handler.handle(this.execute(uri, data, wxType));
  }

  public static RequestExecutor<WxMaAuditMediaUploadResult, File> create(RequestHttp requestHttp) {
    switch (requestHttp.getRequestType()) {
      case APACHE_HTTP:
        return new ApacheAuditMediaUploadRequestExecutor(requestHttp);
      case JODD_HTTP:
        return new JoddHttpAuditMediaUploadRequestExecutor(requestHttp);
      case OK_HTTP:
        return new OkHttpAuditMediaUploadRequestExecutor(requestHttp);
      default:
        return null;
    }
  }

}
