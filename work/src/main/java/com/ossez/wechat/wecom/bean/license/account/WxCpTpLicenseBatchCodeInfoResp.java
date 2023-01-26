package com.ossez.wechat.wecom.bean.license.account;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseActiveCodeInfo;

import java.util.List;

/**
 * 批量查询的激活码详情
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
 *
 * @author Totoro  created on  2022-6-27 14:51:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseBatchCodeInfoResp extends WxCpBaseResp {
  private static final long serialVersionUID = 1327038464020790843L;

  @SerializedName("active_info_list")
  private List<WxCpTpLicenseActiveCodeInfo> activeCodeInfoList;

  @SerializedName("invalid_active_code_list")
  private List<String> invalidActiveCodeList;


  /**
   * From json wx cp tp license batch code info resp.
   *
   * @param json the json
   * @return the wx cp tp license batch code info resp
   */
  public static WxCpTpLicenseBatchCodeInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseBatchCodeInfoResp.class);
  }

}
