package com.ossez.wechat.open.bean.result;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.open.bean.ma.WxOpenMaHistoryVersion;

import java.util.List;

/**
 * 微信开放平台小程序 可回退的小程序版本 返回
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaHistoryVersionResult extends WxOpenResult {
  private static final long serialVersionUID = 4102311851687901079L;

  @SerializedName("version_list")
  List<WxOpenMaHistoryVersion> versions;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

}
