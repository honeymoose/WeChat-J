package com.ossez.wechat.wecom.bean.license.account;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseCorpAccount;

import java.util.List;

/**
 * 企业的帐号列表（已激活）
 * 文档地址：https://developer.work.weixin.qq.com/document/path/95544
 *
 * @author Totoro  created on  2022/6/27 15:15
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseCorpAccountListResp extends WxCpBaseResp {
  private static final long serialVersionUID = -7976008813041959375L;

  @SerializedName("next_cursor")
  private String nextCursor;

  @SerializedName("has_more")
  private Integer hasMore;

  @SerializedName("account_list")
  private List<WxCpTpLicenseCorpAccount> orderList;


  /**
   * From json wx cp tp license corp account list resp.
   *
   * @param json the json
   * @return the wx cp tp license corp account list resp
   */
  public static WxCpTpLicenseCorpAccountListResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseCorpAccountListResp.class);
  }

}
