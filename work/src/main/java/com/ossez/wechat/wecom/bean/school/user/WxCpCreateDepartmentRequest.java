package com.ossez.wechat.wecom.bean.school.user;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.*;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

/**
 * 创建部门请求.
 *
 * @author Wang_Wong  created on  2022-06-22
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain = true)
public class WxCpCreateDepartmentRequest implements Serializable {
  private static final long serialVersionUID = -4960239394895754138L;

  @SerializedName("parentid")
  private Integer parentId;

  @SerializedName("name")
  private String name;

  @SerializedName("id")
  private Integer id;

  @SerializedName("type")
  private Integer type;

  @SerializedName("register_year")
  private Integer registerYear;

  @SerializedName("standard_grade")
  private Integer standardGrade;

  @SerializedName("order")
  private Integer order;

  @SerializedName("department_admins")
  private List<DepartmentAdmin> departmentAdmins;

  /**
   * The type Department admin.
   */
  @Setter
  @Getter
  public static class DepartmentAdmin implements Serializable {

    @SerializedName("userid")
    private String userId;

    @SerializedName("type")
    private Integer type;

    @SerializedName("subject")
    private String subject;

    /**
     * From json department admin.
     *
     * @param json the json
     * @return the department admin
     */
    public static DepartmentAdmin fromJson(String json) {
      return WxCpGsonBuilder.create().fromJson(json, DepartmentAdmin.class);
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

  /**
   * From json wx cp create department request.
   *
   * @param json the json
   * @return the wx cp create department request
   */
  public static WxCpCreateDepartmentRequest fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpCreateDepartmentRequest.class);
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
