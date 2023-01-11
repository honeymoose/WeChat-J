package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutTextMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoTextHandler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WxMpService wxMpService, WxSessionManager sessionManager) {
    WxMpXmlOutTextMessage m
      = WxMpXmlOutMessage.TEXT().content("测试加密消息").fromUser(wxMessage.getToUser())
      .toUser(wxMessage.getFromUser()).build();
    return m;
  }

}
