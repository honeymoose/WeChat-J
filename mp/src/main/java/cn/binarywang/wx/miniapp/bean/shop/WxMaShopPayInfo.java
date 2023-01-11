package cn.binarywang.wx.miniapp.bean.shop;

import com.google.gson.annotations.SerializedName;
import java.io.Serializable;
import lombok.Data;

/**
 * @author leiin
 * created on  2021/3/23
 */
@Data
public class WxMaShopPayInfo implements Serializable {
  private static final long serialVersionUID = 687488209024968647L;

  /**
   * 支付方式（支付方式，0:微信支付，1: 货到付款，2：商家会员储蓄卡, 默认0)
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("pay_method_type")
  private Integer payMethodType;

  @SerializedName("pay_method")
  private String payMethod;

  /**
   * 预支付ID
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("prepay_id")
  private String prepayId;

  /**
   * 预付款时间（拿到prepay_id的时间）
   * <pre>
   * 是否必填：是
   * </pre>
   */
  @SerializedName("prepay_time")
  private String prepayTime;

  // 以下字段仅作为返回数据
  /**
   * 支付ID，调过同步订单支付结果且action_type=1时才存在
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("transaction_id")
  private String transactionId;

  /**
   * 付款时间（拿到transaction_id的时间）
   * <pre>
   * 是否必填：
   * </pre>
   */
  @SerializedName("pay_time")
  private String payTime;
}
