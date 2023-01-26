package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.moment.MomentInfo;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.util.List;

/**
 * 企业发表内容到客户的朋友圈 获取企业全部的发表列表
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGetMomentList extends WxCpBaseResp {
  private static final long serialVersionUID = 106159971765109008L;

  @SerializedName("next_cursor")
  private String nextCursor;
  @SerializedName("moment_list")
  private List<MomentInfo> momentList;

  /**
   * From json wx cp get moment list.
   *
   * @param json the json
   * @return the wx cp get moment list
   */
  public static WxCpGetMomentList fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGetMomentList.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
