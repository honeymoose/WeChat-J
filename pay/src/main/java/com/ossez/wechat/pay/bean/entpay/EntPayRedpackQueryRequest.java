package com.ossez.wechat.pay.bean.entpay;

import com.ossez.wechat.pay.bean.request.BaseWxPayRequest;
import com.ossez.wechat.pay.exception.WxPayException;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import java.util.Map;

/**
 * 红包发送记录查询请求
 *
 * @author wuyong
 * created on  2019-12-01 17:19
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class EntPayRedpackQueryRequest extends BaseWxPayRequest {


  /**
   * 商户订单号
   */
  @XStreamAlias("mch_billno")
  private String mchBillNo;


  @Override
  protected void checkConstraints() throws WxPayException {

  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("mch_billno", mchBillNo);
  }
}
