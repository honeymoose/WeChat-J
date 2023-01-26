package com.ossez.wechat.wecom.bean.oa;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;
import java.util.List;

/**
 * 成员假期余额信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpUserVacationQuota extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = 7387181805254287157L;

  @SerializedName("lists")
  private List<VacationQuota> lists;

  /**
   * The type Vacation quota.
   */
  @Getter
  @Setter
  public static class VacationQuota implements Serializable {
    private static final long serialVersionUID = -5696099236344075582L;

    @SerializedName("id")
    private Integer id;

    @SerializedName("assignduration")
    private Integer assignDuration;

    @SerializedName("usedduration")
    private Integer usedDuration;

    @SerializedName("leftduration")
    private Integer leftDuration;

    @SerializedName("vacationname")
    private String vacationName;

  }

  /**
   * From json wx cp user vacation quota.
   *
   * @param json the json
   * @return the wx cp user vacation quota
   */
  public static WxCpUserVacationQuota fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpUserVacationQuota.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
