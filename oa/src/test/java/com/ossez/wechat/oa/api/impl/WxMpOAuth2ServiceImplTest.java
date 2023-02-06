package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatOAuth2AccessToken;
import com.ossez.wechat.common.model.entity.WeChatOAuth2UserInfo;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import javax.inject.Inject;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-08-09
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpOAuth2ServiceImplTest {
  @Inject
  private WeChatOfficialAccountService mpService;

  @Test
  public void testBuildAuthorizationUrl() {
    final String url = this.mpService.getOAuth2Service().buildAuthorizationUrl("http://www.baidu.com", "test", "GOD");
    Assertions.assertThat(url).isEqualTo("https://open.weixin.qq.com/connect/oauth2/authorize?appid=" +
      this.mpService.getWxMpConfigStorage().getAppId() +
      "&redirect_uri=http%3A%2F%2Fwww.baidu.com&response_type=code&scope=test&state=GOD&connect_redirect=1#wechat_redirect");
  }

  @Test
  public void testGetAccessToken() throws WxErrorException {
    final WeChatOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().getAccessToken("11");
    Assertions.assertThat(accessToken).isNotNull();
  }

  @Test
  public void testRefreshAccessToken() throws WxErrorException {
    final WeChatOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().refreshAccessToken("11");
    Assertions.assertThat(accessToken).isNotNull();
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    final WeChatOAuth2AccessToken accessToken = this.mpService.getOAuth2Service().getAccessToken("11");
    final WeChatOAuth2UserInfo userInfo = this.mpService.getOAuth2Service().getUserInfo(accessToken, null);
    Assertions.assertThat(userInfo).isNotNull();
  }

  @Test
  public void testValidateAccessToken() {
    final boolean result = this.mpService.getOAuth2Service().validateAccessToken(new WeChatOAuth2AccessToken());
    Assertions.assertThat(result).isTrue();
  }
}
