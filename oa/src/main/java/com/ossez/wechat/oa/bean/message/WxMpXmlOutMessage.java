package com.ossez.wechat.oa.bean.message;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.ossez.wechat.common.config.ConfigStorage;
import com.ossez.wechat.common.util.crypto.WxCryptUtil;
import com.ossez.wechat.common.util.xml.XStreamCDataConverter;
import com.ossez.wechat.oa.builder.outxml.*;
import com.ossez.wechat.oa.util.crypto.WxMpCryptUtil;
import com.ossez.wechat.oa.util.xml.XStreamTransformer;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;

import java.io.Serializable;

@Data
@XStreamAlias("xml")
@JacksonXmlRootElement(localName = "xml")
public abstract class WxMpXmlOutMessage implements Serializable {
  private static final long serialVersionUID = -381382011286216263L;

  @XStreamAlias("ToUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "ToUserName")
  @JacksonXmlCData
  protected String toUserName;

  @XStreamAlias("FromUserName")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "FromUserName")
  @JacksonXmlCData
  protected String fromUserName;

  @XStreamAlias("CreateTime")
  @JacksonXmlProperty(localName = "CreateTime")
  protected Long createTime;

  @XStreamAlias("MsgType")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgType")
  @JacksonXmlCData
  protected String msgType;


  @XStreamAlias("Encrypt")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Encrypt")
  @JacksonXmlCData
  private String encrypt;

  @XStreamAlias("MsgSignature")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MsgSignature")
  @JacksonXmlCData
  private String msgSignature;

  @XStreamAlias("TimeStamp")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "TimeStamp")
  @JacksonXmlCData
  private String timeStamp;

  @XStreamAlias("Nonce")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "Nonce")
  @JacksonXmlCData
  private String nonce;

  /**
   * 获得文本消息builder
   */
  public static TextBuilder TEXT() {
    return new TextBuilder();
  }

  /**
   * 获得图片消息builder
   */
  public static ImageBuilder IMAGE() {
    return new ImageBuilder();
  }

  /**
   * 获得语音消息builder
   */
  public static VoiceBuilder VOICE() {
    return new VoiceBuilder();
  }

  /**
   * 获得视频消息builder
   */
  public static VideoBuilder VIDEO() {
    return new VideoBuilder();
  }

  /**
   * 获得音乐消息builder
   */
  public static MusicBuilder MUSIC() {
    return new MusicBuilder();
  }

  /**
   * 获得图文消息builder
   */
  public static NewsBuilder NEWS() {
    return new NewsBuilder();
  }

  /**
   * 获得客服消息builder
   */
  public static TransferCustomerServiceBuilder TRANSFER_CUSTOMER_SERVICE() {
    return new TransferCustomerServiceBuilder();
  }

  /**
   * 获得设备消息builder
   */
  public static DeviceBuilder DEVICE() {
      return new DeviceBuilder();
  }

  @SuppressWarnings("unchecked")
  public String toXml() {
    return XStreamTransformer.toXml((Class<WxMpXmlOutMessage>) this.getClass(), this);
  }

  /**
   * 转换成加密的结果
   */
  public WxMpXmlOutMessage toEncrypted(ConfigStorage configStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(configStorage);
    WxCryptUtil.EncryptContext context = pc.encryptContext(plainXml);
    WxMpXmlOutMessage res = new WxMpXmlOutMessage() {};
    res.setNonce(context.getNonce());
    res.setEncrypt(context.getEncrypt());
    res.setTimeStamp(context.getTimeStamp());
    res.setMsgSignature(context.getSignature());
    return res;
  }

  /**
   * 转换成加密的xml格式
   */
  public String toEncryptedXml(ConfigStorage configStorage) {
    String plainXml = toXml();
    WxMpCryptUtil pc = new WxMpCryptUtil(configStorage);
    return pc.encrypt(plainXml);
  }
}
