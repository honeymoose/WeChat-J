package com.ossez.wechat.wecom.bean.order;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Getter;
import lombok.Setter;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.util.List;

/**
 * 应用版本付费订单列表
 *
 * @author leiguoqing  created on  2022年4月24日
 */
@Getter
@Setter
public class WxCpTpOrderListGetResult extends WxCpBaseResp {

  /**
   * The constant serialVersionUID.
   */
  private static final long serialVersionUID = -5028321625140879571L;

  /**
   * 订单列表
   */
  @SerializedName("order_list")
  private List<WxCpTpOrderDetails> orderList;


  /**
   * From json wx cp tp order list get result.
   *
   * @param json the json
   * @return the wx cp tp order list get result
   */
  public static WxCpTpOrderListGetResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpOrderListGetResult.class);
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
