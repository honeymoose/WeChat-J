package com.ossez.wechat.oa.bean.material;

import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
public class WxMediaImgUploadResult implements Serializable {
  private static final long serialVersionUID = 1996392453428768829L;
  private String url;

  public static WxMediaImgUploadResult fromJson(String json) {
    return WxMpGsonBuilder.create().fromJson(json, WxMediaImgUploadResult.class);
  }

}
