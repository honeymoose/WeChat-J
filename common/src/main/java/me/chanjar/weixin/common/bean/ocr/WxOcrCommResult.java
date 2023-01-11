package me.chanjar.weixin.common.bean.ocr;

import com.google.gson.annotations.SerializedName;
import lombok.Data;
import me.chanjar.weixin.common.util.json.WxGsonBuilder;

import java.io.Serializable;
import java.util.List;

/**
 * @author Theo Nie
 */
@Data
public class WxOcrCommResult implements Serializable {
  private static final long serialVersionUID = 455833771627756440L;

  @SerializedName("img_size")
  private WxOcrImgSize imgSize;
  @SerializedName("items")
  private List<Items> items;

  public static WxOcrCommResult fromJson(String json) {
    return WxGsonBuilder.create().fromJson(json, WxOcrCommResult.class);
  }

  @Override
  public String toString() {
    return WxGsonBuilder.create().toJson(this);
  }

  @Data
  public static class Items implements Serializable {
    private static final long serialVersionUID = 3066181677009102791L;

    @SerializedName("text")
    private String text;
    @SerializedName("pos")
    private WxOcrPos pos;

    @Override
    public String toString() {
      return WxGsonBuilder.create().toJson(this);
    }
  }
}
