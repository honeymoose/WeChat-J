package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Getter;
import lombok.Setter;

/**
 * 预授权码返回
 *
 * @author yqx  created on  2020/3/19
 */
@Getter
@Setter
public class WxCpTpPreauthCode extends WxCpBaseResp {

  /**
   * The Pre auth code.
   */
  @SerializedName("pre_auth_code")
  String preAuthCode;

  /**
   * The Expires in.
   */
  @SerializedName("expires_in")
  Long expiresIn;

  /**
   * From json wx cp tp preauth code.
   *
   * @param json the json
   * @return the wx cp tp preauth code
   */
  public static WxCpTpPreauthCode fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpPreauthCode.class);
  }
}
