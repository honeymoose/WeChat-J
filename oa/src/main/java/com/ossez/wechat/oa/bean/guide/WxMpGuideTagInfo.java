package com.ossez.wechat.oa.bean.guide;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.common.bean.ToJson;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * 标签信息
 *
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/11/011
 */

@Data
public class WxMpGuideTagInfo implements ToJson, Serializable {
  private static final long serialVersionUID = 2086445319422158695L;

  /**
   * 标签类型名称
   */
  @SerializedName("tag_name")
  private String tagName;

  /**
   * 标签值
   */
  @SerializedName("tag_values")
  private List<String> values;

  @Override
  public String toJson() {
    return WxGsonBuilder.create().toJson(this);
  }

  public static WxMpGuideTagInfo fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxMpGuideTagInfo.class);
  }
}
