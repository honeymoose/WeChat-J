package cn.binarywang.wx.miniapp.bean;

import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import okhttp3.MediaType;

/**
 * 微信二维码（小程序码）包装器.
 *
 * @author Element
 */
public abstract class AbstractWxMaQrcodeWrapper {
  public MediaType toJson() {
    return null; //WxMaGsonBuilder.create().toJson(this);
  }

}
