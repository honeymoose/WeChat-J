package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMassed;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMassedInfo;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMaterialInfo;
import org.assertj.core.api.Assertions;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/13/013
 */

@Guice(modules = ApiTestModule.class)
public class WxMpGuideMassedJobServiceImplTest {
  @Inject
  protected WeChatOfficialAccountService wxService;

  /**
   * 顾问微信号 guide_account
   */
  private static final String ACCOUNT = "sxc_Warm";

  @Test
  public void testAddGuideMassedJob() throws WxErrorException {
    List<String> userOpenId = new ArrayList<>();
    userOpenId.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    List<WxMpGuideMaterialInfo> list = new ArrayList<>();
    list.add(WxMpGuideMaterialInfo.builder().type(1).word("文字素材测试").build());
    list.add(WxMpGuideMaterialInfo.builder().type(3).mediaId("qDrCfXeDorLgy83d8h6VzVip9s6omPXF_2ILuoke1j0sY4bSFVaA8lkGzUaznU9e").build()); //图片素材
    list.add(WxMpGuideMaterialInfo.builder().type(49).mediaId("qDrCfXeDorLgy83d8h6VzVip9s6omPXF_2ILuoke1j0sY4bSFVaA8lkGzUaznU9e").title("小程序标题").path("pages/login-type/index.html").appId("wx4f793c04fd3be5a8").build()); //图片素材
    WxMpGuideMassed wxMpGuideMassed = this.wxService.getGuideMassedJobService().addGuideMassedJob(ACCOUNT, null, "群发任务", "群发任务备注", System.currentTimeMillis() / 1000, userOpenId, list);
    assertThat(wxMpGuideMassed).isNotNull();
  }

  @Test
  public void testGetGuideMassedJobList() throws WxErrorException {
    List<WxMpGuideMassedInfo> guideMassedJobList = this.wxService.getGuideMassedJobService().getGuideMassedJobList(ACCOUNT, null, null, null, null);
    Assertions.assertThat(guideMassedJobList).isNotNull();
  }

  @Test
  public void testGetGuideMassedJob() throws WxErrorException {
    WxMpGuideMassedInfo guideMassedJob = this.wxService.getGuideMassedJobService().getGuideMassedJob("1867407932930228228");
    assertThat(guideMassedJob).isNotNull();
  }

  @Test
  public void testUpdateGuideMassedJob() throws WxErrorException {
    this.wxService.getGuideMassedJobService().updateGuideMassedJob("1867407932930228228", "修改群发任务", null, null, null, null);
  }

  @Test
  public void testCancelGuideMassedJob() throws WxErrorException {
    this.wxService.getGuideMassedJobService().cancelGuideMassedJob("1867407932930228228");
  }
}
