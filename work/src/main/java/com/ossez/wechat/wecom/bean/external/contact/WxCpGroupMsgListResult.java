package com.ossez.wechat.wecom.bean.external.contact;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Getter;
import lombok.Setter;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.external.msg.Attachment;
import com.ossez.wechat.wecom.bean.external.msg.Text;

import java.io.Serializable;
import java.util.List;

/**
 * <pre>
 * 获取企业群发成员执行结果
 * 参考文档：https://work.weixin.qq.com/api/doc/90000/90135/93338
 * </pre>
 *
 * @author <a href="https://github.com/wslongchen">Mr.Pan</a>
 */
@Getter
@Setter
public class WxCpGroupMsgListResult extends WxCpBaseResp implements Serializable {

  private static final long serialVersionUID = 3464981991558716620L;

  @SerializedName("group_msg_list")
  private List<ExternalContactGroupMsgInfo> groupMsgList;

  @SerializedName("next_cursor")
  private String nextCursor;

  /**
   * The type External contact group msg info.
   */
  @Getter
  @Setter
  public static class ExternalContactGroupMsgInfo implements Serializable {

    private static final long serialVersionUID = 3108435608725559381L;
    @SerializedName("msgid")
    private String msgId;

    private String creator;

    private Text text;

    private List<Attachment> attachments;

    @SerializedName("create_type")
    private Integer createType;

    @SerializedName("create_time")
    private Long createTime;

  }

  /**
   * From json wx cp group msg list result.
   *
   * @param json the json
   * @return the wx cp group msg list result
   */
  public static WxCpGroupMsgListResult fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpGroupMsgListResult.class);
  }

}
