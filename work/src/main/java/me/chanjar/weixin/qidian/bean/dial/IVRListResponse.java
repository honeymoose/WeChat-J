package me.chanjar.weixin.qidian.bean.dial;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;
import me.chanjar.weixin.qidian.bean.common.QidianResponse;

import java.util.List;

@Data
public class IVRListResponse extends QidianResponse {
  private List<Ivr> node;

  public static IVRListResponse fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, IVRListResponse.class);
  }
}
