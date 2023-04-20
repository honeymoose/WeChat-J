package com.ossez.wechat.oa.api.test;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.PropertyNamingStrategy;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMsgService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountApi;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountServiceOkHttp;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.AuthenticationInterceptor;
import com.ossez.wechat.oa.api.impl.okhttp.interceptor.WeChatErrorInterceptor;
import okhttp3.ConnectionPool;
import okhttp3.OkHttpClient;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.ossez.wechat.common.util.xml.XStreamInitializer;
import com.ossez.wechat.common.config.ConfigStorage;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.jackson.JacksonConverterFactory;

public class ApiTestModule implements Module {
    private final Logger log = LoggerFactory.getLogger(this.getClass());
    private static final String TEST_CONFIG_XML = "test-config.xml";

    @Override
    public void configure(Binder binder) {
        try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {

            if (ObjectUtils.isEmpty(inputStream)) {
                throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
            }


            // Init WeChat config for testing
            Document document = new SAXReader().read(inputStream);
            TestConfigStorage config = new TestConfigStorage();
            config.setAppId(document.getRootElement().element("appId").getText());
            config.setSecret(document.getRootElement().element("secret").getText());
            config.setToken(document.getRootElement().element("token").getText());
            config.setOpenid(document.getRootElement().element("openid").getText());
            config.setAccessTokenLock(new ReentrantLock());

            // Init WeChat Service
            WeChatOfficialAccountService weChatOfficialAccountService = new WeChatOfficialAccountServiceOkHttp();
            weChatOfficialAccountService.setWxMpConfigStorage(config);
            weChatOfficialAccountService.addConfigStorage("another", config);

            // Init WeChatMsgService
            WeChatMsgService weChatMsgService = new WeChatMsgService(weChatOfficialAccountService);

            binder.bind(TestConfigStorage.class).toInstance(config);
            binder.bind(WeChatOfficialAccountService.class).toInstance(weChatOfficialAccountService);
            binder.bind(WeChatMsgService.class).toInstance(weChatMsgService);
        } catch (IOException e) {
            this.log.error(e.getMessage(), e);
        } catch (DocumentException e) {
            throw new RuntimeException(e);
        }
    }
}
