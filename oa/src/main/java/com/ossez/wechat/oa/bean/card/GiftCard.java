package com.ossez.wechat.oa.bean.card;

import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * created on  2018/12/29
 */
@Data
public final class GiftCard extends Card implements Serializable {

  private static final long serialVersionUID = -6168739707511792266L;

  /**
   * 兑换券专用，填写兑换内容的名称。
   */
  private String gift;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public static GiftCard fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, GiftCard.class);
  }
}
