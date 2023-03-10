package com.ossez.wechat.wecom.bean.kf;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 添加客服帐号-返回结果
 *
 * @author Fu  created on  2022/1/19 19:04
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfAccountAddResp extends WxCpBaseResp {

  private static final long serialVersionUID = -6649323005421772827L;

  /**
   * 新创建的客服帐号ID
   */
  @SerializedName("open_kfid")
  private String openKfid;

  /**
   * From json wx cp kf account add resp.
   *
   * @param json the json
   * @return the wx cp kf account add resp
   */
  public static WxCpKfAccountAddResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfAccountAddResp.class);
  }
}
