package com.ossez.wechat.common.api;

import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import com.ossez.wechat.common.model.WeChatOAuth2AccessToken;
import com.ossez.wechat.common.model.entity.WeChatOAuth2UserInfo;
import com.ossez.wechat.common.model.req.NetworkCheck;
import com.ossez.wechat.common.model.req.QueryQuota;
import com.ossez.wechat.common.model.res.NetworkCheckResponse;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeChatOpenApi {

    @GET("/sns/oauth2/access_token")
    Single<WeChatOAuth2AccessToken> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret, @Query("code") String code);

    @GET("/sns/userinfo")
    Single<WeChatOAuth2UserInfo> getWeChatUserInfo(@Query("access_token") String accessToken, @Query("openid") String openId);


}
