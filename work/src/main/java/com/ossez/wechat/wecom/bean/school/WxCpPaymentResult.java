package com.ossez.wechat.wecom.bean.school;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;
import java.util.List;

/**
 * 获取学生付款结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpPaymentResult extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("project_name")
  private String projectName;

  @SerializedName("amount")
  private Integer amount;

  @SerializedName("payment_result")
  private List<PaymentResult> paymentResult;

  /**
   * The type Payment result.
   */
  @Setter
  @Getter
  public static class PaymentResult {

    @SerializedName("student_userid")
    private String studentUserId;

    @SerializedName("trade_no")
    private String tradeNo;

    @SerializedName("payer_parent_userid")
    private String payerParentUserId;

    @SerializedName("trade_state")
    private Integer tradeState;

    /**
     * From json payment result.
     *
     * @param json the json
     * @return the payment result
     */
    public static PaymentResult fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, PaymentResult.class);
    }

    /**
     * To json string.
     *
     * @return the string
     */
    public String toJson() {
      return WxCpGsonBuilder.create().toJson(this);
    }

  }

  /**
   * From json wx cp payment result.
   *
   * @param json the json
   * @return the wx cp payment result
   */
  public static WxCpPaymentResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpPaymentResult.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
