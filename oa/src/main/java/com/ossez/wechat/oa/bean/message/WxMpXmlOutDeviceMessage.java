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
public class WxMpXmlOutDeviceMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = -3093843149649157587L;

  @XStreamAlias("DeviceType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "DeviceType")
  @JacksonXmlCData
  private String deviceType;

  @XStreamAlias("DeviceID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "DeviceID")
  @JacksonXmlCData
  private String deviceId;

  @XStreamAlias("Content")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Content")
  @JacksonXmlCData
  private String content;

  @XStreamAlias("SessionID")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "SessionID")
  @JacksonXmlCData
  private String sessionId;

  public WxMpXmlOutDeviceMessage() {
    this.msgType = WeChatConstant.XmlMsgType.DEVICE_TEXT;
  }
}
