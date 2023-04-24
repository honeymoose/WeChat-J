package com.ossez.wechat.oa.api.test;

import com.google.inject.AbstractModule;
import com.google.inject.Guice;
import com.google.inject.Injector;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatDataCubeService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatMsgService;
import com.ossez.wechat.oa.api.impl.okhttp.WeChatOfficialAccountServiceOkHttp;
import org.apache.commons.lang3.ObjectUtils;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.io.SAXReader;
import org.junit.jupiter.api.BeforeAll;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Init Guice DI
 *
 * @author YuCheng
 */
public class TestBase {
    private static final Logger log = LoggerFactory.getLogger(TestBase.class);
    private static final String TEST_CONFIG_XML = "test-config.xml";

    @BeforeAll
    public void setup() {
        injector.injectMembers(this);
    }

    private static final Injector injector = Guice.createInjector(new AbstractModule() {
        @Override
        public void configure() {
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
                WeChatDataCubeService weChatDataCubeService = new WeChatDataCubeService(weChatOfficialAccountService);

                bind(TestConfigStorage.class).toInstance(config);
                bind(WeChatOfficialAccountService.class).toInstance(weChatOfficialAccountService);
                bind(WeChatMsgService.class).toInstance(weChatMsgService);
                bind(WeChatDataCubeService.class).toInstance(weChatDataCubeService);
            } catch (IOException e) {
                log.error(e.getMessage(), e);
            } catch (DocumentException e) {
                throw new RuntimeException(e);
            }
        }

    });



}
