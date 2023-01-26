package com.ossez.wechat.wecom.api.impl;

import com.google.inject.Inject;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.ApiTestModule;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.message.WxCpMessage;
import com.ossez.wechat.wecom.bean.message.WxCpMessageSendResult;
import com.ossez.wechat.wecom.bean.taskcard.TaskCardButton;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.Arrays;

import static org.testng.Assert.assertNotNull;

/**
 * 测试任务卡片服务
 *
 * @author <a href="https://github.com/domainname">Jeff</a> created on  2019-05-16
 */
@Guice(modules = ApiTestModule.class)
public class WxCpTaskCardServiceImplTest {

  @Inject
  private WxCpService wxCpService;

  /**
   * Test send task card.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testSendTaskCard() throws WxErrorException {
    TaskCardButton btn1 = TaskCardButton.builder()
      .key("key1")
      .name("同意")
      .replaceName("已同意")
      .bold(true)
      .build();
    TaskCardButton btn2 = TaskCardButton.builder()
      .key("key2")
      .name("拒绝")
      .replaceName("已拒绝")
      .color("red")
      .build();
    WxCpMessage message = WxCpMessage.TASKCARD()
      .toUser("jeff|mr.t")
      .title("有一个待审批的请求")
      .description("申请：购买图书\n金额：100 元")
      .taskId("task_1")
      .url("http://www.qq.com")
      .buttons(Arrays.asList(btn1, btn2))
      .build();

    WxCpMessageSendResult messageSendResult = this.wxCpService.getMessageService().send(message);
    assertNotNull(messageSendResult);
    System.out.println(messageSendResult);
    System.out.println(messageSendResult.getInvalidPartyList());
    System.out.println(messageSendResult.getInvalidUserList());
    System.out.println(messageSendResult.getInvalidTagList());
  }

  /**
   * Test update.
   *
   * @throws Exception the exception
   */
  @Test
  public void testUpdate() throws Exception {
    wxCpService.getTaskCardService().update(Arrays.asList("jeff", "mr.t"), "task_1", "key1");
  }

}
