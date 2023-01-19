package com.ossez.wechat.oa.api.impl;

import java.io.File;

import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import org.assertj.core.api.Assertions;
import org.testng.annotations.*;

import com.google.inject.Inject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.enums.AiLangType;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/10.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpAiOpenServiceImplTest {
  @Inject
  protected WeChatOfficialAccountService wxService;

  @Test
  public void testUploadVoice() throws WxErrorException {
    String voiceId = System.currentTimeMillis() + "a";
    AiLangType lang = AiLangType.zh_CN;
    this.wxService.getAiOpenService().uploadVoice(voiceId, lang, new File("d:\\t.mp3"));
  }

  @Test
  public void testRecogniseVoice() throws WxErrorException {
    String voiceId = System.currentTimeMillis() + "a";
    AiLangType lang = AiLangType.zh_CN;
    final String result = this.wxService.getAiOpenService().recogniseVoice(voiceId, lang, new File("d:\\t.mp3"));
    Assertions.assertThat(result).isNotEmpty();
  }

  @Test
  public void testTranslate() throws WxErrorException {
    final String result = this.wxService.getAiOpenService().translate(AiLangType.zh_CN, AiLangType.en_US, "微信文档很坑爹");
    Assertions.assertThat(result).isNotEmpty();
  }
}
