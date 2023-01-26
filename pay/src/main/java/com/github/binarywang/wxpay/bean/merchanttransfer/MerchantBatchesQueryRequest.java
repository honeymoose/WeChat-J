package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Merchant batches query request.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class MerchantBatchesQueryRequest implements Serializable {
  private static final long serialVersionUID = 7074459219428697275L;
  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[1,32]
   * 描述：
   *  path商户系统内部的商家批次单号，要求此参数只能由数字、大小写字母组成，在商户系统内部唯一
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：是否查询转账明细单
   * 变量名：need_query_detail
   * 是否必填：是
   * 类型：boolean
   * 描述：
   *  query枚举值：
   * true：是；
   * false：否，默认否。
   * 商户可选择是否查询指定状态的转账明细单，当转账批次单状态为“FINISHED”（已完成）时，才会返回满足条件的转账明细单
   * 示例值：true
   * </pre>
   */
  @SerializedName("need_query_detail")
  private Boolean needQueryDetail;

  /**
   * <pre>
   * 字段名：请求资源起始位置
   * 变量名：offset
   * 是否必填：否
   * 类型：int
   * 描述：
   *  query该次请求资源的起始位置，从0开始，默认值为0
   * </pre>
   */
  @SerializedName("offset")
  private Integer offset;

  /**
   * <pre>
   * 字段名：最大资源条数
   * 变量名：limit
   * 是否必填：否
   * 类型：int
   * 描述：
   *  query该次请求可返回的最大明细条数，最小20条，最大100条，不传则默认20条。不足20条按实际条数返回
   * 示例值：20
   * </pre>
   */
  @SerializedName("limit")
  private Integer limit;

  /**
   * <pre>
   * 字段名：明细状态
   * 变量名：detail_status
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  query查询指定状态的转账明细单，当need_query_detail为true时，该字段必填
   * ALL:全部。需要同时查询转账成功和转账失败的明细单
   * SUCCESS:转账成功。只查询转账成功的明细单
   * FAIL:转账失败。只查询转账失败的明细单
   * 示例值：FAIL
   * </pre>
   */
  @SerializedName("detail_status")
  private String detailStatus;
}
