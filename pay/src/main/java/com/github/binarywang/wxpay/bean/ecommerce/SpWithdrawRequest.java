package com.github.binarywang.wxpay.bean.ecommerce;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;

/**
 * 电商平台提现
 * <pre>
 *   文档地址：https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/ecommerce/fund/chapter3_5.shtml
 * </pre>
 */
@Data
@NoArgsConstructor
public class SpWithdrawRequest implements Serializable {
  /**
   * <pre>
   * 字段名：商户提现单号
   * 变量名：out_request_no
   * 是否必填：是
   * 类型：string（32）
   * 描述：
   *  商户提现单号，由商户自定义生成。
   * 示例值：20190611222222222200000000012122
   * </pre>
   */
  @SerializedName(value = "out_request_no")
  private String outRequestNo;

  /**
   * <pre>
   * 字段名：提现金额
   * 变量名：amount
   * 是否必填：是
   * 类型：int64
   * 描述：
   *  提现金额，单位：分（RMB）
   * 示例值：1
   * </pre>
   */
  @SerializedName(value = "amount")
  private Integer amount;

  /**
   * <pre>
   * 字段名：备注
   * 变量名：remark
   * 是否必填：否
   * 类型：string（56）
   * 描述：
   *  商户对提现单的备注
   * 示例值：交易提现
   * </pre>
   */
  @SerializedName(value = "remark")
  private String remark;

  /**
   * <pre>
   * 字段名：银行附言
   * 变量名：bank_memo
   * 是否必填：否
   * 类型：string（32）
   * 描述：
   *  展示在收款银行系统中的附言，数字、字母最长32个汉字（能否成功展示依赖银行系统支持）。
   * 示例值：xx平台提现
   * </pre>
   */
  @SerializedName(value = "bank_memo")
  private String bankMemo;

  /**
   * <pre>
   * 字段名：账户类型
   * 变量名：account_type
   * 是否必填：是
   * 类型：string（16）
   * 描述：
   *  枚举值：
   *    BASIC：基本账户
   *    OPERATION：运营账户
   *    FEES：手续费账户
   * 示例值：BASIC
   * </pre>
   */
  @SerializedName(value = "account_type")
  private String accountType;

}
