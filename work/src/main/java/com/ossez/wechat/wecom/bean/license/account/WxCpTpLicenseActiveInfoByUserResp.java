package com.ossez.wechat.wecom.bean.license.account;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseActiveCodeInfo;

import java.util.List;

/**
 * 某个企业成员的激活情况
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95555
 *
 * @author Totoro  created on  2022-6-27 14:51:19
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseActiveInfoByUserResp extends WxCpBaseResp {
  private static final long serialVersionUID = -5172901191911873330L;


  @SerializedName("active_status")
  private Integer activeStatus;

  @SerializedName("active_info_list")
  private List<WxCpTpLicenseActiveCodeInfo> activeInfoList;


  /**
   * From json wx cp tp license active info by user resp.
   *
   * @param json the json
   * @return the wx cp tp license active info by user resp
   */
  public static WxCpTpLicenseActiveInfoByUserResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseActiveInfoByUserResp.class);
  }

}
