package com.ossez.wechat.oa.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.common.bean.ToJson;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;

/**
 * 顾问快捷回复
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/8/008
 */
@Data
public class WxMpGuideFastReply implements ToJson, Serializable {
  private static final long serialVersionUID = -3316181204068248972L;

  /**
   * 快捷回复内容
   */
  @SerializedName("content")
  private String content;

  /**
   * 修改时间
   */
  @SerializedName("updatetime")
  private Long updateTime;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideFastReply fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideFastReply.class);
  }
}
