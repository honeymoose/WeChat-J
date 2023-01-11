package com.ossez.wechat.open.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 文档地址：https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/api/account/get.html
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxOpenGetResult extends WxOpenResult implements Serializable {

  private static final long serialVersionUID = -1196242565823312696L;

  @SerializedName("open_appid")
  private String openAppid;

  public static WxOpenGetResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenGetResult.class);
  }

}
