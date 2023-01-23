package com.ossez.wechat.oa.api.impl.okhttp;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.constant.WeChatApiParameter;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatAccessToken;
import com.ossez.wechat.common.util.http.HttpType;
import com.ossez.wechat.common.util.http.okhttp.OkHttpProxyInfo;
import com.ossez.wechat.oa.api.impl.BaseWeChatOfficialAccountServiceImpl;
import com.ossez.wechat.oa.config.WxMpConfigStorage;
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
 * okhttp实现.
 *
 * @author someone
 */
public class WeChatOfficialAccountServiceOkHttp extends BaseWeChatOfficialAccountServiceImpl<OkHttpClient, OkHttpProxyInfo> {

    final Logger log = LoggerFactory.getLogger(WeChatOfficialAccountServiceOkHttp.class);

    WeChatOfficialAccountApi weChatOfficialAccountApi;
    private OkHttpClient httpClient;
    private OkHttpProxyInfo httpProxy;

    @Override
    public OkHttpClient getRequestHttpClient() {
        return httpClient;
    }

    @Override
    public OkHttpProxyInfo getRequestHttpProxy() {
        return httpProxy;
    }

    @Override
    public HttpType getRequestType() {
        return HttpType.OK_HTTP;
    }

    @Override
    public synchronized String getAccessToken(boolean forceRefresh) throws WxErrorException {

        WeChatAccessToken weChatAccessToken = new WeChatAccessToken();

        final WxMpConfigStorage config = this.getWxMpConfigStorage();
        if (!config.isAccessTokenExpired() && !forceRefresh) {
            return config.getAccessToken();
        }

        try {
            weChatAccessToken = weChatOfficialAccountApi.getAccessToken(WeChatApiParameter.ACCESS_TOKEN_GRANT_TYPE_CLIENT_CREDENTIAL, config.getAppId(), config.getSecret()).blockingGet();
        }
        catch (HttpException ex) {
            log.warn("Access WeChat API return error.",ex);
            if(ex.code() == 400) {
                throw new WxErrorException(ex);
            }
            System.out.println(">>>>>>>>>>>>>>>>>>>> "+ex.getMessage());
        }


//      Request request = new Request.Builder().url(url).get().build();
//      Response response = getRequestHttpClient().newCall(request).execute();
//      return this.extractAccessToken(Objects.requireNonNull(response.body().toString()));
//        } catch (Exception e) {
//            throw new WxRuntimeException(e);
//        }

        return weChatAccessToken.getAccessToken();
    }

    @Override
    public void initHttp() {

        ObjectMapper mapper = new ObjectMapper();
        mapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
        mapper.setSerializationInclusion(JsonInclude.Include.NON_NULL);
        mapper.setPropertyNamingStrategy(PropertyNamingStrategy.SNAKE_CASE);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(new WeChatErrorInterceptor())
                .connectionPool(new ConnectionPool(5, 1, TimeUnit.SECONDS)).readTimeout(1000, TimeUnit.SECONDS).build();

        Retrofit retrofit = new Retrofit.Builder().baseUrl(WeChatConstant.ENDPOINT_OFFICIAL_ACCOUNT).client(client)
                .addConverterFactory(JacksonConverterFactory.create(mapper))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        this.weChatOfficialAccountApi = retrofit.create(WeChatOfficialAccountApi.class);


//    WxMpConfigStorage wxMpConfigStorage = getWxMpConfigStorage();
//    //设置代理
//    if (wxMpConfigStorage.getHttpProxyHost() != null && wxMpConfigStorage.getHttpProxyPort() > 0) {
//      httpProxy = OkHttpProxyInfo.httpProxy(wxMpConfigStorage.getHttpProxyHost(),
//        wxMpConfigStorage.getHttpProxyPort(),
//        wxMpConfigStorage.getHttpProxyUsername(),
//        wxMpConfigStorage.getHttpProxyPassword());
//    }
//
//    OkHttpClient.Builder clientBuilder = new OkHttpClient.Builder();
//    if (httpProxy != null) {
//      clientBuilder.proxy(getRequestHttpProxy().getProxy());
//
//      //设置授权
//      clientBuilder.authenticator(new Authenticator() {
//        @Override
//        public Request authenticate(Route route, Response response) throws IOException {
//          String credential = Credentials.basic(httpProxy.getProxyUsername(), httpProxy.getProxyPassword());
//          return response.request().newBuilder()
//            .header("Authorization", credential)
//            .build();
//        }
//      });
//    }
//    httpClient = clientBuilder.build();
    }

}
