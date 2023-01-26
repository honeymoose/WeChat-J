package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * 应用市场延长试用期结果
 *
 * @author leiguoqing  created on  2022年4月24日
 */
@Getter
@Setter
public class WxCpTpProlongTryResult extends WxCpBaseResp {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -5028321625140879571L;

  /**
   * 延长后的试用到期时间（秒级时间戳）
   */
  @SerializedName("try_end_time")
  private Long tryEndTime;


  /**
   * From json wx cp tp order list get result.
   *
   * @param json the json
   * @return the wx cp tp order list get result
   */
  public static WxCpTpProlongTryResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpProlongTryResult.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  @Override
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
