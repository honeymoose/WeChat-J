package com.ossez.wechat.open.test;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.ossez.wechat.open.api.WxOpenComponentService;
import com.ossez.wechat.open.api.WxOpenMaService;
import com.ossez.wechat.open.api.WeChatOfficialAccountService;
import com.ossez.wechat.open.api.WeChatOpenService;
import com.ossez.wechat.open.api.impl.WeChatOpenServiceImpl;
import com.thoughtworks.xstream.XStream;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.xml.XStreamInitializer;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

public class ApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      TestConfigStorage config = this.fromXml(TestConfigStorage.class, inputStream);
      WeChatOpenService service = new WeChatOpenServiceImpl<>();

      service.setWxOpenConfigStorage(config);

      binder.bind(TestConfigStorage.class).toInstance(config);
      binder.bind(WeChatOpenService.class).toInstance(service);
      binder.bind(WxOpenComponentService.class).toInstance(service.getWxOpenComponentService());

      if (config.getTestMpAppId() != null && !config.getTestMpAppId().isEmpty()) {
        //如果配置了测试公众号,则构建公众号服务依赖
        binder.bind(WeChatOfficialAccountService.class).toInstance(service.getWxOpenComponentService().getWxMpServiceByAppid(config.getTestMpAppId()));
      } else {
        log.warn("建议参照参照 test-config-sample.xml 配置测试公众号");
      }

      if (config.getTestMaAppId() != null && !config.getTestMaAppId().isEmpty()) {
        //如果配置了测试小程序,则构建小程序服务依赖
        binder.bind(WxOpenMaService.class).toInstance(service.getWxOpenComponentService().getWxMaServiceByAppid(config.getTestMaAppId()));
      } else {
        log.warn("建议参照参照 test-config-sample.xml 配置测试小程序");
      }

    } catch (IOException e) {
      this.log.error(e.getMessage(), e);
    }
  }

  @SuppressWarnings("unchecked")
  private <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

}
