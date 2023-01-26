package com.ossez.wechat.wecom.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 获取分享链接返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileShare extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("share_url")
  private String shareUrl;

  /**
   * From json wx cp file share.
   *
   * @param json the json
   * @return the wx cp file share
   */
  public static WxCpFileShare fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileShare.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
