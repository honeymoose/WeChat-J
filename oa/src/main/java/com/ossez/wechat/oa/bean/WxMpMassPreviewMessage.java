package com.ossez.wechat.oa.bean;

import lombok.Data;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.io.Serializable;

/**
 * @author miller
 */
@Data
public class WxMpMassPreviewMessage implements Serializable {
  private static final long serialVersionUID = 9095211638358424020L;

  private String toWxUserName;
  private String toWxUserOpenid;
  /**
   * <pre>
   * 消息类型
   * 请使用
   * {@link WeChatConstant.MassMsgType#IMAGE}
   * {@link WeChatConstant.MassMsgType#MPNEWS}
   * {@link WeChatConstant.MassMsgType#TEXT}
   * {@link WeChatConstant.MassMsgType#MPVIDEO}
   * {@link WeChatConstant.MassMsgType#VOICE}
   * 如果msgtype和media_id不匹配的话，会返回系统繁忙的错误
   * </pre>
   */
  private String msgType;
  private String content;
  private String mediaId;

  public WxMpMassPreviewMessage() {
    super();
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }
}
