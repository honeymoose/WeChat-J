package com.ossez.wechat.wecom.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 新建空间信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpSpaceCreateData extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("spaceid")
  private String spaceId;

  /**
   * From json wx cp space create data.
   *
   * @param json the json
   * @return the wx cp space create data
   */
  public static WxCpSpaceCreateData fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpSpaceCreateData.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
