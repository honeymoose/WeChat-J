package com.ossez.wechat.oa.bean.kefu.result;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * @author Binary Wang
 */
@Data
public class WxMpKfSessionWaitCaseList implements Serializable {
  private static final long serialVersionUID = 2432132626631361922L;

  /**
   * count 未接入会话数量
   */
  @SerializedName("count")
  private Long count;

  /**
   * waitcaselist 未接入会话列表，最多返回100条数据
   */
  @SerializedName("waitcaselist")
  private List<WxMpKfSession> kfSessionWaitCaseList;

  public static WxMpKfSessionWaitCaseList fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json,
      WxMpKfSessionWaitCaseList.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
