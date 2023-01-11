package com.ossez.wechat.oa.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * .
 * @author leeis
 * created on  2018/12/29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class GiftCardCreateRequest extends AbstractCardCreateRequest implements Serializable {
  private static final long serialVersionUID = 1283655452584811858L;

  @SerializedName("card_type")
  private String cardType = "GIFT";

  private GiftCard gift;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
