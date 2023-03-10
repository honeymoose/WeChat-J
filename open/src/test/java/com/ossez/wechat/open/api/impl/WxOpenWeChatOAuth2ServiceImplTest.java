package com.ossez.wechat.open.api.impl;

import com.ossez.wechat.common.model.WeChatOAuth2AccessToken;
import com.ossez.wechat.common.exception.WxErrorException;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

/**
 * 单元测试.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-10-19
 */
public class WxOpenWeChatOAuth2ServiceImplTest {
  private final WeChatOAuth2Service service = new WeChatOAuth2Service("121", "", "123", "");

  @BeforeTest
  public void init() {
    this.service.setWxOpenConfigStorage(new WxOpenInMemoryConfigStorage());
  }

  @Test
  public void testBuildAuthorizationUrl() {
    this.service.buildAuthorizationUrl("", "", "");
  }

  @Test
  public void testGetAccessToken() throws WxErrorException {
    this.service.getAccessToken("a");
  }

  @Test
  public void testTestGetAccessToken() throws WxErrorException {
    this.service.getAccessToken("", "", "");
  }

  @Test
  public void testRefreshAccessToken() throws WxErrorException {
    this.service.refreshAccessToken("");
  }

  @Test
  public void testGetUserInfo() throws WxErrorException {
    this.service.getUserInfo(new WeChatOAuth2AccessToken(), "");
  }

  @Test
  public void testValidateAccessToken() {
    this.service.validateAccessToken(new WeChatOAuth2AccessToken());
  }
}
