package com.ossez.wechat.wecom.bean;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 *  更新任务卡片消息状态的返回类
 *  参考文档：https://work.weixin.qq.com/api/doc#90000/90135/91579
 *  Created by Jeff on 2019-05-16.
 * </pre>
 *
 * @author <a href="https://github.com/domainname">Jeff</a> created on  2019-05-16
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class WxCpTaskCardUpdateResult implements Serializable {

  @SerializedName("errcode")
  private Integer errcode;

  @SerializedName("errmsg")
  private String errmsg;

  /**
   * 用户列表
   */
  @SerializedName("invaliduser")
  private List<String> invalidUsers;

  /**
   * From json wx cp task card update result.
   *
   * @param json the json
   * @return the wx cp task card update result
   */
  public static WxCpTaskCardUpdateResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTaskCardUpdateResult.class);
  }
}
