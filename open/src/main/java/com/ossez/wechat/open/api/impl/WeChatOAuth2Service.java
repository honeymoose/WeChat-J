package com.ossez.wechat.open.api.impl;

import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.model.WeChatOAuth2AccessToken;
import com.ossez.wechat.common.model.entity.WeChatOAuth2UserInfo;
import com.ossez.wechat.common.model.entity.WeChatUser;
import com.ossez.wechat.common.service.WxOAuth2Service;
import com.ossez.wechat.common.util.http.SimpleGetRequestExecutor;
import com.ossez.wechat.common.util.http.URIUtil;
import lombok.extern.slf4j.Slf4j;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import retrofit2.HttpException;

import java.io.IOException;

import static com.ossez.wechat.common.enums.WxMpApiUrl.OAuth2.*;
import static com.ossez.wechat.common.enums.WxMpApiUrl.Other.QRCONNECT_URL;

/**
 * oauth2 接口实现
 *
 * @author YuCheng
 */
@Slf4j
public class WeChatOAuth2Service extends WeChatOpenServiceImpl implements WxOAuth2Service {
    private final String openAppId;
    private final String openSecret;
    private final String appId;
    private final String appSecret;

    public WeChatOAuth2Service(String openAppId, String openSecret, String appId, String appSecret) {
        this.openAppId = openAppId;
        this.openSecret = openSecret;
        this.appId = appId;
        this.appSecret = appSecret;
    }

    @Override
    public String buildAuthorizationUrl(String redirectUri, String scope, String state) {
        return String.format(QRCONNECT_URL.getUrl(null),
                this.appId, URIUtil.encodeURIComponent(redirectUri), scope, StringUtils.trimToEmpty(state));
    }

    private WeChatOAuth2AccessToken getOAuth2AccessToken(String url) throws WxErrorException {
        return WeChatOAuth2AccessToken.fromJson(this.get(url, null));
    }

    @Override
    public WeChatOAuth2AccessToken getAccessToken(String code) throws WxErrorException {
        return this.getAccessToken(this.openAppId, this.openSecret, code);
    }

    @Override
    public WeChatOAuth2AccessToken getAccessToken(String appId, String appSecret, String code) throws WxErrorException {

        WeChatOAuth2AccessToken weChatOAuth2AccessToken = new WeChatOAuth2AccessToken();

        try {

            weChatOAuth2AccessToken = weChatOpenApi.getAccessToken("authorization_code", appId, appSecret, code).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }

        return weChatOAuth2AccessToken;
    }

    @Override
    public WeChatOAuth2AccessToken refreshAccessToken(String refreshToken) throws WxErrorException {
        String url = String.format(OAUTH2_REFRESH_TOKEN_URL.getUrl(null), this.appId, refreshToken);
        return this.getOAuth2AccessToken(url);
    }

    @Override
    @Deprecated
    public WeChatOAuth2UserInfo getUserInfo(WeChatOAuth2AccessToken token, String lang) throws WxErrorException {
        if (lang == null) {
            lang = "zh_CN";
        }

        String url = String.format(OAUTH2_USERINFO_URL.getUrl(null), token.getAccessToken(), token.getOpenId(), lang);

        return null;
    }

    @Override
    public boolean validateAccessToken(WeChatOAuth2AccessToken token) {
        String url = String.format(OAUTH2_VALIDATE_TOKEN_URL.getUrl(null), token.getAccessToken(), token.getOpenId());

        try {
            SimpleGetRequestExecutor.create(this).execute(url, null, WxType.MP);
        } catch (IOException e) {
            throw new WxRuntimeException(e);
        } catch (WxErrorException e) {
            return false;
        }
        return true;
    }

    public WeChatOAuth2UserInfo getWeChatUserInfo(String weChatCode, String weChatState) throws WxErrorException {
        OkHttpClient client = new OkHttpClient();
        String response = StringUtils.EMPTY;
        WeChatUser weChatUser = null;
//    String xx = weChatOfficialAccountService.getAccessToken();


        WeChatOAuth2UserInfo weChatOAuth2UserInfo = new WeChatOAuth2UserInfo();

        try {
            WeChatOAuth2AccessToken weChatOAuth2AccessToken = getAccessToken(weChatCode);
            String accessToken = weChatOAuth2AccessToken.getAccessToken();
            String openId = weChatOAuth2AccessToken.getOpenId();


            weChatOAuth2UserInfo = weChatOpenApi.getWeChatUserInfo(accessToken, openId).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }
        return weChatOAuth2UserInfo;


//    response = callWeChatAccessTokenAPI(client, weChatCode, weChatState);
//    WeChatAccessToken weChatAccessToken = objectMapper.readValue(response, WeChatAccessToken.class);
//
//
//    String accessToken = weChatAccessToken.getAccessToken();
//    String openId = weChatAccessToken.getOpenId();
//
//    if (StringUtils.isNoneEmpty(accessToken, openId)) {
//      response = callWeChatUserInfoAPI(client, accessToken, openId);
//      weChatUser = objectMapper.readValue(response, WeChatUser.class);
//
//      log.debug("WeChat NickName - [{}]", weChatUser.getNickname());
//      WeChatCallState weChatCallState = new WeChatCallState();
//      weChatCallState.setWeChatState(weChatState);
//      weChatCallState.setWeChatCode(weChatCode);
//      weChatCallState.setWeChatAccessToken(accessToken);
//      weChatCallState.setWeChatOpenId(weChatUser.getOpenId());
//      weChatCallState.setWeChatUnionId(weChatUser.getUnionId());
//      weChatCallState.setNickName(weChatUser.getNickname());
//      weChatCallState.setHeadImgURL(weChatUser.getHeadImgURL());
//      weChatCallStateRepository.save(weChatCallState);
//    }
//
//        return true;
    }
}
