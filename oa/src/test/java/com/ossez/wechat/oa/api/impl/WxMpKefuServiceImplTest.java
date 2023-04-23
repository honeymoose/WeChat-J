package com.ossez.wechat.oa.api.impl;

import java.io.File;
import java.time.Instant;
import java.time.temporal.ChronoUnit;
import java.util.Date;

import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.common.constant.WeChatConstant.MsgType;
import com.ossez.wechat.common.model.req.CustomMessage;
import com.ossez.wechat.common.model.req.CustomMessage.KfText;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMsgService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountApi;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestBase;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.bean.kefu.WxMpKefuMessage;
import com.ossez.wechat.oa.bean.kefu.request.WxMpKfAccountRequest;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;


import com.google.inject.Inject;
import com.ossez.wechat.common.constant.WeChatConstant;
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
@org.junit.jupiter.api.TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class WxMpKefuServiceImplTest extends TestBase {
    @Inject
    protected TestConfigStorage testConfigStorage;

    @Inject
    protected WeChatMsgService weChatMsgService;


//  public void testSendKefuMpNewsMessage() throws WxErrorException {
//    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
//    WxMpKefuMessage message = new WxMpKefuMessage();
//    message.setMsgType(WeChatConstant.MsgType.MPNEWS);
//    message.setToUser(configStorage.getOpenid());
//    message.setMpNewsMediaId("52R6dL2FxDpM9N1rCY3sYBqHwq-L7K_lz1sPI71idMg");
//
//    boolean result = this.wxService.getKefuService().sendKefuMessage(message);
//    Assertions.assertThat(result).isTrue();
//  }


    @Test
    void testSendKefuMessage() throws WxErrorException {


        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("欢迎使用 WeChat-J API " + System.lineSeparator());
        stringBuilder.append("回车换行测试 " + System.lineSeparator());
        stringBuilder.append("超链接:<a href=\"https://www.ossez.com/\">OSSEZ.COM</a>");

        CustomMessage.KfText kfText = new CustomMessage.KfText("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"https://www.ossez.com/\">OSSEZ.COM</a>");
        CustomMessage customMessage = new CustomMessage();
        customMessage.setToUser(testConfigStorage.getOpenid());
        customMessage.setMsgType(MsgType.TEXT);
        customMessage.setText(new KfText(stringBuilder.toString()));


        String result = weChatMsgService.sendMessage(customMessage);
//    Assertions.assertThat(result).isTrue();
    }

//  public void testSendKefuMessageWithKfAccount() throws WxErrorException {
//    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
//    WxMpKefuMessage message = new WxMpKefuMessage();
//    message.setMsgType(WeChatConstant.MsgType.TEXT);
//    message.setToUser(configStorage.getOpenid());
//    message.setKfAccount(configStorage.getKfAccount());
//    message.setContent("欢迎欢迎，热烈欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
//
//    boolean result = this.wxService.getKefuService().sendKefuMessage(message);
//    Assertions.assertThat(result).isTrue();
//  }
//
//  public void testKfList() throws WxErrorException {
//    WxMpKfList kfList = this.wxService.getKefuService().kfList();
//    assertThat(kfList).isNotNull();
//    for (WxMpKfInfo k : kfList.getKfList()) {
//      System.err.println(k);
//    }
//  }
//
//  public void testKfOnlineList() throws WxErrorException {
//    WxMpKfOnlineList kfOnlineList = this.wxService.getKefuService().kfOnlineList();
//    assertThat(kfOnlineList).isNotNull();
//    for (WxMpKfInfo k : kfOnlineList.getKfOnlineList()) {
//      System.err.println(k);
//    }
//  }
//
//  @DataProvider
//  public Object[][] getKfAccount() {
//    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
//    return new Object[][]{{configStorage.getKfAccount()}};
//  }
//
//  @Test(dataProvider = "getKfAccount")
//  public void testKfAccountAdd(String kfAccount) throws WxErrorException {
//    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
//      .kfAccount(kfAccount).nickName("我晕").build();
//    assertThat(this.wxService.getKefuService().kfAccountAdd(request)).isTrue();
//  }
//
//  @Test(dependsOnMethods = {
//    "testKfAccountAdd"}, dataProvider = "getKfAccount")
//  public void testKfAccountUpdate(String kfAccount) throws WxErrorException {
//    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
//      .kfAccount(kfAccount).nickName("我晕").build();
//    assertThat(this.wxService.getKefuService().kfAccountUpdate(request)).isTrue();
//  }
//
//  @Test(dependsOnMethods = {
//    "testKfAccountAdd"}, dataProvider = "getKfAccount")
//  public void testKfAccountInviteWorker(String kfAccount) throws WxErrorException {
//    WxMpKfAccountRequest request = WxMpKfAccountRequest.builder()
//      .kfAccount(kfAccount).inviteWx("    ").build();
//    assertThat(this.wxService.getKefuService().kfAccountInviteWorker(request)).isTrue();
//  }
//
//  @Test(dependsOnMethods = {"testKfAccountUpdate", "testKfAccountAdd"}, dataProvider = "getKfAccount")
//  public void testKfAccountUploadHeadImg(String kfAccount) throws WxErrorException {
//    File imgFile = new File("src\\test\\resources\\mm.jpeg");
//    boolean result = this.wxService.getKefuService().kfAccountUploadHeadImg(kfAccount, imgFile);
//    Assertions.assertThat(result).isTrue();
//  }
//
//  @Test(dataProvider = "getKfAccount")
//  public void testKfAccountDel(String kfAccount) throws WxErrorException {
//    boolean result = this.wxService.getKefuService().kfAccountDel(kfAccount);
//    Assertions.assertThat(result).isTrue();
//  }
//
//  @DataProvider
//  public Object[][] getKfAccountAndOpenid() {
//    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
//    return new Object[][]{{configStorage.getKfAccount(), configStorage.getOpenid()}};
//  }
//
//  @Test(dataProvider = "getKfAccountAndOpenid")
//  public void testKfSessionCreate(String kfAccount, String openid) throws WxErrorException {
//    boolean result = this.wxService.getKefuService().kfSessionCreate(openid, kfAccount);
//    Assertions.assertThat(result).isTrue();
//  }
//
//  @Test(dataProvider = "getKfAccountAndOpenid")
//  public void testKfSessionClose(String kfAccount, String openid) throws WxErrorException {
//    boolean result = this.wxService.getKefuService().kfSessionClose(openid, kfAccount);
//    Assertions.assertThat(result).isTrue();
//  }
//
//  @Test(dataProvider = "getKfAccountAndOpenid")
//  public void testKfSessionGet(@SuppressWarnings("unused") String kfAccount, String openid) throws WxErrorException {
//    WxMpKfSessionGetResult result = this.wxService.getKefuService().kfSessionGet(openid);
//    assertThat(result).isNotNull();
//    System.err.println(result);
//  }
//
//  @Test(dataProvider = "getKfAccount")
//  public void testKfSessionList(String kfAccount) throws WxErrorException {
//    WxMpKfSessionList result = this.wxService.getKefuService().kfSessionList(kfAccount);
//    assertThat(result).isNotNull();
//    System.err.println(result);
//  }
//
//  @Test
//  public void testKfSessionGetWaitCase() throws WxErrorException {
//    WxMpKfSessionWaitCaseList result = this.wxService.getKefuService().kfSessionGetWaitCase();
//    assertThat(result).isNotNull();
//    System.err.println(result);
//  }
//
//  @Test
//  public void testKfMsgList() throws WxErrorException {
//    // Date startTime = DateTime.now().minusDays(1).toDate();
//    // Date endTime = DateTime.now().minusDays(0).toDate();
//    Date startTime = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
//    Date endTime = Date.from(Instant.now());
//
//    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime, 1L, 50);
//    assertThat(result).isNotNull();
//    System.err.println(result);
//  }
//
//  @Test
//  public void testKfMsgListAll() throws WxErrorException {
//    // Date startTime = DateTime.now().minusDays(1).toDate();
//    // Date endTime = DateTime.now().minusDays(0).toDate();
//    Date startTime = Date.from(Instant.now().minus(1, ChronoUnit.DAYS));
//    Date endTime = Date.from(Instant.now());
//
//    WxMpKfMsgList result = this.wxService.getKefuService().kfMsgList(startTime, endTime);
//    assertThat(result).isNotNull();
//    System.err.println(result);
//  }
//
//  @Test
//  public void testSendKfTypingState() throws WxErrorException {
//    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
//    boolean result = this.wxService.getKefuService().sendKfTypingState(configStorage.getOpenid(), "Typing");
//    Assertions.assertThat(result).isTrue();
//  }
}

