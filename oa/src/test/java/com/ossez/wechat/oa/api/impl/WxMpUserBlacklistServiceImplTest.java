package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.bean.result.WxMpUserBlacklistGetResult;
import org.testng.*;
import org.testng.annotations.*;

import javax.inject.Inject;
import java.util.ArrayList;
import java.util.List;

/**
 * @author miller
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpUserBlacklistServiceImplTest {
  @Inject
  protected WeChatOfficialAccountService wxService;

  @Test
  public void testGetBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    WxMpUserBlacklistGetResult wxMpUserBlacklistGetResult = this.wxService.getBlackListService().getBlacklist(configStorage.getOpenid());
    Assert.assertNotNull(wxMpUserBlacklistGetResult);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getCount() == -1);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getTotal() == -1);
    Assert.assertFalse(wxMpUserBlacklistGetResult.getOpenidList().size() == -1);
    System.out.println(wxMpUserBlacklistGetResult);
  }

  @Test
  public void testPushToBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    List<String> openidList = new ArrayList<>();
    openidList.add(configStorage.getOpenid());
    this.wxService.getBlackListService().pushToBlacklist(openidList);
  }

  @Test
  public void testPullFromBlacklist() throws Exception {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService
      .getWxMpConfigStorage();
    List<String> openidList = new ArrayList<>();
    openidList.add(configStorage.getOpenid());
    this.wxService.getBlackListService().pullFromBlacklist(openidList);
  }

}
