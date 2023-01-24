package com.ossez.wechat.oa.api.impl;

import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.bean.WxOAuth2UserInfo;
import com.ossez.wechat.common.bean.oauth2.WxOAuth2AccessToken;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.SimpleGetRequestExecutor;
import com.ossez.wechat.common.util.http.URIUtil;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.service.WxOAuth2Service;
import com.ossez.wechat.oa.config.ConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.io.IOException;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.OAuth2.*;

/**
 * oauth2 相关接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2020-08-08
 */
@RequiredArgsConstructor
public class WxMpOAuth2ServiceImpl implements WxOAuth2Service {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public String buildAuthorizationUrl(String redirectUri, String scope, String state) {
    return String.format(CONNECT_OAUTH2_AUTHORIZE_URL.getUrl(getMpConfigStorage()),
      getMpConfigStorage().getAppId(), URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
  }

  private WxOAuth2AccessToken getOAuth2AccessToken(String url) throws WxErrorException {
    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp());
      String responseText = executor.execute(url, null, WxType.MP);
      return WxOAuth2AccessToken.fromJson(responseText);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorCode(99999).errorMsg(e.getMessage()).build(), e);
    }
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
    return this.getAccessToken(getMpConfigStorage().getAppId(), getMpConfigStorage().getSecret(), code);
  }

  @Override
  public WxOAuth2AccessToken getAccessToken(String appId, String appSecret, String code) throws WxErrorException {
    return this.getOAuth2AccessToken(String.format(OAUTH2_ACCESS_TOKEN_URL.getUrl(getMpConfigStorage()), appId, appSecret, code));
  }

  @Override
  public WxOAuth2AccessToken refreshAccessToken(String refreshToken) throws WxErrorException {
    String url = String.format(OAUTH2_REFRESH_TOKEN_URL.getUrl(getMpConfigStorage()), getMpConfigStorage().getAppId(), refreshToken);
    return this.getOAuth2AccessToken(url);
  }

  protected ConfigStorage getMpConfigStorage() {
    return this.weChatOfficialAccountService.getWxMpConfigStorage();
  }

  @Override
  public WxOAuth2UserInfo getUserInfo(WxOAuth2AccessToken token, String lang) throws WxErrorException {
    if (lang == null) {
      lang = "zh_CN";
    }

    String url = String.format(OAUTH2_USERINFO_URL.getUrl(getMpConfigStorage()), token.getAccessToken(), token.getOpenId(), lang);

    try {
      RequestExecutor<String, String> executor = SimpleGetRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp());
      String responseText = executor.execute(url, null, WxType.MP);
      return WxOAuth2UserInfo.fromJson(responseText);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    }
  }

  @Override
  public boolean validateAccessToken(WxOAuth2AccessToken token) {
    String url = String.format(OAUTH2_VALIDATE_TOKEN_URL.getUrl(getMpConfigStorage()), token.getAccessToken(), token.getOpenId());

    try {
      SimpleGetRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()).execute(url, null, WxType.MP);
    } catch (IOException e) {
      throw new WxRuntimeException(e);
    } catch (WxErrorException e) {
      return false;
    }
    return true;
  }
}
