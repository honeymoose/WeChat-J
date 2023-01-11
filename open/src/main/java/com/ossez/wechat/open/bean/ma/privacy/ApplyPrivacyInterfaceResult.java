package com.ossez.wechat.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.Getter;
import lombok.Setter;

/**
 * 获取接口列表 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class ApplyPrivacyInterfaceResult extends WxOpenResult {

  /**
   * 审核ID
   */
  @SerializedName("audit_id")
  private Long auditId;

}
