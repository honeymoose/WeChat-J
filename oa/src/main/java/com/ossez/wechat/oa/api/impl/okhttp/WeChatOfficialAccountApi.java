package com.ossez.wechat.oa.api.impl.okhttp;

import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import com.ossez.wechat.common.model.WeChatStatus;
import com.ossez.wechat.common.model.req.*;
import com.ossez.wechat.common.model.res.DataCubeArticle;
import com.ossez.wechat.common.model.res.NetworkCheckResponse;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import com.ossez.wechat.common.model.res.DataCubeUser;
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

    @POST("/cgi-bin/message/custom/send")
    Single<WeChatStatus> sendMessage(@Body CustomMessage customMessage);

    // MENU
    @POST("/cgi-bin/menu/create")
    Single<WeChatStatus> createMenu(@Body MenuRequest menuRequest);

    @GET("/cgi-bin/get_current_selfmenu_info")
    Single<WeChatStatus> getMenu();

    // DATA ANALYST
    @POST("/datacube/getusersummary")
    Single<DataCubeUser> getUserSummary(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getusercumulate")
    Single<DataCubeUser> getUserCumulate(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getarticlesummary")
    Single<DataCubeArticle> getarticlesummary(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getarticletotal")
    Single<DataCubeUser> getarticletotal(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getuserread")
    Single<DataCubeUser> getuserread(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getuserreadhour")
    Single<DataCubeUser> getuserreadhour(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getusershare")
    Single<DataCubeUser> getusershare(@Body DataCubeRequest dataCubeRequest);

    @POST("/datacube/getusersharehour")
    Single<DataCubeUser> getusersharehour(@Body DataCubeRequest dataCubeRequest);
}