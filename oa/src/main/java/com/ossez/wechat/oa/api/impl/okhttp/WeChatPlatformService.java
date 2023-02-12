package com.ossez.wechat.oa.api.impl.okhttp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import com.ossez.wechat.common.model.req.NetworkCheck;
import com.ossez.wechat.common.model.req.QueryQuota;
import com.ossez.wechat.common.model.res.NetworkCheckResponse;
import com.ossez.wechat.common.model.res.QueryQuotaResponse;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.AuthenticationInterceptor;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.WeChatErrorInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.concurrent.TimeUnit;

/**
 * WeChat Official Account Platform related Service
 *
 * @author YuCheng
 */
public class WeChatPlatformService {
    private final Logger log = LoggerFactory.getLogger(WeChatPlatformService.class);

    private final WeChatOfficialAccountService weChatOfficialAccountService;
    WeChatOfficialAccountApi weChatOfficialAccountApi;

    public WeChatPlatformService(WeChatOfficialAccountService weChatOfficialAccountService) {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
        String accessToken = null;
        try {
            accessToken = weChatOfficialAccountService.getAccessToken();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new AuthenticationInterceptor(accessToken))
                .addInterceptor(new WeChatErrorInterceptor())
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS)).readTimeout(1000, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatConstant.ENDPOINT_WECHAT).client(client)
//                .addConverterFactory(GsonConverterFactory.create())
                .addConverterFactory(JacksonConverterFactory.create())
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.weChatOfficialAccountApi = retrofit.create(WeChatOfficialAccountApi.class);

    }


    public String getDomainIPs() throws WxErrorException {

        WeChatApiDomainIp apiDomainIp = new WeChatApiDomainIp();

        try {
            apiDomainIp = weChatOfficialAccountApi.getDomainIPs().blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return apiDomainIp.getIpList().toString();
    }

    public NetworkCheckResponse checkNetwork() throws WxErrorException {

        NetworkCheck networkCheck = new NetworkCheck();
        networkCheck.setAction("all");
        NetworkCheckResponse networkCheckResponse = new NetworkCheckResponse();

        try {
            networkCheckResponse = weChatOfficialAccountApi.checkNetwork(networkCheck).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return networkCheckResponse;
    }

    public NetworkCheckResponse clearQuota() throws WxErrorException {


        NetworkCheck networkCheck = new NetworkCheck();
        networkCheck.setAction("all");
        NetworkCheckResponse networkCheckResponse = new NetworkCheckResponse();

        try {
            networkCheckResponse = weChatOfficialAccountApi.clearQuota(networkCheck).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return networkCheckResponse;
    }

    /**
     * queryQuota for WeChat API
     *
     * @return
     * @throws WxErrorException
     */
    public QueryQuotaResponse queryQuota() throws WxErrorException {


        QueryQuota queryQuota = new QueryQuota();
        queryQuota.setCgiPath("/cgi-bin/message/custom/send");

        QueryQuotaResponse queryQuotaResponse = new QueryQuotaResponse();

        try {
            queryQuotaResponse = weChatOfficialAccountApi.queryQuota(queryQuota).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return queryQuotaResponse;
    }


}
