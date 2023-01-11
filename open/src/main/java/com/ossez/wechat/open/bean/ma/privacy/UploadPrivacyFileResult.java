package com.ossez.wechat.open.bean.ma.privacy;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.Getter;
import lombok.Setter;

/**
 * 上传小程序用户隐私保护指引文件 响应
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
@Getter
@Setter
public class UploadPrivacyFileResult extends WxOpenResult {

  /**
   * 文件的media_id
   */
  @SerializedName("ext_file_media_id")
  private String extFileMediaId;
}
