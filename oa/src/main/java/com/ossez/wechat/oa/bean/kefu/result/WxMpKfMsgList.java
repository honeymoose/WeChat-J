package com.ossez.wechat.oa.bean.kefu.result;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 *
 * @author Binary Wang
 * created on  2016/7/15
 */
@Data
public class WxMpKfMsgList implements Serializable {
  private static final long serialVersionUID = 4524296707435188202L;

  @SerializedName("recordlist")
  private List<WxMpKfMsgRecord> records;

  @SerializedName("number")
  private Integer number;

  @SerializedName("msgid")
  private Long msgId;

  public static WxMpKfMsgList fromJson(String responseContent) {
    return WxMpGsonBuilder.create().fromJson(responseContent, WxMpKfMsgList.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
