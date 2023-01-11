package com.ossez.wechat.oa.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Builder;
import lombok.Data;
import com.ossez.wechat.common.bean.ToJson;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 客户信息dto
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/11/011
 */

@Data
@Builder
public class WxMpAddGuideBuyerInfo implements ToJson, Serializable {
  private static final long serialVersionUID = -1703303970552268691L;

  /**
   * 客户的openId
   */
  @SerializedName("openid")
  private String openid;

  /**
   * 客户的名称
   */
  @SerializedName("buyer_nickname")
  private String nickname;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpAddGuideBuyerInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpAddGuideBuyerInfo.class);
  }
}
