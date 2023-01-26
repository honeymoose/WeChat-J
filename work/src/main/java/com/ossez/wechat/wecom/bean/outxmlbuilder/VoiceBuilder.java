package com.ossez.wechat.wecom.bean.outxmlbuilder;

import com.ossez.wechat.wecom.bean.message.WxCpXmlOutVoiceMessage;

/**
 * 语音消息builder
 *
 * @author Daniel Qian
 */
public final class VoiceBuilder extends BaseBuilder<VoiceBuilder, WxCpXmlOutVoiceMessage> {

  private String mediaId;

  /**
   * Media id voice builder.
   *
   * @param mediaId the media id
   * @return the voice builder
   */
  public VoiceBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxCpXmlOutVoiceMessage build() {
    WxCpXmlOutVoiceMessage m = new WxCpXmlOutVoiceMessage();
    setCommon(m);
    m.setMediaId(this.mediaId);
    return m;
  }

}
