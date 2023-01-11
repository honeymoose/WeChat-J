package com.ossez.wechat.oa.bean.card.membercard;

import java.io.Serializable;

import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

@Data
public class MemberCardActivateUserFormResult implements Serializable {
  private Integer errcode;
  private String errmsg;

  public boolean isSuccess() {
    return 0 == errcode;
  }

  public static MemberCardActivateUserFormResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, MemberCardActivateUserFormResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}

