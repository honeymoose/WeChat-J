package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.oa.bean.WxMpUserQuery;
import com.ossez.wechat.oa.bean.result.WxMpChangeOpenid;
import com.ossez.wechat.oa.bean.result.WxMpUser;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.WxMpUserService;
import com.ossez.wechat.oa.bean.result.WxMpUserList;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import org.apache.commons.lang3.StringUtils;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ossez.wechat.common.enums.WxMpApiUrl.User.*;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author BinaryWang
 */
@RequiredArgsConstructor
public class WxMpUserServiceImpl implements WxMpUserService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public void userUpdateRemark(String openid, String remark) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("openid", openid);
    json.addProperty("remark", remark);
    this.weChatOfficialAccountService.post(USER_INFO_UPDATE_REMARK_URL, json.toString());
  }

  @Override
  public WxMpUser userInfo(String openid) throws WxErrorException {
    return this.userInfo(openid, null);
  }

  @Override
  public WxMpUser userInfo(String openid, String lang) throws WxErrorException {
    lang = lang == null ? "zh_CN" : lang;
    String responseContent = this.weChatOfficialAccountService.get(USER_INFO_URL, "openid=" + openid + "&lang=" + lang);
    return WxMpUser.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList(String nextOpenid) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(USER_GET_URL, nextOpenid == null ? null : "next_openid=" + nextOpenid);
    return WxMpUserList.fromJson(responseContent);
  }

  @Override
  public WxMpUserList userList() throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.get(USER_GET_URL, null);
    WxMpUserList mergeList = new WxMpUserList();

    WxMpUserList wxMpUserList = WxMpUserList.fromJson(responseContent);
    mergeList.getOpenids().addAll(wxMpUserList.getOpenids());
    mergeList.setCount(wxMpUserList.getCount());
    mergeList.setTotal(wxMpUserList.getTotal());

    while (StringUtils.isNotEmpty(wxMpUserList.getNextOpenid())) {
      WxMpUserList nextReqUserList = userList(wxMpUserList.getNextOpenid());
      mergeList.getOpenids().addAll(nextReqUserList.getOpenids());
      mergeList.setCount(mergeList.getCount() + nextReqUserList.getCount());
      wxMpUserList = nextReqUserList;
    }
    return mergeList;
  }

  @Override
  public List<WxMpChangeOpenid> changeOpenid(String fromAppid, List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("from_appid", fromAppid);
    map.put("openid_list", openidList);
    String responseContent = this.weChatOfficialAccountService.post(USER_CHANGE_OPENID_URL, WxMpGsonBuilder.create().toJson(map));

    return WxMpChangeOpenid.fromJsonList(responseContent);
  }

  @Override
  public List<WxMpUser> userInfoList(List<String> openidList)
    throws WxErrorException {
    return this.userInfoList(new WxMpUserQuery(openidList));
  }

  @Override
  public List<WxMpUser> userInfoList(WxMpUserQuery userQuery) throws WxErrorException {
    String responseContent = this.weChatOfficialAccountService.post(USER_INFO_BATCH_GET_URL, userQuery.toJsonString());
    return WxMpUser.fromJsonList(responseContent);
  }

}
