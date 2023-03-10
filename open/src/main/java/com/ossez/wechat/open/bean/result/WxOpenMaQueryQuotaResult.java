package com.ossez.wechat.open.bean.result;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 微信开放平台小程序当前分阶段发布详情
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaQueryQuotaResult extends WxOpenResult {

  private static final long serialVersionUID = 5915265985261653007L;

  @SerializedName("rest")
  private Integer rest;

  @SerializedName("limit")
  private Integer limit;

  @SerializedName("speedup_rest")
  private Integer speedupRest;

  @SerializedName("speedup_limit")
  private Integer speedupLimit;


  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }


}
