package com.ossez.wechat.oa.api.impl.okhttp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.google.gson.JsonObject;
import com.ossez.wechat.common.bean.WxJsapiSignature;
import com.ossez.wechat.common.bean.WxNetCheckResult;
import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.enums.TicketType;
import com.ossez.wechat.common.enums.WxMpApiUrl;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatApiDomainIp;
import com.ossez.wechat.common.service.WxImgProcService;
import com.ossez.wechat.common.service.WxOAuth2Service;
import com.ossez.wechat.common.service.WxOcrService;
import com.ossez.wechat.common.service.WxService;
import com.ossez.wechat.common.util.http.MediaUploadRequestExecutor;
import com.ossez.wechat.common.util.http.RequestExecutor;
import com.ossez.wechat.common.util.http.RequestHttp;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.WxMpSemanticQuery;
import com.ossez.wechat.oa.bean.result.WxMpCurrentAutoReplyInfo;
import com.ossez.wechat.oa.bean.result.WxMpSemanticQueryResult;
import com.ossez.wechat.oa.bean.result.WxMpShortKeyResult;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.util.Map;
import java.util.concurrent.TimeUnit;

/**
 * 微信公众号API的Service.
 *
 * @author chanjarster
 */
public class WeChatPlatformService {

    final Logger log = LoggerFactory.getLogger(WeChatPlatformService.class);

    WeChatOfficialAccountApi weChatOfficialAccountApi;
    WeChatOfficialAccountService weChatOfficialAccountService;

    public WeChatOfficialAccountService getWeChatOfficialAccountService() {
        return weChatOfficialAccountService;
    }

    public void setWeChatOfficialAccountService(WeChatOfficialAccountService weChatOfficialAccountService) {
        this.weChatOfficialAccountService = weChatOfficialAccountService;
    }


    public WeChatPlatformService() {
        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new WeChatErrorInterceptor())
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS)).readTimeout(1000, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatConstant.ENDPOINT_WECHAT).client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.weChatOfficialAccountApi = retrofit.create(WeChatOfficialAccountApi.class);

    }

    public String getDomainIPs() throws WxErrorException {
        String xx = weChatOfficialAccountService.getAccessToken();

        WeChatApiDomainIp apiDomainIp = new WeChatApiDomainIp();

        try {
            apiDomainIp = weChatOfficialAccountApi.getDomainIPs(xx).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return apiDomainIp.getIpList().toString();
    }

    public String checkNetwork() throws WxErrorException {
        String xx = weChatOfficialAccountService.getAccessToken();

        String networkResponse = StringUtils.EMPTY;

        try {
            networkResponse = weChatOfficialAccountApi.checkNetwork(xx).blockingGet();
        } catch (HttpException ex) {
            log.warn("Access WeChat API return error.", ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> " + ex.getMessage());
        }
        return networkResponse;
    }

}
