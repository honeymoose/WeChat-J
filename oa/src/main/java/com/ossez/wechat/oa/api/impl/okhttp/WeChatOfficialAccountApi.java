package com.ossez.wechat.oa.api.impl.okhttp;

import com.ossez.wechat.common.model.WeChatAccessToken;
import io.reactivex.Single;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeChatOfficialAccountApi {

    @GET("token")
    Single<WeChatAccessToken> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);
}
