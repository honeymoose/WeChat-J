package com.ossez.wechat.wecom.bean.oa.wedrive;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.io.Serializable;

/**
 * 新建文件/微文档 返回信息.
 *
 * @author Wang_Wong
 */
@Data
public class WxCpFileCreate extends WxCpBaseResp implements Serializable {
  private static final long serialVersionUID = -5028321625142879581L;

  @SerializedName("fileid")
  private String fileId;

  @SerializedName("url")
  private String url;

  /**
   * From json wx cp file create.
   *
   * @param json the json
   * @return the wx cp file create
   */
  public static WxCpFileCreate fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpFileCreate.class);
  }

  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }

}
