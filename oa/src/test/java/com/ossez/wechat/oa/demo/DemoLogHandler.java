package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoLogHandler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
    System.out.println(wxMessage.toString());
    return null;
  }
}
