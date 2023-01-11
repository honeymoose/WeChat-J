package com.ossez.wechat.oa.bean.card;

import java.io.Serializable;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

@Data
public class WxMpCardQrcodeCreateResult implements Serializable {
  private static final long serialVersionUID = -128818731449449537L;
  private Integer errcode;
  private String errmsg;
  private String ticket;

  @SerializedName("expire_seconds")
  private Integer expireSeconds;

  private String url;

  @SerializedName("show_qrcode_url")
  private String showQrcodeUrl;

  public boolean isSuccess() {
    return 0 == errcode;
  }

  public static WxMpCardQrcodeCreateResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardQrcodeCreateResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

