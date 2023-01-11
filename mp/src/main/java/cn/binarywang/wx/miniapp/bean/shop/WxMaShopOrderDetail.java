package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMaShopOrderDetail implements Serializable {
  private static final long serialVersionUID = 3325843289672341160L;


  /**
   * 推广员、分享员信息
   */
  @SerializedName("promotion_info")
  private WxMaShopPromotionInfo promotionInfo;

  /**
   * 下单商品信息
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("product_infos")
  private List<WxMaShopProductInfo> productInfos;

  /**
   * 支付信息 (当作为返回结果，payorder时action_type!=6时存在)
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("pay_info")
  private WxMaShopPayInfo payInfo;

  /**
   * 价格信息
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("price_info")
  private WxMaShopPriceInfo priceInfo;

  // 以下字段仅作为结果返回展示字段
  /**
   * payorder时action_type=6时存在
   */
  @SerializedName("multi_pay_info")
  private List<WxMaShopPayInfo> multiPayInfo;

  /**
   * 必须调过发货接口才会存在这个字段
   */
  @SerializedName("delivery_detail")
  private WxMaShopDeliveryDetail deliveryDetail;
}
