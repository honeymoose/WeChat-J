package com.ossez.wechat.open.bean.ma;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * 微信小程序分类目录.
 *
 * @author yqx
 * created on  2018/9/13
 */
@Data
public class WxOpenMaCategory implements Serializable {
  private static final long serialVersionUID = -700005096619889630L;

  @SerializedName("first_class")
  private String firstClass;

  @SerializedName("second_class")
  private String secondClass;

  @SerializedName("third_class")
  private String thirdClass;

  @SerializedName("first_id")
  private Integer firstId;

  @SerializedName("second_id")
  private Integer secondId;

  @SerializedName("third_id")
  private Integer thirdId;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }
}
