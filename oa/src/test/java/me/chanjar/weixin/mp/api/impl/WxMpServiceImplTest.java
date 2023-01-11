package me.chanjar.weixin.mp.api.impl;

import org.apache.commons.lang3.StringUtils;
import org.testng.*;
import org.testng.annotations.*;

import com.google.inject.Inject;
import com.ossez.wechat.common.api.WxConsts;
import com.ossez.wechat.common.exception.WxErrorException;
import me.chanjar.weixin.mp.config.WxMpConfigStorage;
import me.chanjar.weixin.mp.api.WxMpService;
import me.chanjar.weixin.mp.api.test.ApiTestModule;
import me.chanjar.weixin.mp.api.test.TestConfigStorage;
import me.chanjar.weixin.mp.bean.result.WxMpCurrentAutoReplyInfo;
import com.ossez.wechat.common.enums.TicketType;

@Test
@Guice(modules = ApiTestModule.class)
public class WxMpServiceImplTest {
  @Inject
  private WxMpService wxService;

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
      WxConsts.QrConnectScope.SNSAPI_LOGIN, null);
    Assert.assertNotNull(qrConnectUrl);
    System.out.println(qrConnectUrl);
  }

  public void testGetTicket() throws WxErrorException {
    String ticket = this.wxService.getTicket(TicketType.SDK, false);
    System.out.println(ticket);
    Assert.assertNotNull(ticket);
  }

  public void testRefreshAccessToken() throws WxErrorException {
    WxMpConfigStorage configStorage = this.wxService.getWxMpConfigStorage();
    String before = configStorage.getAccessToken();
    this.wxService.getAccessToken(false);

    String after = configStorage.getAccessToken();
    Assert.assertNotEquals(before, after);
    Assert.assertTrue(StringUtils.isNotBlank(after));
  }
}
