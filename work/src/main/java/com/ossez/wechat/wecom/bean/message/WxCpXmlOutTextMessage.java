package com.ossez.wechat.wecom.bean.message;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.xml.XStreamCDataConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Wx cp xml out text message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutTextMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = 2569239617185930232L;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  private String content;

  /**
   * Instantiates a new Wx cp xml out text message.
   */
  public WxCpXmlOutTextMessage() {
    this.msgType = WeChatConstant.XmlMsgType.TEXT;
  }

}
