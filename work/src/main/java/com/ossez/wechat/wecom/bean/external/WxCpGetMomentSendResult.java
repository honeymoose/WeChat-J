package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.moment.CustomerItem;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈 获取客户朋友圈发表后的可见客户列表
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentSendResult extends WxCpBaseResp {
  private static final long serialVersionUID = -5782811995184523379L;

  @SerializedName("next_cursor")
  private String nextCursor;
  @SerializedName("customer_list")
  private List<CustomerItem> customerList;

  /**
   * From json wx cp get moment send result.
   *
   * @param json the json
   * @return the wx cp get moment send result
   */
  public static WxCpGetMomentSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentSendResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
