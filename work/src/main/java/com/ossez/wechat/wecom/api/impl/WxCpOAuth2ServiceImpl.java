package com.ossez.wechat.wecom.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.URIUtil;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.wecom.api.WxCpOAuth2Service;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.WxCpOauth2UserInfo;
import com.ossez.wechat.wecom.bean.WxCpUserDetail;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;

import static com.ossez.wechat.common.constant.WeChatConstant.OAuth2Scope.*;

/**
 * <pre>
 * oauth2相关接口实现类.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpOAuth2ServiceImpl implements WxCpOAuth2Service {
  private final WxCpService mainService;

  @Override
  public String buildAuthorizationUrl(String state) {
    return this.buildAuthorizationUrl(
      this.mainService.getWxCpConfigStorage().getOauth2redirectUri(),
      state
    );
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state) {
    return this.buildAuthorizationUrl(redirectUri, state, SNSAPI_BASE);
  }

  @Override
  public String buildAuthorizationUrl(String redirectUri, String state, String scope) {
    StringBuilder url = new StringBuilder(WxCpApiPathConsts.OAuth2.URL_OAUTH2_AUTHORIZE);
    url.append("?appid=").append(this.mainService.getWxCpConfigStorage().getCorpId());
    url.append("&redirect_uri=").append(URIUtil.encodeURIComponent(redirectUri));
    url.append("&response_type=code");
    url.append("&scope=").append(scope);

    if (SNSAPI_PRIVATEINFO.equals(scope) || SNSAPI_USERINFO.equals(scope)) {
      url.append("&agentid=").append(this.mainService.getWxCpConfigStorage().getAgentId());
    }

    if (state != null) {
      url.append("&state=").append(state);
    }

    url.append("#wechat_redirect");
    return url.toString();
  }

  @Override
  public WxCpOauth2UserInfo getUserInfo(String code) throws WxErrorException {
    return this.getUserInfo(this.mainService.getWxCpConfigStorage().getAgentId(), code);
  }

  @Override
  public WxCpOauth2UserInfo getUserInfo(Integer agentId, String code) throws WxErrorException {
    String responseText =
      this.mainService.get(String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.OAuth2.GET_USER_INFO), code,
        agentId), null);
    JsonObject jo = GsonParser.parse(responseText);

    return WxCpOauth2UserInfo.builder()
      .userId(GsonHelper.getString(jo, "UserId"))
      .deviceId(GsonHelper.getString(jo, "DeviceId"))
      .openId(GsonHelper.getString(jo, "OpenId"))
      .userTicket(GsonHelper.getString(jo, "user_ticket"))
      .expiresIn(GsonHelper.getString(jo, "expires_in"))
      .externalUserId(GsonHelper.getString(jo, "external_userid"))
      .parentUserId(GsonHelper.getString(jo, "parent_userid"))
      .studentUserId(GsonHelper.getString(jo, "student_userid"))
      .build();
  }

  @Override
  public WxCpOauth2UserInfo getSchoolUserInfo(String code) throws WxErrorException {
    String responseText =
      this.mainService.get(String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.OAuth2.GET_SCHOOL_USER_INFO),
        code), null);
    JsonObject jo = GsonParser.parse(responseText);

    return WxCpOauth2UserInfo.builder()
      .deviceId(GsonHelper.getString(jo, "DeviceId"))
      .parentUserId(GsonHelper.getString(jo, "parent_userid"))
      .studentUserId(GsonHelper.getString(jo, "student_userid"))
      .build();
  }

  @Override
  public WxCpUserDetail getUserDetail(String userTicket) throws WxErrorException {
    JsonObject param = new JsonObject();
    param.addProperty("user_ticket", userTicket);
    String responseText = this.mainService.post(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.OAuth2.GET_USER_DETAIL),
      param.toString());
    return WxCpGsonBuilder.create().fromJson(responseText, WxCpUserDetail.class);
  }
}