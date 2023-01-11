package cn.binarywang.wx.miniapp.bean.shop.request;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * @author liming1019
 * created on  2021/8/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopAfterSaleGetRequest implements Serializable {
  private static final long serialVersionUID = -1275475147400719521L;

  /**
   * order_id : 32434234
   * out_order_id : xxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   */

  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("out_order_id")
  private String outOrderId;
  @SerializedName("openid")
  private String openid;
}
