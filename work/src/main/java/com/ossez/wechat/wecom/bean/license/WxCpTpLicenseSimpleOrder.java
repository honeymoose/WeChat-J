package com.ossez.wechat.wecom.bean.license;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * The type Wx cp tp license simple order.
 *
 * @author Totoro  created on  2022/6/27 11:38
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseSimpleOrder implements Serializable {

  private static final long serialVersionUID = -4094302825442292644L;

  @SerializedName("order_id")
  private String orderId;
  @SerializedName("order_type")
  private Integer orderType;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
