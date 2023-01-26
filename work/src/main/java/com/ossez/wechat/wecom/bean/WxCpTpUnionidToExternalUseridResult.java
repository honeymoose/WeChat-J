package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.*;


@Getter
@Setter
public class WxCpTpUnionidToExternalUseridResult extends WxCpBaseResp {


  private static final long serialVersionUID = -6153589164415497369L;

  @SerializedName("external_userid")
  private String externalUserid;

  @SerializedName("pending_id")
  private String pendingId;


  public static WxCpTpUnionidToExternalUseridResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpUnionidToExternalUseridResult.class);
  }


}
