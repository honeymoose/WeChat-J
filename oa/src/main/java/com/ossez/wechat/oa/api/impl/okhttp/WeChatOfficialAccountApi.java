package com.ossez.wechat.oa.api.impl.okhttp;

import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import com.ossez.wechat.common.model.req.NetworkCheck;
import com.ossez.wechat.common.model.req.QueryQuota;
import com.ossez.wechat.common.model.res.NetworkCheckResponse;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface WeChatOfficialAccountApi {

    @GET("/cgi-bin/token")
    Single<WeChatAccessToken> getAccessToken(@Query("grant_type") String grantType, @Query("appid") String appId, @Query("secret") String secret);

    @GET("/cgi-bin/get_api_domain_ip")
    Single<WeChatApiDomainIp> getDomainIPs();

    @POST("/cgi-bin/callback/check")
    Single<NetworkCheckResponse> checkNetwork(@Body NetworkCheck request);

    @POST("/cgi-bin/clear_quota")
    Single<NetworkCheckResponse> clearQuota(@Body NetworkCheck request);

    @POST("/cgi-bin/openapi/quota/get")
    Single<QueryQuotaResponse> queryQuota(@Body QueryQuota request);

}
