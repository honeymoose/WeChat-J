package com.ossez.wechat.wecom.api.impl;

import com.google.inject.Inject;
import com.ossez.wechat.common.enums.WeChatErrorCode;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.wecom.api.ApiTestModule;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.config.WxCpConfigStorage;
import com.ossez.wechat.wecom.config.impl.WxCpDefaultConfigImpl;
import org.assertj.core.api.Assertions;
import org.mockito.Mockito;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.atomic.AtomicInteger;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.mock;

/**
 * <pre>
 *  Created by BinaryWang on 2019/3/31.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class BaseWxCpServiceImplTest {
  /**
   * The Wx service.
   */
  @Inject
  protected WxCpService wxService;

  /**
   * Test get agent jsapi ticket.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetAgentJsapiTicket() throws WxErrorException {
    assertThat(this.wxService.getAgentJsapiTicket()).isNotEmpty();
    assertThat(this.wxService.getAgentJsapiTicket(true)).isNotEmpty();
  }

  /**
   * Test js code 2 session.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testJsCode2Session() throws WxErrorException {
    Assertions.assertThat(this.wxService.jsCode2Session("111")).isNotNull();
  }

  /**
   * Test get provider token.
   *
   * @throws WxErrorException the wx error exception
   */
  @Test
  public void testGetProviderToken() throws WxErrorException {
    Assertions.assertThat(this.wxService.getProviderToken("111", "123")).isNotNull();
  }


  /**
   * Test execute auto refresh token.
   *
   * @throws WxErrorException the wx error exception
   * @throws IOException      the io exception
   */
  @Test
  public void testExecuteAutoRefreshToken() throws WxErrorException, IOException {
    //测试access token获取时的重试机制
    WxCpDefaultConfigImpl config = new WxCpDefaultConfigImpl();
    BaseWxCpServiceImpl service = new BaseWxCpServiceImpl() {
      @Override
      public Object getRequestHttpClient() {
        return null;
      }

      @Override
      public Object getRequestHttpProxy() {
        return null;
      }

      @Override
      public HttpType getRequestType() {
        return null;
      }

      @Override
      public String getAccessToken(boolean forceRefresh) throws WxErrorException {
        return "模拟一个过期的access token:" + System.currentTimeMillis();
      }

      @Override
      public void initHttp() {

      }

      @Override
      public WxCpConfigStorage getWxCpConfigStorage() {
        return config;
      }
    };
    config.setAgentId(1);
    service.setWxCpConfigStorage(config);
    RequestExecutor<Object, Object> re = mock(RequestExecutor.class);

    AtomicInteger counter = new AtomicInteger();

    Mockito.when(re.execute(Mockito.anyString(), Mockito.any(), Mockito.any())).thenAnswer(invocation -> {
      counter.incrementAndGet();
      WxError error =
        WxError.builder().errorCode(WeChatErrorCode.CODE_40001.getCode()).errorMsg(WeChatErrorCode.CODE_40001.getMsg()).build();
      throw new WxErrorException(error);
    });
    try {
      Object execute = service.execute(re, "http://baidu.com", new HashMap<>());
      Assert.fail("代码应该不会执行到这里");
    } catch (WxErrorException e) {
      Assert.assertEquals(WeChatErrorCode.CODE_40001.getCode(), e.getError().getErrorCode());
      Assert.assertEquals(2, counter.get());
    }
  }
}
