package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.bean.template.*;
import org.apache.commons.lang3.RandomStringUtils;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * <pre>
 * Created by Binary Wang on 2016-10-14.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Guice(modules = ApiTestModule.class)
public class WxMpTemplateMsgServiceImplTest {
  @Inject
  protected WeChatOfficialAccountService wxService;

  @Test(invocationCount = 5, threadPoolSize = 3)
  public void testSendTemplateMsg() throws WxErrorException {
    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS");
    TestConfigStorage configStorage = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpTemplateMessage templateMessage = WxMpTemplateMessage.builder()
      .toUser(configStorage.getOpenid())
      .templateId(configStorage.getTemplateId())
      .url(" ")
      .build();

    templateMessage.addData(new WxMpTemplateData("first", dateFormat.format(new Date()), "#FF00FF"))
      .addData(new WxMpTemplateData("remark", RandomStringUtils.randomAlphanumeric(100), "#FF00FF"));
    String msgId = this.wxService.getTemplateMsgService().sendTemplateMsg(templateMessage);
    Assert.assertNotNull(msgId);
    System.out.println(msgId);
  }

  @Test
  public void testGetIndustry() throws Exception {
    final WxMpTemplateIndustry industry = this.wxService.getTemplateMsgService().getIndustry();
    Assert.assertNotNull(industry);
    System.out.println(industry);
  }

  @Test
  public void testSetIndustry() throws Exception {
    WxMpTemplateIndustry industry = new WxMpTemplateIndustry(WxMpTemplateIndustryEnum.findByCode(29),
      WxMpTemplateIndustryEnum.findByCode(8));
    boolean result = this.wxService.getTemplateMsgService().setIndustry(industry);
    Assert.assertTrue(result);
  }

  @Test
  public void testAddTemplate() throws Exception {
    String result = this.wxService.getTemplateMsgService().addTemplate("TM00015");
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testGetAllPrivateTemplate() throws Exception {
    List<WxMpTemplate> result = this.wxService.getTemplateMsgService().getAllPrivateTemplate();
    Assert.assertNotNull(result);
    System.err.println(result);
  }

  @Test
  public void testDelPrivateTemplate() throws Exception {
    String templateId = "RPcTe7-4BkU5A2J3imC6W0b4JbjEERcJg0whOMKJKIc";
    boolean result = this.wxService.getTemplateMsgService().delPrivateTemplate(templateId);
    Assert.assertTrue(result);
  }

}
