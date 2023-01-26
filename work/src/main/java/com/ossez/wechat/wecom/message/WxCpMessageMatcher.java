package com.ossez.wechat.wecom.message;

import com.ossez.wechat.wecom.bean.message.WxCpXmlMessage;

/**
 * 消息匹配器，用在消息路由的时候
 *
 * @author Daniel Qian
 */
public interface WxCpMessageMatcher {

  /**
   * 消息是否匹配某种模式
   *
   * @param message the message
   * @return the boolean
   */
  boolean match(WxCpXmlMessage message);

}
