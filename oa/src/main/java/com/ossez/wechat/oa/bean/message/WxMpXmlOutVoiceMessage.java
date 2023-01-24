package com.ossez.wechat.oa.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.xml.XStreamMediaIdConverter;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutVoiceMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 240367390249860551L;

  @XStreamAlias("Voice")
  @XStreamConverter(value = XStreamMediaIdConverter.class)
  @JacksonXmlProperty(localName = "Voice")
  @JacksonXmlCData
  private String mediaId;

  public WxMpXmlOutVoiceMessage() {
    this.msgType = WeChatConstant.XmlMsgType.VOICE;
  }

}
