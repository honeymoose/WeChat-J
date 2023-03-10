package com.ossez.wechat.pay.testbase;

import com.ossez.wechat.pay.config.WxPayConfig;
import com.ossez.wechat.pay.service.WxPayService;
import com.ossez.wechat.pay.service.impl.WxPayServiceImpl;
import com.google.inject.Binder;
import com.google.inject.Module;
import com.ossez.wechat.common.exception.WxRuntimeException;
import com.ossez.wechat.common.util.xml.XStreamInitializer;
import com.thoughtworks.xstream.XStream;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Api test module.
 */
public class ApiTestModule implements Module {
  private final Logger log = LoggerFactory.getLogger(this.getClass());
  private static final String TEST_CONFIG_XML = "test-config.xml";

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      XmlWxPayConfig config = this.fromXml(XmlWxPayConfig.class, inputStream);
      config.setIfSaveApiData(true);
      WxPayService wxService = new WxPayServiceImpl();
      wxService.setConfig(config);

      binder.bind(WxPayService.class).toInstance(wxService);
      binder.bind(WxPayConfig.class).toInstance(config);
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
