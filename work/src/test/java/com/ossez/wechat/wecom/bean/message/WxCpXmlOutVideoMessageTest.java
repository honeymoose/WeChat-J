package com.ossez.wechat.wecom.bean.message;

import org.testng.Assert;
import org.testng.annotations.Test;

/**
 * The type Wx cp xml out video message test.
 */
@Test
public class WxCpXmlOutVideoMessageTest {

  /**
   * Test.
   */
  public void test() {
    WxCpXmlOutVideoMessage m = new WxCpXmlOutVideoMessage();
    m.setMediaId("media_id");
    m.setTitle("title");
    m.setDescription("ddfff");
    m.setCreateTime(1122L);
    m.setFromUserName("fromUser");
    m.setToUserName("toUser");

    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[video]]></MsgType>"
      + "<Video>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Title><![CDATA[title]]></Title>"
      + "<Description><![CDATA[ddfff]]></Description>"
      + "</Video> "
      + "</xml>";
    System.out.println(m.toXml());
    Assert.assertEquals(m.toXml().replaceAll("\\s", ""), expected.replaceAll("\\s", ""));
  }

  /**
   * Test build.
   */
  public void testBuild() {
    WxCpXmlOutVideoMessage m = WxCpXmlOutMessage.VIDEO()
      .mediaId("media_id")
      .fromUser("fromUser")
      .toUser("toUser")
      .title("title")
      .description("ddfff")
      .build();
    String expected = "<xml>"
      + "<ToUserName><![CDATA[toUser]]></ToUserName>"
      + "<FromUserName><![CDATA[fromUser]]></FromUserName>"
      + "<CreateTime>1122</CreateTime>"
      + "<MsgType><![CDATA[video]]></MsgType>"
      + "<Video>"
      + "<MediaId><![CDATA[media_id]]></MediaId>"
      + "<Title><![CDATA[title]]></Title>"
      + "<Description><![CDATA[ddfff]]></Description>"
      + "</Video> "
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
