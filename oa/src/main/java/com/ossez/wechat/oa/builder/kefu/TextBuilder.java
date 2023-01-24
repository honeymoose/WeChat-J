package com.ossez.wechat.oa.builder.kefu;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.bean.kefu.WxMpKefuMessage;

/**
 * 文本消息builder
 * <pre>
 * 用法: WxMpKefuMessage m = WxMpKefuMessage.TEXT().content(...).toUser(...).build();
 * </pre>
 *
 * @author chanjarster
 */
public final class TextBuilder extends BaseBuilder<TextBuilder> {
  private String content;

  public TextBuilder() {
    this.msgType = WeChatConstant.KefuMsgType.TEXT;
  }

  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setContent(this.content);
    return m;
  }
}
