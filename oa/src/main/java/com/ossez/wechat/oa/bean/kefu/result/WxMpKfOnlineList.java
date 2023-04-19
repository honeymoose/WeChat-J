package com.ossez.wechat.oa.bean.kefu.result;

import java.io.Serializable;
import java.util.List;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * @author YuCheng
 */
public class WxMpKfOnlineList implements Serializable {
  private static final long serialVersionUID = -6154705288500854617L;

  @SerializedName("kf_online_list")
  private List<WxMpKfInfo> kfOnlineList;

  public static WxMpKfOnlineList fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpKfOnlineList.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public List<WxMpKfInfo> getKfOnlineList() {
    return kfOnlineList;
  }

  public void setKfOnlineList(List<WxMpKfInfo> kfOnlineList) {
    this.kfOnlineList = kfOnlineList;
  }
}
