package com.ossez.wechat.wecom.bean.school.user;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 创建部门返回结果.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpCreateDepartment extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625140879571L;

  @SerializedName("id")
  private Integer id;

  /**
   * From json wx cp create department.
   *
   * @param json the json
   * @return the wx cp create department
   */
  public static WxCpCreateDepartment fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCreateDepartment.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
