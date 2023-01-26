package com.ossez.wechat.wecom.bean.school.user;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 获取「学校通知」二维码 返回结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSubscribeQrCode extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("qrcode_big")
  private String qrCodeBig;

  @SerializedName("qrcode_middle")
  private String qrCodeMiddle;

  @SerializedName("qrcode_thumb")
  private String qrCodeThumb;

  /**
   * From json wx cp subscribe qr code.
   *
   * @param json the json
   * @return the wx cp subscribe qr code
   */
  public static WxCpSubscribeQrCode fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSubscribeQrCode.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
