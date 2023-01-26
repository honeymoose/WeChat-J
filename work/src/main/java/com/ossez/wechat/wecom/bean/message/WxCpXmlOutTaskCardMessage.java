package com.ossez.wechat.wecom.bean.message;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.xml.XStreamReplaceNameConverter;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * The type Wx cp xml out task card message.
 */
@XStreamAlias("xml")
@Data
@EqualsAndHashCode(callSuper = false)
public class WxCpXmlOutTaskCardMessage extends WxCpXmlOutMessage {
  private static final long serialVersionUID = 7028014900972827324L;

  @XStreamAlias("TaskCard")
  @XStreamConverter(value = XStreamReplaceNameConverter.class)
  private String replaceName;

  /**
   * Instantiates a new Wx cp xml out task card message.
   */
  public WxCpXmlOutTaskCardMessage() {
    this.msgType = WeChatConstant.XmlMsgType.UPDATE_TASKCARD;
  }

}
