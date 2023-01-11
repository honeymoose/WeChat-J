package com.ossez.wechat.open.bean.result;

import java.util.List;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.open.bean.ma.WxOpenMaMember;

/**
 * 微信开放平台小程序体验者列表返回.
 *
 * @author yqx
 * created on  2018/9/12
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxOpenMaTesterListResult extends WxOpenResult {
  private static final long serialVersionUID = -613936397557067111L;

  @SerializedName("members")
  List<WxOpenMaMember> membersList;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

}
