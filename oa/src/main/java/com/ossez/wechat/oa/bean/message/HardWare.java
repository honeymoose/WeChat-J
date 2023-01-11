package com.ossez.wechat.oa.bean.message;

import java.io.Serializable;

import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlCData;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlProperty;
import com.fasterxml.jackson.dataformat.xml.annotation.JacksonXmlRootElement;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.thoughtworks.xstream.annotations.XStreamConverter;
import lombok.Data;
import com.ossez.wechat.common.util.xml.XStreamCDataConverter;

/**
 * <pre>
 *  Created by BinaryWang on 2017/5/4.
 * </pre>
 *
 * @author Binary Wang
 */
@Data
@XStreamAlias("HardWare")
@JacksonXmlRootElement(localName = "HardWare")
public class HardWare implements Serializable {
  private static final long serialVersionUID = -1295785297354896461L;

  /**
   * 消息展示，目前支持myrank(排行榜)
   */
  @XStreamAlias("MessageView")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MessageView")
  @JacksonXmlCData
  private String messageView;

  /**
   * 消息点击动作，目前支持ranklist(点击跳转排行榜)
   */
  @XStreamAlias("MessageAction")
  @XStreamConverter(value = XStreamCDataConverter.class)
  @JacksonXmlProperty(localName = "MessageAction")
  @JacksonXmlCData
  private String messageAction;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
