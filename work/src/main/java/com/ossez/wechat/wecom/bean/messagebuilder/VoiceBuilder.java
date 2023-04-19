package com.ossez.wechat.wecom.bean.messagebuilder;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.wecom.bean.message.WxCpMessage;

/**
 * 语音消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.VOICE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder> {
  private String mediaId;

  /**
   * Instantiates a new Voice builder.
   */
  public VoiceBuilder() {
    this.msgType = WeChatConstant.MsgType.VOICE;
  }

  /**
   * Media id voice builder.
   *
   * @param media_id the media id
   * @return the voice builder
   */
  public VoiceBuilder mediaId(String media_id) {
    this.mediaId = media_id;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
