package com.ossez.wechat.oa.api.impl;

import com.google.common.collect.Lists;
import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.bean.marketing.WxMpUserAction;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-07-14
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMarketingServiceImplTest {
  @Inject
  protected WxMpService wxService;

  @Test
  public void testAddUserActionSets() {
  }

  @Test
  public void testGetUserActionSets() {
  }

  @Test
  public void testAddUserAction() throws WxErrorException {
    this.wxService.getMarketingService().addUserAction(Lists.newArrayList(new WxMpUserAction()));
  }

  @Test
  public void testGetAdLeads() {
  }
}
