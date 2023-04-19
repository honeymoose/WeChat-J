package com.ossez.wechat.wecom.bean.messagebuilder;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.wecom.bean.message.WxCpMessage;

/**
 * 获得消息builder
 * <pre>
 * 用法: WxCustomMessage m = WxCustomMessage.FILE().mediaId(...).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class FileBuilder extends BaseBuilder<FileBuilder> {
  private String mediaId;

  /**
   * Instantiates a new File builder.
   */
  public FileBuilder() {
    this.msgType = WeChatConstant.MsgType.FILE;
  }

  /**
   * Media id file builder.
   *
   * @param mediaId the media id
   * @return the file builder
   */
  public FileBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMediaId(this.mediaId);
    return m;
  }
}
