package com.ossez.wechat.wecom.bean.license.order;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 订单创建结果
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95644
 *
 * @author Totoro  created on  2022-6-27 11:26:36
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseCreateOrderResp extends WxCpBaseResp {
  private static final long serialVersionUID = 6644560301282598903L;

  @SerializedName("order_id")
  private String orderId;


  /**
   * From json wx cp tp license create order resp.
   *
   * @param json the json
   * @return the wx cp tp license create order resp
   */
  public static WxCpTpLicenseCreateOrderResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseCreateOrderResp.class);
  }


}
