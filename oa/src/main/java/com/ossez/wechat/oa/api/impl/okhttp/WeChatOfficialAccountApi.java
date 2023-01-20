package com.ossez.wechat.oa.api.impl.okhttp;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface WeChatOfficialAccountApi {

    @GET("/token")
    Call<ResponseBody> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);
}
