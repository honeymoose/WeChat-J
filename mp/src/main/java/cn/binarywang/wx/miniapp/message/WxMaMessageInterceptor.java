package cn.binarywang.wx.miniapp.message;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.WxMaMessage;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.session.WxSessionManager;

import java.util.Map;

/**
 * 微信消息拦截器，可以用来做验证.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
public interface WxMaMessageInterceptor {

  /**
   * 拦截微信消息.
   *
   * @param wxMessage      .
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMaService    .
   * @param sessionManager .
   * @return true代表OK，false代表不OK
   * @throws WxErrorException .
   */
  boolean intercept(WxMaMessage wxMessage,
                    Map<String, Object> context,
                    WxMaService wxMaService,
                    WxSessionManager sessionManager) throws WxErrorException;

}
