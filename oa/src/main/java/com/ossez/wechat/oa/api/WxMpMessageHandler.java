package com.ossez.wechat.oa.api;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * 处理微信推送消息的处理器接口.
 *
 * @author Daniel Qian
 */
public interface WxMpMessageHandler {

  /**
   * 处理微信推送消息.
   *
   * @param wxMessage      微信推送消息
   * @param context        上下文，如果handler或interceptor之间有信息要传递，可以用这个
   * @param wxMpService    服务类
   * @param sessionManager session管理器
   * @return xml格式的消息，如果在异步规则里处理的话，可以返回null
   * @throws WxErrorException 异常
   */
  WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                           Map<String, Object> context,
                           WxMpService wxMpService,
                           WxSessionManager sessionManager) throws WxErrorException;

}
