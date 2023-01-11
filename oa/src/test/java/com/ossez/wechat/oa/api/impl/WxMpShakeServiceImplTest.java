package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.bean.WxMpShakeInfoResult;
import com.ossez.wechat.oa.bean.WxMpShakeQuery;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试摇一摇周边相关的接口
 *
 * @author rememberber
 */
@Test(groups = "userAPI")
@Guice(modules = ApiTestModule.class)
public class WxMpShakeServiceImplTest {
  @Inject
  private WxMpService wxService;

  public void testGetShakeInfo() throws Exception {
    WxMpShakeQuery wxMpShakeQuery = new WxMpShakeQuery();
    wxMpShakeQuery.setTicket("b87db7df490e5cbe4f598272f77f46be");
    wxMpShakeQuery.setNeedPoi(1);
    WxMpShakeInfoResult wxMpShakeInfoResult = this.wxService.getShakeService().getShakeInfo(wxMpShakeQuery);

    System.out.println();
  }

}
