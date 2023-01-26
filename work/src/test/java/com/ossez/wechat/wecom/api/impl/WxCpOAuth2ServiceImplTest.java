package com.ossez.wechat.wecom.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.ApiTestModule;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.WxCpOauth2UserInfo;
import com.ossez.wechat.wecom.bean.WxCpUserDetail;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/4/22.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxCpOAuth2ServiceImplTest {
  @Inject
  private WxCpService wxService;

  /**
   * Test get user detail.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserDetail() throws WxErrorException {
    WxCpUserDetail userDetail = this.wxService.getOauth2Service().getUserDetail("b");
    System.out.println(userDetail);
  }

  /**
   * Test get user info.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetUserInfo() throws WxErrorException {
    final WxCpOauth2UserInfo result = this.wxService.getOauth2Service().getUserInfo("abc");
    assertThat(result).isNotNull();
    System.out.println(result);
  }

  /**
   * Test build authorization url.
   */
  @Test
  public void testBuildAuthorizationUrl() {
  }

}
