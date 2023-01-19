package com.ossez.wechat.open.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.bean.oauth2.WxOAuth2AccessToken;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.open.api.WeChatOfficialAccountService;
import com.ossez.wechat.open.test.ApiTestModule;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

@Guice(modules = ApiTestModule.class)
public class WxOpenMpOAuth2ServiceImplTest {

  @Inject
  protected WeChatOfficialAccountService wxOpenMpService;


  @Test
  public void buildAuthorizationUrl() {
    String url = wxOpenMpService.getOAuth2Service().buildAuthorizationUrl("https://t.aaxp.cn/api/base/mp/showCode", "snsapi_userinfo", "");
    System.out.println(url);
  }

  @Test
  public void getAccessToken() throws WxErrorException {
    WxOAuth2AccessToken result = wxOpenMpService.getOAuth2Service().getAccessToken("041crm0005iFJL1b2l400I0s0k4crm0z");
    System.out.println(result);
  }
}
