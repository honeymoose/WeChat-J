package com.ossez.wechat.oa.api.impl;

import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpAiOpenService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.enums.Language;
import com.ossez.wechat.oa.util.requestexecuter.voice.VoiceUploadRequestExecutor;

import java.io.File;

import static com.ossez.wechat.common.enums.WxMpApiUrl.AiOpen.*;

/**
 * <pre>
 *  Created by BinaryWang on 2018/6/9.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxMpAiOpenServiceImpl implements WxMpAiOpenService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public void uploadVoice(String voiceId, Language lang, File voiceFile) throws WxErrorException {
    if (lang == null) {
      lang = Language.ZH_CN;
    }

    this.weChatOfficialAccountService.execute(VoiceUploadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      String.format(VOICE_UPLOAD_URL.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), "mp3", voiceId, lang.getCode()),
      voiceFile);
  }

  @Override
  public String recogniseVoice(String voiceId, Language lang, File voiceFile) throws WxErrorException {
    this.uploadVoice(voiceId, lang, voiceFile);
    return this.queryRecognitionResult(voiceId, lang);
  }

  @Override
  public String translate(Language langFrom, Language langTo, String content) throws WxErrorException {
    String response = this.weChatOfficialAccountService.post(String.format(TRANSLATE_URL.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()),
      langFrom.getCode(), langTo.getCode()), content);

    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(response).get("to_content").getAsString();
  }

  @Override
  public String queryRecognitionResult(String voiceId, Language lang) throws WxErrorException {
    if (lang == null) {
      lang = Language.ZH_CN;
    }

    final String response = this.weChatOfficialAccountService.get(VOICE_QUERY_RESULT_URL,
      String.format("voice_id=%s&lang=%s", voiceId, lang.getCode()));
    WxError error = WxError.fromJson(response, WxType.MP);
    if (error.getErrorCode() != 0) {
      throw new WxErrorException(error);
    }

    return GsonParser.parse(response).get("result").getAsString();
  }
}
