package com.ossez.wechat.wecom.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 上传文件返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileUpload extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("fileid")
  private String fileId;

  /**
   * From json wx cp file upload.
   *
   * @param json the json
   * @return the wx cp file upload
   */
  public static WxCpFileUpload fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileUpload.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
