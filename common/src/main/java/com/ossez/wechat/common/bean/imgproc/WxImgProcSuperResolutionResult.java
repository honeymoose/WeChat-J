package com.ossez.wechat.common.bean.imgproc;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * 图片高清化返回结果
 * @author Theo Nie
 */
@Data
public class WxImgProcSuperResolutionResult implements Serializable {
  private static final long serialVersionUID = 8007440280170407021L;

  @SerializedName("media_id")
  private String mediaId;

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxImgProcSuperResolutionResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxImgProcSuperResolutionResult.class);
  }
}
