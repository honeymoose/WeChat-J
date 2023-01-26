package com.ossez.wechat.wecom.api.impl;

import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import lombok.RequiredArgsConstructor;

import com.ossez.wechat.wecom.api.WxCpOaMeetingRoomService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.oa.meetingroom.WxCpOaMeetingRoom;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;

import java.util.List;

import static com.ossez.wechat.wecom.constant.WxCpApiPathConsts.Oa.*;

/**
 * The type Wx cp oa meeting room service.
 *
 * @author fcat
 * @version 1.0  Create by 2022/8/12 23:49
 */
@RequiredArgsConstructor
public class WxCpOaMeetingRoomServiceImpl implements WxCpOaMeetingRoomService {
  private final WxCpService wxCpService;

  @Override
  public String addMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException {
    return this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(MEETINGROOM_ADD), meetingRoom);
  }

  @Override
  public List<WxCpOaMeetingRoom> listMeetingRoom(WxCpOaMeetingRoom meetingRoomRequest) throws WxErrorException {
    String response = this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(MEETINGROOM_LIST),
      meetingRoomRequest);
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("meetingroom_list").getAsJsonArray().toString(),
      new TypeToken<List<WxCpOaMeetingRoom>>() {
      }.getType());
  }

  @Override
  public void editMeetingRoom(WxCpOaMeetingRoom meetingRoom) throws WxErrorException {
    this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(MEETINGROOM_EDIT), meetingRoom);
  }

  @Override
  public void deleteMeetingRoom(Integer meetingRoomId) throws WxErrorException {
    this.wxCpService.post(this.wxCpService.getWxCpConfigStorage().getApiUrl(MEETINGROOM_DEL),
      GsonHelper.buildJsonObject("meetingroom_id", meetingRoomId));
  }
}
