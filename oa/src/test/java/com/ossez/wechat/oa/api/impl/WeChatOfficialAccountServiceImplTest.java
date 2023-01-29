package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.bean.result.WxMpCurrentAutoReplyInfo;
import org.apache.commons.lang3.StringUtils;
import org.testng.*;
import org.testng.annotations.*;

import com.google.inject.Inject;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.common.enums.TicketType;

@Test
@Guice(modules = ApiTestModule.class)
public class WeChatOfficialAccountServiceImplTest {
  @Inject
  private WeChatOfficialAccountService wxService;

  @Test
  public void testGetCurrentAutoReplyInfo() throws WxErrorException {
    WxMpCurrentAutoReplyInfo autoReplyInfo = this.wxService.getCurrentAutoReplyInfo();

    Assert.assertNotNull(autoReplyInfo);
    System.out.println(autoReplyInfo);
  }

  @Test
  public void testClearQuota() throws WxErrorException {
    this.wxService.clearQuota(wxService.getWxMpConfigStorage().getAppId());
  }

  @Test
  public void testBuildQrConnectUrl() {
    String qrconnectRedirectUrl = ((TestConfigStorage) this.wxService.getWxMpConfigStorage()).getQrconnectRedirectUrl();
    String qrConnectUrl = this.wxService.buildQrConnectUrl(qrconnectRedirectUrl,
      WeChatConstant.QrConnectScope.SNSAPI_LOGIN, null);
    Assert.assertNotNull(qrConnectUrl);
    System.out.println(qrConnectUrl);
  }

  public void testGetTicket() throws WxErrorException {
    String ticket = this.wxService.getTicket(TicketType.SDK, false);
    System.out.println(ticket);
    Assert.assertNotNull(ticket);
  }

  public void testRefreshAccessToken() throws WxErrorException {
    ConfigStorage configStorage = this.wxService.getWxMpConfigStorage();
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    Assert.assertNotEquals(before, after);
    Assert.assertTrue(StringUtils.isNotBlank(after));
  }
}
