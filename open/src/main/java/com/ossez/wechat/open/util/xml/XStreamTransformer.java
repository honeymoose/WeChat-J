package com.ossez.wechat.open.util.xml;

import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.thoughtworks.xstream.XStream;
import com.ossez.wechat.common.util.xml.XStreamInitializer;
import com.ossez.wechat.open.bean.message.WxOpenXmlMessage;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class XStreamTransformer {
  private static final Map<Class<?>, XStream> CLASS_2_XSTREAM_INSTANCE = new HashMap<>();

  static {
    registerClass(WxOpenXmlMessage.class);
  }

  /**
   * xml -> pojo.
   */
  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, String xml) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(xml);
    return object;
  }

  @SuppressWarnings("unchecked")
  public static <T> T fromXml(Class<T> clazz, InputStream is) {
    T object = (T) CLASS_2_XSTREAM_INSTANCE.get(clazz).fromXML(is);
    return object;
  }

  /**
   * pojo -> xml.
   */
  public static <T> String toXml(Class<T> clazz, T object) {
    return CLASS_2_XSTREAM_INSTANCE.get(clazz).toXML(object);
  }

  /**
   * 注册扩展消息的解析器.
   *
   * @param clz     类型
   * @param xStream xml解析器
   */
  public static void register(Class<?> clz, XStream xStream) {
    CLASS_2_XSTREAM_INSTANCE.put(clz, xStream);
  }

  /**
   * 会自动注册该类及其子类.
   *
   * @param clz 要注册的类
   */
  private static void registerClass(Class<?> clz) {
    XStream xstream = XStreamInitializer.getInstance();

    xstream.processAnnotations(clz);
    xstream.processAnnotations(getInnerClasses(clz));
    if (clz.equals(WxOpenXmlMessage.class)) {
      // 操蛋的微信，模板消息推送成功的消息是MsgID，其他消息推送过来是MsgId
      xstream.aliasField("MsgID", WxOpenXmlMessage.class, "msgId");
    }

    register(clz, xstream);
  }

  private static Class<?>[] getInnerClasses(Class<?> clz) {
    Class<?>[] innerClasses = clz.getClasses();
    if (innerClasses == null) {
      return null;
    }

    List<Class<?>> result = new ArrayList<>();
    result.addAll(Arrays.asList(innerClasses));
    for (Class<?> inner : innerClasses) {
      Class<?>[] innerClz = getInnerClasses(inner);
      if (innerClz == null) {
        continue;
      }

      result.addAll(Arrays.asList(innerClz));
    }

    return result.toArray(new Class<?>[0]);
  }
}
