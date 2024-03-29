package com.ossez.wechat.wecom.tp.service.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.bean.WxCpTpProlongTryResult;
import com.ossez.wechat.wecom.config.WxCpTpConfigStorage;
import com.ossez.wechat.wecom.config.impl.WxCpTpDefaultConfigImpl;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.tp.service.WxCpTpEditionService;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.Test;

import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertNotNull;

/**
 * 应用版本付费版本相关接口测试
 */
public class WxCpTpEditionServiceImplTest {

  @Mock
  private WxCpTpServiceApacheHttpClientImpl wxCpTpService;

  private WxCpTpConfigStorage configStorage;

  private WxCpTpEditionService wxCpTpEditionService;

  /**
   * Sets up.
   */
  @BeforeClass
  public void setUp() {
    MockitoAnnotations.initMocks(this);
    configStorage = new WxCpTpDefaultConfigImpl();
    when(wxCpTpService.getWxCpTpConfigStorage()).thenReturn(configStorage);
    wxCpTpEditionService = new WxCpTpEditionServiceImpl(wxCpTpService);
  }


  /**
   * 延长试用期
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testProlongTry() throws WxErrorException {

    String buyerCorpId = "wx7da9abf8ac62baaa";
    Integer prolongDays = 7;
    String appId = "1";

    Long tryEndTime = 1565152189L;

    String result = "" +
      "  {\n" +
      "    \"errcode\" : 0,\n" +
      "    \"errmsg\" : \"ok\",\n" +
      "    \"try_end_time\" : 1565152189\n" +
      "  }";

    String url = configStorage.getApiUrl(WxCpApiPathConsts.Tp.PROLONG_TRY);
    when(wxCpTpService.post(eq(url), any(String.class))).thenReturn(result);

    final WxCpTpProlongTryResult prolongTryResult = wxCpTpEditionService.prolongTry(buyerCorpId, prolongDays, appId);

    assertNotNull(prolongTryResult);

    assertEquals(prolongTryResult.getTryEndTime(), tryEndTime);

  }
}
