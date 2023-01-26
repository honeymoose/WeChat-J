package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.msg.Attachment;
import com.ossez.wechat.wecom.bean.external.msg.Text;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.List;

/**
 * 新客户欢迎语.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-08-16
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpWelcomeMsg implements Serializable {
  private static final long serialVersionUID = 4170843890468921757L;

  @SerializedName("welcome_code")
  private String welcomeCode;

  private Text text;

  private List<Attachment> attachments;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
