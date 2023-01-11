package com.ossez.wechat.common.bean;

import java.io.Serializable;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

/**
 * 卡券Api签名.
 *
 * @author YuJian
 * @version 15/11/8
 */
@Data
public class WxCardApiSignature implements Serializable {
  private static final long serialVersionUID = 158176707226975979L;

  private String appId;

  private String cardId;

  private String cardType;

  private String locationId;

  private String code;

  private String openId;

  private Long timestamp;

  private String nonceStr;

  private String signature;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }
}
