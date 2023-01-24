package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;

import java.util.Map;

/**
 * Created by qianjia on 15/1/22.
 */
public class DemoOAuth2Handler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage,
                                  Map<String, Object> context, WeChatOfficialAccountService weChatOfficialAccountService,
                                  WxSessionManager sessionManager) {
    String href = "<a href=\"" + weChatOfficialAccountService.getOAuth2Service().buildAuthorizationUrl(
      weChatOfficialAccountService.getWxMpConfigStorage().getOauth2redirectUri(),
      WeChatConstant.OAuth2Scope.SNSAPI_USERINFO, null) + "\">测试oauth2</a>";
    return WxMpXmlOutMessage.TEXT().content(href)
      .fromUser(wxMessage.getToUser()).toUser(wxMessage.getFromUser())
      .build();
  }
}
