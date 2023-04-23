package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.guide.WxMpGuideBuyerResp;
import com.ossez.wechat.oa.bean.guide.WxMpGuideTagInfo;
import lombok.extern.slf4j.Slf4j;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;

import java.util.ArrayList;
import java.util.List;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/13/013
 */

@TestInstance(TestInstance.Lifecycle.PER_CLASS)
@Slf4j
public class WxMpGuideTagServiceImplTest {
  
  @Inject
  protected WeChatOfficialAccountService wxService;

  /**
   * 顾问微信号 guide_account
   */
  private static final String ACCOUNT = "sxc_Warm";

  @Test
  public void testNewGuideTagOption() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类一");
    list.add("分类二");
    list.add("分类三");
    this.wxService.getGuideTagService().newGuideTagOption("A组", list);
  }

  @Test
  public void testDelGuideTagOption() throws WxErrorException {
    this.wxService.getGuideTagService().delGuideTagOption("A组");
  }

  @Test
  public void testAddGuideTagOption() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类四");
    this.wxService.getGuideTagService().addGuideTagOption("A组", list);
  }

  @Test
  public void testGetGuideTagOption() throws WxErrorException {
    List<WxMpGuideTagInfo> guideTagOption = this.wxService.getGuideTagService().getGuideTagOption();
    Assertions.assertThat(guideTagOption).isNotNull();
  }

  @Test
  public void testAddGuideBuyerTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    list.add("oqlk8vybPMWapMwOfFTFVYqWpGM0");
    List<WxMpGuideBuyerResp> wxMpGuideBuyerResps = this.wxService.getGuideTagService().addGuideBuyerTag(ACCOUNT, null, "分类一", list);
    Assertions.assertThat(wxMpGuideBuyerResps).isNotNull();
  }

  @Test
  public void testAddGuideBuyerTagOnce() throws WxErrorException {
    this.wxService.getGuideTagService().addGuideBuyerTag(ACCOUNT, null, "分类二", "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testGetGuideBuyerTag() throws WxErrorException {
    List<String> guideBuyerTag = this.wxService.getGuideTagService().getGuideBuyerTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", true);
    Assertions.assertThat(guideBuyerTag).isNotNull();
  }

  @Test
  public void testQueryGuideBuyerByTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("分类一");
    List<String> list1 = this.wxService.getGuideTagService().queryGuideBuyerByTag(ACCOUNT, null, 0, list);
    Assertions.assertThat(list1).isNotNull();
  }

  @Test
  public void testdelGuideBuyerTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("oqlk8v0uTJgRnn5eEskNruD4-bc8");
    list.add("oqlk8vybPMWapMwOfFTFVYqWpGM0");
    List<WxMpGuideBuyerResp> respList = this.wxService.getGuideTagService().delGuideBuyerTag(ACCOUNT, null, "分类一", list);
    Assertions.assertThat(respList).isNotNull();
  }

  @Test
  public void testDelGuideBuyerTagOnce() throws WxErrorException {
    this.wxService.getGuideTagService().delGuideBuyerTag(ACCOUNT, null, "分类一", "oqlk8v0uTJgRnn5eEskNruD4-bc8");
  }

  @Test
  public void testAddGuideBuyerDisplayTag() throws WxErrorException {
    List<String> list = new ArrayList<>();
    list.add("自定义信息1");
    list.add("自定义信息2");
    this.wxService.getGuideTagService().addGuideBuyerDisplayTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8", list);
  }

  @Test
  public void testGetGuideBuyerDisplayTag() throws WxErrorException {
    List<String> list = this.wxService.getGuideTagService().getGuideBuyerDisplayTag(ACCOUNT, null, "oqlk8v0uTJgRnn5eEskNruD4-bc8");
    Assertions.assertThat(list).isNotNull();
  }

}
