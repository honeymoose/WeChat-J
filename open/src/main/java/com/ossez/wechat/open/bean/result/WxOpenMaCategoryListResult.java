package com.ossez.wechat.open.bean.result;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.open.bean.ma.WxOpenMaCategory;

import java.util.List;

/**
 * 微信开放平台小程序分类目录列表返回
 *
 * @author yqx
 * created on  2018/9/12
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class WxOpenMaCategoryListResult extends WxOpenResult {
  private static final long serialVersionUID = 4549360618179745721L;

  @SerializedName("category_list")
  List<WxOpenMaCategory> categoryList;

  @Override
  public String toString() {
    return WxOpenGsonBuilder.create().toJson(this);
  }

}
