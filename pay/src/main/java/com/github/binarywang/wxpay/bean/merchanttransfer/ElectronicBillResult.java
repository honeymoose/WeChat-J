package com.github.binarywang.wxpay.bean.merchanttransfer;

import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * The type Electronic bill result.
 *
 * @author glz
 * created on  2022-6-11
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class ElectronicBillResult implements Serializable {
  private static final long serialVersionUID = 7528245102572829190L;
  /**
   * <pre>
   * 字段名：商家批次单号
   * 变量名：out_batch_no
   * 是否必填：是
   * 类型：string[5,32]
   * 描述：
   *  body商户系统内部的商家批次单号，在商户系统内部唯一。需要电子回单的批次单号
   * 示例值：plfk2020042013
   * </pre>
   */
  @SerializedName("out_batch_no")
  private String outBatchNo;

  /**
   * <pre>
   * 字段名：电子回单申请单号
   * 变量名：signature_no
   * 是否必填：是
   * 类型：string[3,45]
   * 描述：
   *  电子回单申请单号，申请单据的唯一标识
   * 示例值：1050000010509999485212020110200058820001
   * </pre>
   */
  @SerializedName("signature_no")
  private String signatureNo;

  /**
   * <pre>
   * 字段名：电子回单状态
   * 变量名：signature_status
   * 是否必填：否
   * 类型：string[1,10]
   * 描述：
   *  枚举值：
   * ACCEPTED:已受理，电子签章已受理成功
   * FINISHED:已完成。电子签章已处理完成
   * 示例值：ACCEPTED
   * </pre>
   */
  @SerializedName("signature_status")
  private String signatureStatus;

  /**
   * <pre>
   * 字段名：电子回单文件的hash方法
   * 变量名：hash_type
   * 是否必填：否
   * 类型：string[1,20]
   * 描述：
   *  电子回单文件的hash方法，回单状态为：FINISHED时返回。
   * 示例值：SHA256
   * </pre>
   */
  @SerializedName("hash_type")
  private String hashType;

  /**
   * <pre>
   * 字段名：电子回单文件的hash值
   * 变量名：hash_value
   * 是否必填：否
   * 类型：string[3,1000]
   * 描述：
   *  电子回单文件的hash值，用于下载之后验证文件的完整、正确性，回单状态为：FINISHED时返回。
   * 示例值：DE731F35146A0BEFADE5DB9D1E468D96C01CA8898119C674FEE9F11F4DBE5529
   * </pre>
   */
  @SerializedName("hash_value")
  private String hashValue;

  /**
   * <pre>
   * 字段名：电子回单文件的下载地址
   * 变量名：download_url
   * 是否必填：否
   * 类型：string[10,3000]
   * 描述：
   *  电子回单文件的下载地址，回单状态为：FINISHED时返回。URL有效时长为10分钟，10分钟后需要重新去获取下载地址（但不需要走受理）
   * 示例值：https://api.mch.weixin.qq.com/v3/billdownload/file?token=xxx
   * </pre>
   */
  @SerializedName("download_url")
  private String downloadUrl;

  /**
   * <pre>
   * 字段名：创建时间
   * 变量名：create_time
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  电子签章单创建时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2020-05-20T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName("create_time")
  private String createTime;

  /**
   * <pre>
   * 字段名：更新时间
   * 变量名：update_time
   * 是否必填：否
   * 类型：string[1,32]
   * 描述：
   *  电子签章单最近一次状态变更的时间，遵循rfc3339标准格式，格式为yyyy-MM-DDTHH:mm:ss.sss+TIMEZONE，yyyy-MM-DD表示年月日，T出现在字符串中，表示time元素的开头，HH:mm:ss.sss表示时分秒毫秒，TIMEZONE表示时区（+08:00表示东八区时间，领先UTC 8小时，即北京时间）。例如：2015-05-20T13:29:35.120+08:00表示北京时间2015年05月20日13点29分35秒
   * 示例值：2020-05-21T13:29:35.120+08:00
   * </pre>
   */
  @SerializedName("update_time")
  private String updateTime;
}
