package com.ossez.wechat.wecom.bean.external;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.moment.VisibleRange;
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
 * 企业发表内容到客户的朋友圈 创建发表任务
 *
 * @author leiin  created on  2021-10-29
 */
@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpAddMomentTask implements Serializable {
  @SerializedName("visible_range")
  private VisibleRange visibleRange;

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
