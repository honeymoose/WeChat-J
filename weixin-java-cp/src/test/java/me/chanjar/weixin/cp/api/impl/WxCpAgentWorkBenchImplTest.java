package me.chanjar.weixin.cp.api.impl;

import com.google.inject.Inject;
import me.chanjar.weixin.common.exception.WxErrorException;
import me.chanjar.weixin.cp.api.ApiTestModule;
import me.chanjar.weixin.cp.api.WxCpService;
import me.chanjar.weixin.cp.bean.WxCpAgentWorkBench;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchKeyData;
import me.chanjar.weixin.cp.bean.workbench.WorkBenchList;
import me.chanjar.weixin.cp.constant.WxCpConsts;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * The type Wx cp agent work bench impl test.
 *
 * @author songshiyu  created on  : create in 10:31 2020/9/29
 * @description: 测试工作台服务
 */
@Guice(modules = ApiTestModule.class)
public class WxCpAgentWorkBenchImplTest {

  @Inject
  private WxCpService wxCpService;

  /**
   * Test template set.
   *
   * @param template the template
   * @throws WxErrorException the wx error exception
   */
  @Test(dataProvider = "template")
  public void testTemplateSet(WxCpAgentWorkBench template) throws WxErrorException {
    this.wxCpService.getWorkBenchService().setWorkBenchTemplate(template);
  }

  /**
   * Test template get.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testTemplateGet() throws WxErrorException {
    String result = this.wxCpService.getWorkBenchService().getWorkBenchTemplate(1000011L);
    System.out.println("获取工作台模板设置：" + result);
  }

  /**
   * Test user data set.
   *
   * @param userDatas the user datas
   * @throws WxErrorException the wx error exception
   */
  @Test(dataProvider = "userDatas")
  public void testUserDataSet(WxCpAgentWorkBench userDatas) throws WxErrorException {
    this.wxCpService.getWorkBenchService().setWorkBenchData(userDatas);
  }

  /**
   * Template object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] template() {
    return new Object[][]{
      {WxCpAgentWorkBench.builder()
        .agentId(1000011L)
        .replaceUserData(true)
        .type(WxCpConsts.WorkBenchType.IMAGE)
        .url("http://www.qq.com")
        .jumpUrl("http://www.qq.com")
        .pagePath("pages/index")
        .build()
      },
    };
  }

  /**
   * User datas object [ ] [ ].
   *
   * @return the object [ ] [ ]
   */
  @DataProvider
  public Object[][] userDatas() {
    return new Object[][]{
      {WxCpAgentWorkBench.builder()
        .agentId(1000011L)
        .userId("HaHa")
        .type(WxCpConsts.WorkBenchType.IMAGE)
        .url("http://www.qq.com")
        .jumpUrl("http://www.qq.com")
        .pagePath("pages/index")
        .build()
      },
    };
  }

  /**
   * Test key data template set.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testKeyDataTemplateSet() throws WxErrorException {
    WxCpAgentWorkBench template = new WxCpAgentWorkBench();
    template.setAgentId(1000011L);
    template.setType(WxCpConsts.WorkBenchType.KEYDATA);
    List<WorkBenchKeyData> workBenchKeyDataList = new ArrayList<>();
    for (int i = 1; i < 4; i++) {
      WorkBenchKeyData workBenchKeyData = new WorkBenchKeyData();
      workBenchKeyData.setKey("审批" + i);
      workBenchKeyData.setData(String.valueOf(i));
      workBenchKeyData.setJumpUrl("http://www.qq.com");
      workBenchKeyData.setPagePath("pages/index");
      workBenchKeyDataList.add(workBenchKeyData);
    }
    template.setKeyDataList(workBenchKeyDataList);
    template.setReplaceUserData(true);
    this.wxCpService.getWorkBenchService().setWorkBenchTemplate(template);
  }

  /**
   * Test key data user data set.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testKeyDataUserDataSet() throws WxErrorException {
    WxCpAgentWorkBench template = new WxCpAgentWorkBench();
    template.setAgentId(1000011L);
    template.setUserId("HaHa");
    template.setType(WxCpConsts.WorkBenchType.KEYDATA);
    List<WorkBenchKeyData> workBenchKeyDataList = new ArrayList<>();
    WorkBenchKeyData workBenchKeyData = new WorkBenchKeyData();
    workBenchKeyData.setKey("待审批");
    workBenchKeyData.setData("2");
    workBenchKeyData.setJumpUrl("http://www.qq.com");
    workBenchKeyData.setPagePath("pages/index");
    workBenchKeyDataList.add(workBenchKeyData);
    template.setKeyDataList(workBenchKeyDataList);
    this.wxCpService.getWorkBenchService().setWorkBenchTemplate(template);
  }

  /**
   * Test list template set.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testListTemplateSet() throws WxErrorException {
    WxCpAgentWorkBench template = new WxCpAgentWorkBench();
    template.setAgentId(1000011L);
    template.setType(WxCpConsts.WorkBenchType.LIST);
    List<WorkBenchList> workBenchListArray = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      WorkBenchList workBenchlist = new WorkBenchList();
      workBenchlist.setTitle("测试" + i);
      workBenchlist.setJumpUrl("http://www.qq.com");
      workBenchlist.setPagePath("pages/index");
      workBenchListArray.add(workBenchlist);
    }
    template.setLists(workBenchListArray);
    template.setReplaceUserData(true);
    this.wxCpService.getWorkBenchService().setWorkBenchTemplate(template);
  }


}
