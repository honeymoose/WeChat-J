package com.ossez.wechat.oa.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.common.util.xml.XStreamCDataConverter;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutTextMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -3972786455288763361L;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Content")
  @JacksonXmlCData
  private String content;

  public WxMpXmlOutTextMessage() {
    this.msgType = WeChatConstant.XmlMsgType.TEXT;
  }

}
