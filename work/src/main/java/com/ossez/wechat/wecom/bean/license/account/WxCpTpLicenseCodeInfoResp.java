package com.ossez.wechat.wecom.bean.license.account;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseActiveCodeInfo;

/**
 * 查询的激活码详情
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95553
 *
 * @author Totoro  created on  2022/6/27 14:28
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseCodeInfoResp extends WxCpBaseResp {
  private static final long serialVersionUID = 8058798194938243361L;

  @SerializedName("active_info")
  private WxCpTpLicenseActiveCodeInfo activeCodeInfo;


  /**
   * From json wx cp tp license code info resp.
   *
   * @param json the json
   * @return the wx cp tp license code info resp
   */
  public static WxCpTpLicenseCodeInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseCodeInfoResp.class);
  }

}
