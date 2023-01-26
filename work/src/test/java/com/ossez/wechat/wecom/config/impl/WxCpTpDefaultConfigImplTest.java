package com.ossez.wechat.wecom.config.impl;

import com.ossez.wechat.common.model.WeChatAccessToken;
import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.concurrent.TimeUnit;

/**
 * The type Wx cp tp default config impl test.
 */
public class WxCpTpDefaultConfigImplTest {

  /**
   * Test get suite access token entity.
   *
   * @throws InterruptedException the interrupted exception
   */
  @Test
  public void testGetSuiteAccessTokenEntity() throws InterruptedException {
    final String testAccessToken = "5O_32IEDOib99RliaF301vzGiZaAJw3CsaNb4QXyQ" +
      "-07KJ0UDQ8nxq9vs66jNLIZ4TvYs3QFlYZag1WfG8i4gNu_dYQj2Ff89xznZPquv7EFMAZha_faYZrE0uCFRqkV";
    final long testExpireTime = 7200L;
    final long restTime = 10L;
    WxCpTpDefaultConfigImpl storage = new WxCpTpDefaultConfigImpl();
    storage.setSuiteAccessToken(testAccessToken);
    storage.setSuiteAccessTokenExpiresTime(System.currentTimeMillis() + (testExpireTime - 200) * 1000L);
    TimeUnit.SECONDS.sleep(restTime);
    WeChatAccessToken accessToken = storage.getSuiteAccessTokenEntity();
    Assert.assertEquals(accessToken.getAccessToken(), testAccessToken, "accessToken不一致");
    Assert.assertTrue(accessToken.getExpiresIn() <= testExpireTime - restTime, "过期时间计算有误");
  }

}
