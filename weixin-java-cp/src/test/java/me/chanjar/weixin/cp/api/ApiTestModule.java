package me.chanjar.weixin.cp.api;

import com.google.inject.Binder;
import com.google.inject.Module;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.extern.slf4j.Slf4j;
import me.chanjar.weixin.common.exception.WxRuntimeException;
import me.chanjar.weixin.common.util.xml.XStreamInitializer;
import me.chanjar.weixin.cp.api.impl.WxCpServiceImpl;
import me.chanjar.weixin.cp.config.impl.WxCpDefaultConfigImpl;

import java.io.IOException;
import java.io.InputStream;

/**
 * The type Api test module.
 */
@Slf4j
public class ApiTestModule implements Module {
  private static final String TEST_CONFIG_XML = "test-config.xml";
  /**
   * The Config.
   */
  protected WxXmlCpInMemoryConfigStorage config;

  private static <T> T fromXml(Class<T> clazz, InputStream is) {
    XStream xstream = XStreamInitializer.getInstance();
    xstream.alias("xml", clazz);
    xstream.processAnnotations(clazz);
    return (T) xstream.fromXML(is);
  }

  @Override
  public void configure(Binder binder) {
    try (InputStream inputStream = ClassLoader.getSystemResourceAsStream(TEST_CONFIG_XML)) {
      if (inputStream == null) {
        throw new WxRuntimeException("测试配置文件【" + TEST_CONFIG_XML + "】未找到，请参照test-config-sample.xml文件生成");
      }

      config = fromXml(WxXmlCpInMemoryConfigStorage.class, inputStream);
      WxCpService wxService = new WxCpServiceImpl();
      wxService.setWxCpConfigStorage(config);

      binder.bind(WxCpService.class).toInstance(wxService);
      binder.bind(WxXmlCpInMemoryConfigStorage.class).toInstance(config);
    } catch (IOException e) {
      log.error(e.getMessage(), e);
    }
  }

  /**
   * The type Wx xml cp in memory config storage.
   */
  @Data
  @EqualsAndHashCode(callSuper = true)
  @XStreamAlias("xml")
  public static class WxXmlCpInMemoryConfigStorage extends WxCpDefaultConfigImpl {
    private static final long serialVersionUID = -4521839921547374822L;

    /**
     * The User id.
     */
    protected String userId;
    /**
     * The Department id.
     */
    protected String departmentId;
    /**
     * The Tag id.
     */
    protected String tagId;
    /**
     * The External user id.
     */
    protected String externalUserId;
  }

}
