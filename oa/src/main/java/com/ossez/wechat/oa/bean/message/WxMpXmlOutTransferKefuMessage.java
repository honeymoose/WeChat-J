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

import java.io.Serializable;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
@EqualsAndHashCode(callSuper = true)
public class WxMpXmlOutTransferKefuMessage extends WxMpXmlOutMessage {
  private static final long serialVersionUID = 1850903037285841322L;

  @XStreamAlias("TransInfo")
  @JacksonXmlProperty(localName = "TransInfo")
  protected TransInfo transInfo;

  public WxMpXmlOutTransferKefuMessage() {
    this.msgType = WeChatConstant.WeChatMsgType.TRANSFER_CUSTOMER_SERVICE;
  }

  @Data
  @XStreamAlias("TransInfo")
  @JacksonXmlRootElement(localName = "TransInfo")
  public static class TransInfo implements Serializable {
    private static final long serialVersionUID = -6317885617135706056L;

    @XStreamAlias("KfAccount")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "KfAccount")
    @JacksonXmlCData
    private String kfAccount;

  }
}
