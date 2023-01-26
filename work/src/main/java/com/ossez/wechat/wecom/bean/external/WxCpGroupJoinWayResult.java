package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

/**
 * 客户群「加入群聊」配置处理结果
 *
 * @author Jc
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxCpGroupJoinWayResult extends WxCpBaseResp {
  private static final long serialVersionUID = 5621905029624794129L;
  @SerializedName("config_id")
  private String configId;

  /**
   * From json wx cp group join way result.
   *
   * @param json the json
   * @return the wx cp group join way result
   */
  public static WxCpGroupJoinWayResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupJoinWayResult.class);
  }
}
