package com.ossez.wechat.oa.api;

import com.google.inject.Inject;
import com.ossez.wechat.common.util.crypto.SHA1;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import org.testng.Assert;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试jsapi ticket接口
 *
 * @author chanjarster
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpJsAPITest {

  @Inject
  protected WeChatOfficialAccountService wxService;

  public void test() {
    long timestamp = 1419835025L;
    String url = "http://omstest.vmall.com:23568/thirdparty/wechat/vcode/gotoshare?quantity=1&batchName=MATE7";
    String noncestr = "82693e11-b9bc-448e-892f-f5289f46cd0f";
    String jsapiTicket = "bxLdikRXVbTPdHSM05e5u4RbEYQn7pNQMPrfzl8lJNb1foLDa3HIwI3BRMkQmSO_5F64VFa75uURcq6Uz7QHgA";
    String result = SHA1.genWithAmple(
      "jsapi_ticket=" + jsapiTicket,
      "noncestr=" + noncestr,
      "timestamp=" + timestamp,
      "url=" + url
    );

    Assert.assertEquals(result, "c6f04b64d6351d197b71bd23fb7dd2d44c0db486");
  }

}
