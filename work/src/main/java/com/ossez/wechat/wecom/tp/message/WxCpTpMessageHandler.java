package com.ossez.wechat.wecom.tp.message;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.wecom.bean.message.WxCpTpXmlMessage;
import com.ossez.wechat.wecom.bean.message.WxCpXmlOutMessage;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

import java.util.Map;

/**
 * 处理微信推送消息的处理器接口
 *
 * @author Daniel Qian
 */
public interface WxCpTpMessageHandler {

  /**
   * Handle wx cp xml out message.
   *
   * @param wxMessage      the wx message
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxCpService    the wx cp service
   * @param sessionManager the session manager
   * @return xml格式的消息 ，如果在异步规则里处理的话，可以返回null
   * @throws WxErrorException the wx error exception
   */
  WxCpXmlOutMessage handle(WxCpTpXmlMessage wxMessage,
                           Map<String, Object> context,
                           WxCpTpService wxCpService,
                           WxSessionManager sessionManager) throws WxErrorException;

}
