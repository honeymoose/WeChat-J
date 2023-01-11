package me.chanjar.weixin.mp.bean.result;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.mp.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * 短key解析结果
 *
 * @author zsa
 */
@Data
public class WxMpShortKeyResult implements Serializable {

  private static final long serialVersionUID = 5770641374997158852L;

  /**
   * 长信息
   */
  @SerializedName("long_data")
  protected String longData;
  /**
   * 创建的时间戳
   */
  @SerializedName("create_time")
  protected long createTime;
  /**
   * 剩余的过期秒数
   */
  @SerializedName("expire_seconds")
  protected long expireSeconds;

  public static WxMpShortKeyResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMpShortKeyResult.class);
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
