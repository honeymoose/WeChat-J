package com.ossez.wechat.open.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 该 API 用于查询公众号或小程序是否绑定的开放平台帐号。
 * 文档地址：https://developers.weixin.qq.com/doc/oplatform/Third-party_Platforms/2.0/api/Mini_Program_Basic_Info/getbindopeninfo.html
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxOpenHaveResult extends WxOpenResult implements Serializable {

  @SerializedName("have_open")
  private Boolean haveOpen;

  public static WxOpenHaveResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOpenHaveResult.class);
  }

}
