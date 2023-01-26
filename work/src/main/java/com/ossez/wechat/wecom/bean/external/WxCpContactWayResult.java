package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 「联系我」方式 处理结果
 *
 * @author 爱因斯唐
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpContactWayResult extends WxCpBaseResp {
  private static final long serialVersionUID = -2612867517869192015L;

  @SerializedName("config_id")
  private String configId;

  @SerializedName("qr_code")
  private String qrCode;

  /**
   * From json wx cp contact way result.
   *
   * @param json the json
   * @return the wx cp contact way result
   */
  public static WxCpContactWayResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpContactWayResult.class);
  }
}
