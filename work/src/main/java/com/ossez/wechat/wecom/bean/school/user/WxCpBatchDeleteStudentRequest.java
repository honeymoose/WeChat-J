package com.ossez.wechat.wecom.bean.school.user;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 批量删除学生请求.
 *
 * @author Wang_Wong  created on  2022-07-01
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpBatchDeleteStudentRequest implements Serializable {
  private static final long serialVersionUID = -4960239393895754138L;

  @SerializedName("useridlist")
  private List<String> userIdList;

  /**
   * From json wx cp batch delete student request.
   *
   * @param json the json
   * @return the wx cp batch delete student request
   */
  public static WxCpBatchDeleteStudentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpBatchDeleteStudentRequest.class);
  }

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
