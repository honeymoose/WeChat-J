package com.ossez.wechat.oa.bean.card.membercard;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 创建会员卡请求对象.
 *
 * @author yuanqixun
 */
@Data
public class MemberCardCreateRequest implements Serializable {
  private static final long serialVersionUID = -1044836608401698097L;

  @SerializedName("card_type")
  private String cardType = "MEMBER_CARD";

  @SerializedName("member_card")
  private MemberCard memberCard;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
