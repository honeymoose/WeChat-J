package com.ossez.wechat.oa.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * 商品信息.
 * @author yuanqixun
 * date:2018-08-25 00:32
 */
@Data
public class Sku implements Serializable {

  /**
   * 卡券库存的数量，不支持填写0，上限为100000000
   */
  @SerializedName("quantity")
  private Integer quantity = 100000000;

  /**
   * 卡券全部库存的数量，上限为100000000。
   * https://developers.weixin.qq.com/doc/offiaccount/Cards_and_Offer/Managing_Coupons_Vouchers_and_Cards.html#4
   */
  @SerializedName("total_quantity")
  private Integer totalQuantity = 100000000;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
