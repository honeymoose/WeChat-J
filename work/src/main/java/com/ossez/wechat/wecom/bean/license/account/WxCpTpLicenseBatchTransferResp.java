package com.ossez.wechat.wecom.bean.license.account;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseTransfer;

import java.util.List;

/**
 * 基础结果返回信息
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95673
 *
 * @author Totoro  created on  2022/6/27 15:49
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseBatchTransferResp extends WxCpBaseResp {
  private static final long serialVersionUID = 5443977430756597486L;

  @SerializedName("transfer_result")
  private List<WxCpTpLicenseTransfer> transferResult;

  /**
   * From json wx cp tp license batch transfer resp.
   *
   * @param json the json
   * @return the wx cp tp license batch transfer resp
   */
  public static WxCpTpLicenseBatchTransferResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseBatchTransferResp.class);
  }


}
