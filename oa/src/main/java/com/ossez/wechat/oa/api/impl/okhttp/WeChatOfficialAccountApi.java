package com.ossez.wechat.oa.api.impl.okhttp;

import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeChatOfficialAccountApi {

    @GET("/cgi-bin/token")
    Single<WeChatAccessToken> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);

    @GET("/cgi-bin/get_api_domain_ip")
    Single<WeChatApiDomainIp> getDomainIPs(@Query("access_token") String accessToken);

    @GET("/cgi-bin/callback/check")
    Single<String> checkNetwork(@Query("access_token") String accessToken);

}
