package com.ossez.wechat.pay.bean.profitsharing;

import com.ossez.wechat.pay.bean.request.BaseWxPayRequest;
import com.ossez.wechat.pay.constant.WxPayConstants;
import com.ossez.wechat.pay.exception.WxPayException;
import com.ossez.wechat.common.annotation.Required;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.*;

import java.util.Map;

/**
 * @author : cofedream
 * created on  : 2020-12-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
@Builder(builderMethodName = "newBuilder")
@NoArgsConstructor
@AllArgsConstructor
@XStreamAlias("xml")
public class ProfitSharingOrderAmountQueryRequest extends BaseWxPayRequest {

  private static final long serialVersionUID = 6009448187615691627L;
  /**
   * <pre>
   * 字段名：微信订单号.
   * 变量名：transaction_id
   * 是否必填：是
   * String(32)
   * 示例值：4208450740201411110007820472
   * 描述：微信支付订单号
   * </pre>
   */
  @XStreamAlias("transaction_id")
  @Required
  private String transactionId;

  @Override
  protected void checkConstraints() throws WxPayException {
    // 目前仅支持HMAC-SHA256.
    this.setSignType(WxPayConstants.SignType.HMAC_SHA256);
  }

  @Override
  public boolean ignoreAppid() {
    return true;
  }

  @Override
  protected boolean ignoreSubAppId() {
    return true;
  }

  @Override
  protected void storeMap(Map<String, String> map) {
    map.put("transaction_id", transactionId);
  }
}
