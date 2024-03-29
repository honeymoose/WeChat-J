package com.ossez.wechat.oa.api.impl.okhttp;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.exception.DataStructureException;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.req.DataCubeRequest;
import com.ossez.wechat.common.model.res.DataCubeArticle;
import com.ossez.wechat.common.model.res.DataCubeUser;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.AuthenticationInterceptor;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.WeChatErrorInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.lang3.ObjectUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import retrofit2.HttpException;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.concurrent.TimeUnit;

/**
 * WeChat Official Account Platform DataCube
 *
 * @author YuCheng
 */
public class WeChatDataCubeService {
    private final Logger log = LoggerFactory.getLogger(WeChatDataCubeService.class);

    private WeChatOfficialAccountApi weChatOfficialAccountApi;

    public WeChatDataCubeService(WeChatOfficialAccountService weChatOfficialAccountService) {
        String accessToken = null;
        try {
            accessToken = weChatOfficialAccountService.getAccessToken();
        } catch (WxErrorException e) {
            throw new RuntimeException(e);
        }

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        OkHttpClient client = new OkHttpClient.Builder().addInterceptor(new AuthenticationInterceptor(accessToken)).addInterceptor(new WeChatErrorInterceptor()).connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS)).readTimeout(1000, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatConstant.ENDPOINT_WECHAT).client(client).addConverterFactory(JacksonConverterFactory.create(mapper)).addCallAdapterFactory(RxJava2CallAdapterFactory.create()).build();

        this.weChatOfficialAccountApi = retrofit.create(WeChatOfficialAccountApi.class);

    }


    /**
     * Get user summary
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws WxErrorException
     */
    public DataCubeUser getUserSummary(LocalDateTime beginDate, LocalDateTime endDate) throws WxErrorException {
        if (!(ObjectUtils.isNotEmpty(beginDate) && ObjectUtils.isNotEmpty(endDate) && endDate.isAfter(beginDate))) {
            log.warn("The begin date [{}] Or end date [{}] was null or ender date greater than begin date.", beginDate, endDate);
            throw new DataStructureException("Query data range error ");
        }

        try {

            DataCubeRequest dataCubeRequest = new DataCubeRequest();
            dataCubeRequest.setBeginDate(beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            dataCubeRequest.setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

            return weChatOfficialAccountApi.getUserSummary(dataCubeRequest).blockingGet();
        } catch (HttpException ex) {
            log.warn("Call WeChat API with error: " + ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }

        }
        return null;
    }

    public DataCubeUser getUserCumulate(LocalDateTime beginDate, LocalDateTime endDate) throws WxErrorException {
        if (!(ObjectUtils.isNotEmpty(beginDate) && ObjectUtils.isNotEmpty(endDate) && endDate.isAfter(beginDate))) {
            log.warn("The begin date [{}] Or end date [{}] was null or ender date greater than begin date.", beginDate, endDate);
            throw new DataStructureException("Query data range error ");
        }

        try {
            DataCubeRequest dataCubeRequest = new DataCubeRequest();
            dataCubeRequest.setBeginDate(beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
            dataCubeRequest.setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

            return weChatOfficialAccountApi.getUserCumulate(dataCubeRequest).blockingGet();
        } catch (HttpException ex) {
            log.warn("Call WeChat API with error: " + ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }

        }
        return null;
    }

    /**
     *
     * @param beginDate
     * @param endDate
     * @return
     * @throws WxErrorException
     */
    public DataCubeArticle getArticleSummary(LocalDateTime beginDate, LocalDateTime endDate) throws WxErrorException {
        try {
            return weChatOfficialAccountApi.getarticlesummary(getDataCubeRequest(beginDate, endDate)).blockingGet();
        } catch (HttpException ex) {
            log.warn("Call WeChat API with error: " + ex);
            if (ex.code() == 400) {
                throw new WxErrorException(ex);
            }
        }
        return null;
    }

    private DataCubeRequest getDataCubeRequest(LocalDateTime beginDate, LocalDateTime endDate) throws DataStructureException {
        if (!(ObjectUtils.isNotEmpty(beginDate) && ObjectUtils.isNotEmpty(endDate) && endDate.isAfter(beginDate))) {
            log.warn("The begin date [{}] Or end date [{}] was null or ender date greater than begin date.", beginDate, endDate);
            throw new DataStructureException("Query data range error ");
        }

        DataCubeRequest dataCubeRequest = new DataCubeRequest();
        dataCubeRequest.setBeginDate(beginDate.format(DateTimeFormatter.ISO_LOCAL_DATE));
        dataCubeRequest.setEndDate(endDate.format(DateTimeFormatter.ISO_LOCAL_DATE));

        return dataCubeRequest;

    }


}
