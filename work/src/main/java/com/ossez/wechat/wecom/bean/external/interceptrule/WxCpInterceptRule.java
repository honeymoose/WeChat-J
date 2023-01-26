package com.ossez.wechat.wecom.bean.external.interceptrule;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.common.bean.ToJson;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 敏感词规则修改接口请求和详情接口响应共用的实体类
 *
 * @author didi
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpInterceptRule implements Serializable, ToJson {
  private static final long serialVersionUID = 7161086545769110431L;

  @SerializedName("rule_name")
  private String ruleName;
  @SerializedName("rule_id")
  private String ruleId;
  @SerializedName("word_list")
  private List<String> wordList;
  @SerializedName("extra_rule")
  private ExtraRule extraRule;
  @SerializedName("intercept_type")
  private int interceptType;
  @SerializedName("applicable_range")
  private ApplicableRange applicableRange;

  @Data
  public static class ExtraRule implements Serializable {
    private static final long serialVersionUID = -6377386837586111671L;

    @SerializedName("semantics_list")
    private List<Integer> semanticsList;
  }

  /**
   * From json wx cp intercept rule resp.
   *
   * @param json the json
   * @return the wx cp intercept rule resp
   */
  public static WxCpInterceptRule fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpInterceptRule.class);
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
