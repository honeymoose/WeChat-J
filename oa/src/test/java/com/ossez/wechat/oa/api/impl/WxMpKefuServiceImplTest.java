package com.ossez.wechat.oa.api.impl;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.bean.kefu.WxMpKefuMessage;
import com.ossez.wechat.oa.bean.kefu.request.WxMpKfAccountRequest;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;

import com.google.inject.Inject;
import com.ossez.wechat.common.api.WxConsts;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfInfo;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfList;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfMsgList;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfOnlineList;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfSessionGetResult;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfSessionList;
import com.ossez.wechat.oa.bean.kefu.result.WxMpKfSessionWaitCaseList;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * 测试客服相关接口
 *
 * @author Binary Wang
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpKefuServiceImplTest {

  @Inject
  protected WeChatOfficialAccountService wxService;

  public void testSendKefuMpNewsMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.KefuMsgType.MPNEWS);
    message.setToUser(configStorage.getOpenid());
    message.setMpNewsMediaId("52R6dL2FxDpM9N1rCY3sYBqHwq-L7K_lz1sPI71idMg");

    boolean result = this.wxService.getKefuService().sendKefuMessage(message);
    Assertions.assertThat(result).isTrue();
  }

  public void testSendKefuMessage() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.KefuMsgType.TEXT);
    message.setToUser(configStorage.getOpenid());
    message.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    boolean result = this.wxService.getKefuService().sendKefuMessage(message);
    Assertions.assertThat(result).isTrue();
  }

  public void testSendKefuMessageWithKfAccount() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpKefuMessage message = new WxMpKefuMessage();
    message.setMsgType(WxConsts.KefuMsgType.TEXT);
    message.setToUser(configStorage.getOpenid());
    message.setKfAccount(configStorage.getKfAccount());
    message.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");

    boolean result = this.wxService.getKefuService().sendKefuMessage(message);
    Assertions.assertThat(result).isTrue();
  }

  public void testKfList() throws WxErrorException {
    WxMpKfList kfList = this.wxService.getKefuService().kfList();
    assertThat(kfList).isNotNull();
    for (WxMpKfInfo k : kfList.getKfList()) {
      System.err.println(k);
    }
  }

  public void testKfOnlineList() throws WxErrorException {
    WxMpKfOnlineList kfOnlineList = this.wxService.getKefuService().kfOnlineList();
    assertThat(kfOnlineList).isNotNull();
    for (WxMpKfInfo k : kfOnlineList.getKfOnlineList()) {
      System.err.println(k);
    }
  }

  @DataProvider
  public Object[][] getKfAccount() {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    return new Object[][]{{configStorage.getKfAccount()}};
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfAccountAdd(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).nickName("我晕").build();
    assertThat(this.wxService.getKefuService().kfAccountAdd(request)).isTrue();
  }

  @Test(dependsOnMethods = {
    "testKfAccountAdd"}, dataProvider = "getKfAccount")
  public void testKfAccountUpdate(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).nickName("我晕").build();
    assertThat(this.wxService.getKefuService().kfAccountUpdate(request)).isTrue();
  }

  @Test(dependsOnMethods = {
    "testKfAccountAdd"}, dataProvider = "getKfAccount")
  public void testKfAccountInviteWorker(String kfAccount) throws WxErrorException {
    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
      .kfAccount(kfAccount).inviteWx("    ").build();
    assertThat(this.wxService.getKefuService().kfAccountInviteWorker(request)).isTrue();
  }

  @Test(dependsOnMethods = {"testKfAccountUpdate", "testKfAccountAdd"}, dataProvider = "getKfAccount")
  public void testKfAccountUploadHeadImg(String kfAccount) throws WxErrorException {
    File imgFile = new File("src\\test\\resources\\mm.jpeg");
    boolean result = this.wxService.getKefuService().kfAccountUploadHeadImg(kfAccount, imgFile);
    Assertions.assertThat(result).isTrue();
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfAccountDel(String kfAccount) throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfAccountDel(kfAccount);
    Assertions.assertThat(result).isTrue();
  }

  @DataProvider
  public Object[][] getKfAccountAndOpenid() {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    return new Object[][]{{configStorage.getKfAccount(), configStorage.getOpenid()}};
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionCreate(String kfAccount, String openid) throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfSessionCreate(openid, kfAccount);
    Assertions.assertThat(result).isTrue();
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionClose(String kfAccount, String openid) throws WxErrorException {
    boolean result = this.wxService.getKefuService().kfSessionClose(openid, kfAccount);
    Assertions.assertThat(result).isTrue();
  }

  @Test(dataProvider = "getKfAccountAndOpenid")
  public void testKfSessionGet(@SuppressWarnings("unused") String kfAccount, String openid) throws WxErrorException {
    WxMpKfSessionGetResult result = this.wxService.getKefuService().kfSessionGet(openid);
    assertThat(result).isNotNull();
    System.err.println(result);
  }

  @Test(dataProvider = "getKfAccount")
  public void testKfSessionList(String kfAccount) throws WxErrorException {
    WxMpKfSessionList result = this.wxService.getKefuService().kfSessionList(kfAccount);
    assertThat(result).isNotNull();
    System.err.println(result);
  }

  @Test
  public void testKfSessionGetWaitCase() throws WxErrorException {
    WxMpKfSessionWaitCaseList result = this.wxService.getKefuService().kfSessionGetWaitCase();
    assertThat(result).isNotNull();
    System.err.println(result);
  }

  @Test
  public void testKfMsgList() throws WxErrorException {
    // Date startTime = DateTime.now().minusDays(1).toDate();
    // Date endTime = DateTime.now().minusDays(0).toDate();
    Date startTime = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
    Date endTime = Date.from(Instant.now());

    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime, 1L, 50);
    assertThat(result).isNotNull();
    System.err.println(result);
  }

  @Test
  public void testKfMsgListAll() throws WxErrorException {
    // Date startTime = DateTime.now().minusDays(1).toDate();
    // Date endTime = DateTime.now().minusDays(0).toDate();
    Date startTime = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
    Date endTime = Date.from(Instant.now());

    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime);
    assertThat(result).isNotNull();
    System.err.println(result);
  }

  @Test
  public void testSendKfTypingState() throws WxErrorException {
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    boolean result = this.wxService.getKefuService().sendKfTypingState(configStorage.getOpenid(), "Typing");
    Assertions.assertThat(result).isTrue();
  }
}
