package com.ossez.wechat.oa.bean.card;

import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * 删除卡券结果.
 *
 * @author fanxl
 * created on  2019/1/22 0022 10:24
 */
public class WxMpCardDeleteResult extends BaseWxMpCardResult {
  private static final long serialVersionUID = -4367717540650523290L;

  public static WxMpCardDeleteResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpCardDeleteResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
