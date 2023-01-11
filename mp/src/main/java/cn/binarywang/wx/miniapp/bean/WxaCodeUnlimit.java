package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import com.google.gson.annotations.SerializedName;
import lombok.Data;
import lombok.EqualsAndHashCode;

import java.io.Serializable;

import static cn.binarywang.wx.miniapp.constant.WxMaConstants.DEFAULT_ENV_VERSION;

/**
 * 小程序码接口B.
 *
 * @author Element
 * created on  2017/7/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxaCodeUnlimit extends AbstractWxMaQrcodeWrapper implements Serializable {
  private static final long serialVersionUID = 4782193774524960401L;
  private String scene;
  private String page;

  @SerializedName("check_path")
  private boolean checkPath = true;

  @SerializedName("env_version")
  private String envVersion = DEFAULT_ENV_VERSION;

  private int width = 430;

  @SerializedName("auto_color")
  private boolean autoColor = true;

  @SerializedName("is_hyaline")
  private boolean isHyaline = false;

  @SerializedName("line_color")
  private WxMaCodeLineColor lineColor = new WxMaCodeLineColor("0", "0", "0");

  public static WxaCodeUnlimit fromJson(String json) {
    return WxMaGsonBuilder.create().fromJson(json, WxaCodeUnlimit.class);
  }

}
