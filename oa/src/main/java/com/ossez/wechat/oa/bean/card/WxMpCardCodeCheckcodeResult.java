package com.ossez.wechat.oa.bean.card;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.io.Serializable;
import java.util.List;


@Data
public class WxMpCardCodeCheckcodeResult implements Serializable {
  private static final long serialVersionUID = -5128692403997016750L;

  /**
   * 已经成功存入的code数目
   */
  @SerializedName("exist_code")
  private List<String> existCode;

  @SerializedName("not_exist_code")
  private List<String> notExistCode;


  public static WxMpCardCodeCheckcodeResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardCodeCheckcodeResult.class);
  }


  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

