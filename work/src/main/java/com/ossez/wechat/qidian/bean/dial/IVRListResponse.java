package com.ossez.wechat.qidian.bean.dial;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.qidian.bean.common.QidianResponse;
import lombok.Data;

import java.util.List;

@Data
public class IVRListResponse extends QidianResponse {
  private List<Ivr> node;

  public static IVRListResponse fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, IVRListResponse.class);
  }
}
