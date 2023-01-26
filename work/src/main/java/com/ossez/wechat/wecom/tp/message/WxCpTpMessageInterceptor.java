package com.ossez.wechat.wecom.tp.message;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.wecom.bean.message.WxCpTpXmlMessage;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

import java.util.Map;

/**
 * 微信消息拦截器，可以用来做验证
 *
 * @author Daniel Qian
 */
public interface WxCpTpMessageInterceptor {

  /**
   * 拦截微信消息
   *
   * @param wxMessage      the wx message
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService    the wx cp service
   * @param sessionManager the session manager
   * @return true代表OK ，false代表不OK
   * @throws WxErrorException the wx error exception
   */
  boolean intercept(WxCpTpXmlMessage wxMessage,
                    Map<String, Object> context,
                    WxCpTpService wxCpService,
                    WxSessionManager sessionManager) throws WxErrorException;

}
