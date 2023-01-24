package com.ossez.wechat.oa.demo;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.bean.result.WxMediaUploadResult;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.session.WxSessionManager;
import com.ossez.wechat.oa.api.WxMpMessageHandler;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.TestConstants;
import com.ossez.wechat.oa.bean.message.WxMpXmlMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutImageMessage;
import com.ossez.wechat.oa.bean.message.WxMpXmlOutMessage;

import java.util.Map;

public class DemoImageHandler implements WxMpMessageHandler {
  @Override
  public WxMpXmlOutMessage handle(WxMpXmlMessage wxMessage, Map<String, Object> context,
                                  WeChatOfficialAccountService weChatOfficialAccountService, WxSessionManager sessionManager) {
    try {
      WxMediaUploadResult wxMediaUploadResult = weChatOfficialAccountService.getMaterialService()
        .mediaUpload(WeChatConstant.MediaFileType.IMAGE, TestConstants.FILE_JPG, ClassLoader.getSystemResourceAsStream("mm.jpeg"));
      WxMpXmlOutImageMessage m
        = WxMpXmlOutMessage
        .IMAGE()
        .mediaId(wxMediaUploadResult.getMediaId())
        .fromUser(wxMessage.getToUser())
        .toUser(wxMessage.getFromUser())
        .build();
      return m;
    } catch (WxErrorException e) {
      e.printStackTrace();
    }

    return null;
  }
}
