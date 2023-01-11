package cn.binarywang.wx.miniapp.bean.shop.request;

import cn.binarywang.wx.miniapp.bean.shop.request.WxMaShopAfterSaleAddRequest.ProductInfosBean;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * @author liming1019
 * created on  2021/8/12
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxMaShopDeliverySendRequest implements Serializable {
  private static final long serialVersionUID = -4034672301224469057L;

  /**
   * order_id : 123456
   * out_order_id : xxxxx
   * openid : oTVP50O53a7jgmawAmxKukNlq3XI
   * finish_all_delivery : 0
   * delivery_list : [{"delivery_id":"SF","waybill_id":"23424324253"}]
   */

  @SerializedName("order_id")
  private Long orderId;
  @SerializedName("out_order_id")
  private String outOrderId;
  @SerializedName("openid")
  private String openid;
  @SerializedName("finish_all_delivery")
  private Integer finishAllDelivery;
  @SerializedName("delivery_list")
  private List<DeliveryListBean> deliveryList;
  @SerializedName("ship_done_time")
  private String shipDoneTme;

  @Data
  @Builder
  @NoArgsConstructor
  @AllArgsConstructor
  public static class DeliveryListBean implements Serializable {
    /**
     * delivery_id : SF
     * waybill_id : 23424324253
     */

    @SerializedName("delivery_id")
    private String deliveryId;
    @SerializedName("waybill_id")
    private String waybillId;
    @SerializedName("product_info_list")
    private List<ProductInfosBean> productInfoList;
  }
}
