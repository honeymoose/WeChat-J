package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.DataStructureException;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.res.UserSummaryResponse;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatDataCubeService;
import com.ossez.wechat.oa.api.test.TestBase;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Test for datacube API
 * @author YuCheng
 */
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
public class DataCubeServiceTest extends TestBase {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);

    @Inject
    protected TestConfigStorage testConfigStorage;

    @Inject
    protected WeChatDataCubeService weChatDataCubeService;

    @Test
    public void testGetUserSummarye() throws WxErrorException, DataStructureException {
        UserSummaryResponse userSummaryResponse = weChatDataCubeService.getUserSummary(LocalDateTime.now().minusDays(4), LocalDateTime.now().minusDays(1));

        assertThat(userSummaryResponse).isNotNull();
        assertThat(userSummaryResponse.getUserDataList().size()).isEqualTo(4);
        assertThat(userSummaryResponse.getUserDataList().get(0).getCancelUser()).isGreaterThanOrEqualTo(0);

    }

    @Test
    public void testGetUserCumulate() throws WxErrorException {
        UserSummaryResponse userSummaryResponse = weChatDataCubeService.getUserCumulate(LocalDateTime.now().minusDays(4), LocalDateTime.now().minusDays(1));

        assertThat(userSummaryResponse).isNotNull();
        assertThat(userSummaryResponse.getUserDataList().size()).isEqualTo(4);
        assertThat(userSummaryResponse.getUserDataList().get(0).getCumulateUser()).isGreaterThanOrEqualTo(0);

    }
//
//  @Test(dataProvider = "oneDay")
//  public void testGetArticleSummary(Date date) throws WxErrorException {
//    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
//      .getArticleSummary(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  /**
//   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
//   */
//  @Test(dataProvider = "oneDay")
//  public void testGetArticleTotal(Date date) throws WxErrorException {
//    List<WxDataCubeArticleTotal> results = this.wxService.getDataCubeService()
//      .getArticleTotal(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "threeDays")
//  public void testGetUserRead(Date beginDate, Date endDate)
//    throws WxErrorException {
//    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
//      .getUserRead(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "oneDay")
//  public void testGetUserReadHour(Date date) throws WxErrorException {
//    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
//      .getUserReadHour(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "sevenDays")
//  public void testGetUserShare(Date beginDate, Date endDate)
//    throws WxErrorException {
//    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
//      .getUserShare(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "oneDay")
//  public void testGetUserShareHour(Date date) throws WxErrorException {
//    List<WxDataCubeArticleResult> results = this.wxService.getDataCubeService()
//      .getUserShareHour(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "sevenDays")
//  public void testGetUpstreamMsg(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsg(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "oneDay")
//  public void testGetUpstreamMsgHour(Date date) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgHour(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "thirtyDays")
//  public void testGetUpstreamMsgWeek(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgWeek(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  /**
//   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
//   */
//  @Test(dataProvider = "thirtyDays")
//  public void testGetUpstreamMsgMonth(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgMonth(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "fifteenDays")
//  public void testGetUpstreamMsgDist(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgDist(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "thirtyDays")
//  public void testGetUpstreamMsgDistWeek(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgDistWeek(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  /**
//   * TODO 该接口，暂时没找到有数据的日期，无法测试验证
//   */
//  @Test(dataProvider = "thirtyDays")
//  public void testGetUpstreamMsgDistMonth(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeMsgResult> results = this.wxService.getDataCubeService()
//      .getUpstreamMsgDistMonth(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "thirtyDays")
//  public void testGetInterfaceSummary(Date beginDate, Date endDate) throws WxErrorException {
//    List<WxDataCubeInterfaceResult> results = this.wxService.getDataCubeService()
//      .getInterfaceSummary(beginDate, endDate);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
//
//  @Test(dataProvider = "oneDay")
//  public void testGetInterfaceSummaryHour(Date date) throws WxErrorException {
//    List<WxDataCubeInterfaceResult> results = this.wxService.getDataCubeService()
//      .getInterfaceSummaryHour(date, date);
//    Assert.assertNotNull(results);
//    System.out.println(results);
//  }
}
