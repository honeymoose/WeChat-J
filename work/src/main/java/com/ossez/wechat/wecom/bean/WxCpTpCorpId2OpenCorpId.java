package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 应用的管理员
 *
 * @author huangxiaoming
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpTpCorpId2OpenCorpId extends WxCpBaseResp {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("open_corpid")
  private String openCorpId;

  /**
   * From json wx cp tp admin.
   *
   * @param json the json
   * @return the wx cp tp admin
   */
  public static WxCpTpCorpId2OpenCorpId fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpCorpId2OpenCorpId.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
