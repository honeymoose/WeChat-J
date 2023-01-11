package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.api.WxConsts;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoOAuth2Handler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WxMpService wxMpService,
                                  WxSessionManager sessionManager) {
    String href = "<a href=\"" + wxMpService.getOAuth2Service().buildAuthorizationUrl(
      wxMpService.getWxMpConfigStorage().getOauth2redirectUri(),
      WxConsts.OAuth2Scope.SNSAPI_USERINFO, null) + "\">测试oauth2</a>";
    return WxMpXmlOutMessage.TEXT().content(href)
      .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
      .build();
  }
}
