package cn.binarywang.wx.miniapp.message;

import com.ossez.wechat.common.constant.WeChatConstant;
import org.testng.annotations.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class WxMaXmlOutMessageTest {

  @Test
  public void testToXml() {
    WxMaXmlOutMessage message = WxMaXmlOutMessage.builder()
      .fromUserName("1")
      .toUserName("2")
      .msgType(WeChatConstant.XmlMsgType.TRANSFER_CUSTOMER_SERVICE)
      .createTime(System.currentTimeMillis() / 1000)
      .build();

    assertThat(message.toXml()).isNotEmpty();
    System.out.println(message.toXml());
  }
}
