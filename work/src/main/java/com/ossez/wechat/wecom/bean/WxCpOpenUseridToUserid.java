package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * userid转换
 * 将代开发应用或第三方应用获取的密文open_userid转换为明文userid
 * 中间对象
 * @author yiyingcanfeng
 */
@Data
public class WxCpOpenUseridToUserid implements Serializable {
  private static final long serialVersionUID = 1714909184316350423L;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp open userid to userid result.
   *
   * @param json the json
   * @return the wx cp open userid to userid result.
   */
  public static WxCpOpenUseridToUserid fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpOpenUseridToUserid.class);
  }

  @SerializedName("userid")
  private String userid;

  @SerializedName("open_userid")
  private String openUserid;

}
