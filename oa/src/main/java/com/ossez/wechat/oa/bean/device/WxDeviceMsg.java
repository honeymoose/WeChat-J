package com.ossez.wechat.oa.bean.device;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * @author keungtung.
 * created on  10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceMsg extends AbstractDeviceBean {
  private static final long serialVersionUID = -5567110858455277963L;

  @SerializedName("device_type")
  private String deviceType;
  @SerializedName("device_id")
  private String deviceId;
  @SerializedName("open_id")
  private String openId;
  private String content;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
