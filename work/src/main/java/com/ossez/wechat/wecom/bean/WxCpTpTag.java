package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * The type Wx cp tp tag.
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021 -02-14 16:15 16:15
 */
@Data
public class WxCpTpTag implements Serializable {
  private static final long serialVersionUID = 581740383760234134L;

  @SerializedName("tagid")
  private String tagId;

  @SerializedName("tagname")
  private String tagName;

  /**
   * Deserialize wx cp tp tag.
   *
   * @param json the json
   * @return the wx cp tp tag
   */
  public static WxCpTpTag deserialize(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpTag.class);
  }
}
