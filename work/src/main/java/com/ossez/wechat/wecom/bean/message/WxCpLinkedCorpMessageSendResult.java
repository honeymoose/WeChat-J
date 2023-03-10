package com.ossez.wechat.wecom.bean.message;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Getter;
import lombok.Setter;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 互联企业的消息推送接口返回实体
 *
 * @author pg  created on  2021年6月22日
 */
@Setter
@Getter
public class WxCpLinkedCorpMessageSendResult extends WxCpBaseResp {
  private static final long serialVersionUID = 3990693822996824333L;

  @SerializedName("invaliduser")
  private String[] invalidUser;

  @SerializedName("invalidparty")
  private String[] invalidParty;

  @SerializedName("invalidtag")
  private String[] invalidTag;

  @Override
  public String toString() {
    return WxCpGsonBuilder.create().toJson(this);
  }

  /**
   * From json wx cp linked corp message send result.
   *
   * @param json the json
   * @return the wx cp linked corp message send result
   */
  public static WxCpLinkedCorpMessageSendResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpLinkedCorpMessageSendResult.class);
  }

}
