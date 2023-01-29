package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.oa.bean.kefu.WxMpKefuMessage;
import com.ossez.wechat.oa.bean.kefu.request.WxMpKfAccountRequest;
import com.ossez.wechat.oa.bean.kefu.request.WxMpKfSessionRequest;
import com.ossez.wechat.oa.bean.kefu.result.*;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.bean.result.WxMediaUploadResult;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.MediaUploadRequestExecutor;
import com.ossez.wechat.oa.api.WxMpKefuService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;

import java.io.File;
import java.util.Date;

import static com.ossez.wechat.common.enums.WxMpApiUrl.Kefu.*;

/**
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpKefuServiceImpl implements WxMpKefuService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public boolean sendKefuMessage(WxMpKefuMessage message) throws WxErrorException {
    return this.sendKefuMessageWithResponse(message) != null;
  }

  @Override
  public String sendKefuMessageWithResponse(WxMpKefuMessage message) throws WxErrorException {
    return this.weChatOfficialAccountService.post(MESSAGE_CUSTOM_SEND, message.toJson());
  }

  @Override
  public WxMpKfList kfList() throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(GET_KF_LIST, null);
    return WxMpKfList.fromJson(responseContent);
  }

  @Override
  public WxMpKfOnlineList kfOnlineList() throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(GET_ONLINE_KF_LIST, null);
    return WxMpKfOnlineList.fromJson(responseContent);
  }

  @Override
  public boolean kfAccountAdd(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(KFACCOUNT_ADD, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUpdate(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(KFACCOUNT_UPDATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountInviteWorker(WxMpKfAccountRequest request) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(KFACCOUNT_INVITE_WORKER, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfAccountUploadHeadImg(String kfAccount, File imgFile) throws WxErrorException {
    WxMediaUploadResult responseContent = this.weChatOfficialAccountService
      .execute(MediaUploadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
        String.format(KFACCOUNT_UPLOAD_HEAD_IMG.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), kfAccount), imgFile);
    return responseContent != null;
  }

  @Override
  public boolean kfAccountDel(String kfAccount) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(String.format(KFACCOUNT_DEL.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()),
      kfAccount), null);
    return responseContent != null;
  }

  @Override
  public boolean kfSessionCreate(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.weChatOfficialAccountService.post(KFSESSION_CREATE, request.toJson());
    return responseContent != null;
  }

  @Override
  public boolean kfSessionClose(String openid, String kfAccount) throws WxErrorException {
    WxMpKfSessionRequest request = new WxMpKfSessionRequest(kfAccount, openid);
    String responseContent = this.weChatOfficialAccountService.post(KFSESSION_CLOSE, request.toJson());
    return responseContent != null;
  }

  @Override
  public WxMpKfSessionGetResult kfSessionGet(String openid) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(String.format(KFSESSION_GET_SESSION
      .getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), openid), null);
    return WxMpKfSessionGetResult.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionList kfSessionList(String kfAccount) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(String.format(KFSESSION_GET_SESSION_LIST
      .getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), kfAccount), null);
    return WxMpKfSessionList.fromJson(responseContent);
  }

  @Override
  public WxMpKfSessionWaitCaseList kfSessionGetWaitCase() throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(KFSESSION_GET_WAIT_CASE, null);
    return WxMpKfSessionWaitCaseList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime, Long msgId, Integer number) throws WxErrorException {
    if (number > 10000) {
      throw new WxErrorException("非法参数请求，每次最多查询10000条记录！");
    }

    if (startTime.after(endTime)) {
      throw new WxErrorException("起始时间不能晚于结束时间！");
    }

    JsonObject param = new JsonObject();
    param.addProperty("starttime", startTime.getTime() / 1000);
    param.addProperty("endtime", endTime.getTime() / 1000);
    param.addProperty("msgid", msgId);
    param.addProperty("number", number);

    String responseContent = this.weChatOfficialAccountService.post(MSG_RECORD_LIST, param.toString());

    return WxMpKfMsgList.fromJson(responseContent);
  }

  @Override
  public WxMpKfMsgList kfMsgList(Date startTime, Date endTime) throws WxErrorException {
    int number = 10000;
    WxMpKfMsgList result = this.kfMsgList(startTime, endTime, 1L, number);

    if (result != null && result.getNumber() == number) {
      Long msgId = result.getMsgId();
      WxMpKfMsgList followingResult = this.kfMsgList(startTime, endTime, msgId, number);
      while (followingResult != null && followingResult.getRecords().size() > 0) {
        result.getRecords().addAll(followingResult.getRecords());
        result.setNumber(result.getNumber() + followingResult.getNumber());
        result.setMsgId(followingResult.getMsgId());
        followingResult = this.kfMsgList(startTime, endTime, followingResult.getMsgId(), number);
      }
    }

    return result;
  }

  @Override
  public boolean sendKfTypingState(String openid, String command) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("touser", openid);
    params.addProperty("command", command);
    String responseContent = this.weChatOfficialAccountService.post(CUSTOM_TYPING, params.toString());
    return responseContent != null;
  }

}
