package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 企业发表内容到客户的朋友圈 创建发表任务结果
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpAddMomentResult extends WxCpBaseResp {
  private static final long serialVersionUID = -7212260280504857210L;

  @SerializedName("jobid")
  private String jobId;

  /**
   * From json wx cp add moment result.
   *
   * @param json the json
   * @return the wx cp add moment result
   */
  public static WxCpAddMomentResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpAddMomentResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
