package com.ossez.wechat.qidian.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.qidian.api.test.ApiTestModule;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.qidian.api.WxQidianService;
import com.ossez.wechat.qidian.bean.call.GetSwitchBoardListResponse;
import com.ossez.wechat.qidian.bean.dial.IVRDialRequest;
import com.ossez.wechat.qidian.bean.dial.IVRDialResponse;
import com.ossez.wechat.qidian.bean.dial.IVRListResponse;
import com.ossez.wechat.qidian.bean.dial.Ivr;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.List;
import java.util.Optional;

@Test
@Guice(modules = ApiTestModule.class)
@Slf4j
public class WxQidianDialServiceImplTest {
  @Inject
  private WxQidianService wxService;

  @Test
  public void dial() throws WxErrorException {
    // ivr
    IVRListResponse iVRListResponse = this.wxService.getDialService().getIVRList();
    Assert.assertEquals(iVRListResponse.getErrcode(), new Integer(0));
    log.info("ivr size:" + iVRListResponse.getNode().size());
    Optional<Ivr> optional = iVRListResponse.getNode().stream().filter(o -> o.getIvr_name().equals("自动接听需求测试"))
        .findFirst();
    Assert.assertTrue(optional.isPresent());
    Ivr ivr = optional.get();
    String ivr_id = ivr.getIvr_id();
    // ivr_id = "433";

    // switch
    GetSwitchBoardListResponse getSwitchBoardListResponse = this.wxService.getCallDataService().getSwitchBoardList();
    Assert.assertEquals(getSwitchBoardListResponse.getErrcode(), new Integer(0));
    log.info("switch size:" + getSwitchBoardListResponse.getData().switchBoards().size());
    List<String> switchBoards = getSwitchBoardListResponse.getData().switchBoards();

    // ivrdial
    IVRDialRequest ivrDial = new IVRDialRequest();
    ivrDial.setPhone_number("18434399105");
    // ivrDial.setPhone_number("13811768266");
    ivrDial.setIvr_id(ivr_id);
    ivrDial.setCorp_phone_list(switchBoards);
    IVRDialResponse ivrDialResponse = this.wxService.getDialService().ivrDial(ivrDial);
    Assert.assertEquals(ivrDialResponse.getCode(), new Integer(0));
    log.info(ivrDialResponse.getCallid());
  }
}
