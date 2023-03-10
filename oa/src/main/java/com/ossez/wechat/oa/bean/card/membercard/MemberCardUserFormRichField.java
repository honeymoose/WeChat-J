package com.ossez.wechat.oa.bean.card.membercard;

import java.util.ArrayList;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.bean.card.enums.CardRichFieldType;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * 富文本字段.
 *
 * @author yuanqixun
 * created on  2018-08-30
 */
@Data
public class MemberCardUserFormRichField {

  /**
   * 富文本类型
   */
  @SerializedName("type")
  private CardRichFieldType type;

  @SerializedName("name")
  private String name;

  @SerializedName("values")
  private List<String> valueList;

  public void add(String value) {
    if (valueList == null) {
      valueList = new ArrayList<>();
    }
    valueList.add(value);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
