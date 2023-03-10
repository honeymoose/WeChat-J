package com.ossez.wechat.oa.bean.message;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

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
@XStreamAlias("SendPicsInfo")
@JacksonXmlRootElement(localName = "SendPicsInfo")
public class SendPicsInfo implements Serializable {
  private static final long serialVersionUID = -4572837013294199227L;

  @XStreamAlias("PicList")
  @JacksonXmlProperty(localName = "PicList")
  protected final List<Item> picList = new ArrayList<>();

  @XStreamAlias("Count")
  @JacksonXmlProperty(localName = "Count")
  private Long count;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  @XStreamAlias("item")
  @JacksonXmlRootElement(localName = "item")
  public static class Item implements Serializable {
    private static final long serialVersionUID = 7706235740094081194L;

    @XStreamAlias("PicMd5Sum")
    @XStreamConverter(value = XStreamCDataConverter.class)
    @JacksonXmlProperty(localName = "PicMd5Sum")
    @JacksonXmlCData
    private String picMd5Sum;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }

  }
}
