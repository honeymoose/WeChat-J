package me.chanjar.weixin.cp.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Wx cp xml out image message test.
 */
@Test
public class WxCpXmlOutImageMessageTest {

  /**
   * Test.
   */
  public void test() {
    WxCpXmlOutImageMessage m = new WxCpXmlOutImageMessage();
    m.setMediaId("ddfefesfsdfef");
    m.setCreateTime(1122L);
    m.setFromUserName("from");
    m.setToUserName("to");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[image]]></MsgType>"
      + "<Image><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Image>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  /**
   * Test build.
   */
  public void testBuild() {
    WxCpXmlOutImageMessage m = WxCpXmlOutMessage.IMAGE().mediaId("ddfefesfsdfef").fromUser("from").toUser("to").build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[to]]></ToUserName>"
      + "<FromUserName><![CDATA[from]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[image]]></MsgType>"
      + "<Image><MediaId><![CDATA[ddfefesfsdfef]]></MediaId></Image>"
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(
      m
        .toXml()
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", ""),
      expected
        .replaceAll("\\s", "")
        .replaceAll("<CreateTime>.*?</CreateTime>", "")
    );

  }
}
