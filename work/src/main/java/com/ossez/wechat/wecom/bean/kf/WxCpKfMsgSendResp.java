package com.ossez.wechat.wecom.bean.kf;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * The type Wx cp kf msg send resp.
 *
 * @author leiin  created on  2022/1/26 7:41 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfMsgSendResp extends WxCpBaseResp {
  @SerializedName("msgid")
  private String msgId;

  /**
   * From json wx cp kf msg send resp.
   *
   * @param json the json
   * @return the wx cp kf msg send resp
   */
  public static WxCpKfMsgSendResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfMsgSendResp.class);
  }
}
