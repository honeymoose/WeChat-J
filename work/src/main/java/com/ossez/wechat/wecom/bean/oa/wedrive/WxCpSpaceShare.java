package com.ossez.wechat.wecom.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 获取邀请链接.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSpaceShare extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("space_share_url")
  private String spaceShareUrl;

  /**
   * From json wx cp space share.
   *
   * @param json the json
   * @return the wx cp space share
   */
  public static WxCpSpaceShare fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceShare.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
