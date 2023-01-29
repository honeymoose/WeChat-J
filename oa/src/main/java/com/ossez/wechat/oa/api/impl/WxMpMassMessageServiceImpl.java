package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WxMpMassMessageService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.*;
import com.ossez.wechat.oa.bean.result.WxMpMassGetResult;
import com.ossez.wechat.oa.bean.result.WxMpMassSendResult;
import com.ossez.wechat.oa.bean.result.WxMpMassSpeedGetResult;
import com.ossez.wechat.oa.bean.result.WxMpMassUploadResult;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import static com.ossez.wechat.common.enums.WxMpApiUrl.MassMessage;

/**
 * <pre>
 * 群发消息服务类
 * Created by Binary Wang on 2017-8-16.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpMassMessageServiceImpl implements WxMpMassMessageService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public WxMpMassUploadResult massNewsUpload(WxMpMassNews news) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MassMessage.MEDIA_UPLOAD_NEWS_URL, news.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassUploadResult massVideoUpload(WxMpMassVideo video) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MassMessage.MEDIA_UPLOAD_VIDEO_URL, video.toJson());
    return WxMpMassUploadResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massGroupMessageSend(WxMpMassTagMessage message) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_SENDALL_URL, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massOpenIdsMessageSend(WxMpMassOpenIdsMessage message) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_SEND_URL, message.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public WxMpMassSendResult massMessagePreview(WxMpMassPreviewMessage wxMpMassPreviewMessage) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_PREVIEW_URL, wxMpMassPreviewMessage.toJson());
    return WxMpMassSendResult.fromJson(responseContent);
  }

  @Override
  public void delete(Long msgId, Integer articleIndex) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("msg_id", msgId);
    jsonObject.addProperty("article_idx", articleIndex);
    this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_DELETE_URL, jsonObject.toString());
  }


  @Override
  public WxMpMassSpeedGetResult messageMassSpeedGet() throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    String response = this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_SPEED_GET_URL, jsonObject.toString());
    return WxMpMassSpeedGetResult.fromJson(response);
  }


  @Override
  public void messageMassSpeedSet(Integer speed) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("speed", speed);
    this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_SPEED_SET_URL, jsonObject.toString());
  }


  @Override
  public WxMpMassGetResult messageMassGet(Long msgId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("msg_id", msgId);
    String response = this.weChatOfficialAccountService.post(MassMessage.MESSAGE_MASS_GET_URL, jsonObject.toString());
    return WxMpMassGetResult.fromJson(response);
  }

}
