package com.ossez.wechat.oa.bean.card.membercard;

import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;


/**
 * @author thomas
 * created on  2019/4/26
 */
@Data
public class WxMpMemberCardActivateTempInfoResult {

  private String errorCode;

  private String errorMsg;

  private MemberCardUserInfo userInfo;

  public static WxMpMemberCardActivateTempInfoResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpMemberCardActivateTempInfoResult.class);
  }

}
