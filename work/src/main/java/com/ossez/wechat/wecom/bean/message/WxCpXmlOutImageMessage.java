package com.ossez.wechat.wecom.bean.message;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.xml.XStreamMediaIdConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Wx cp xml out image message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutImageMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = -1099446240667237313L;

  @XStreamAlias("Image")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  private String mediaId;

  /**
   * Instantiates a new Wx cp xml out image message.
   */
  public WxCpXmlOutImageMessage() {
    this.msgType = WeChatConstant.XmlMsgType.IMAGE;
  }

}
