package com.ossez.wechat.wecom.bean.outxmlbuilder;

import com.ossez.wechat.wecom.bean.message.WxCpXmlOutTextMessage;

/**
 * 文本消息builder
 *
 * @author Daniel Qian
 */
public final class TextBuilder extends BaseBuilder<TextBuilder, WxCpXmlOutTextMessage> {
  private String content;

  /**
   * Content text builder.
   *
   * @param content the content
   * @return the text builder
   */
  public TextBuilder content(String content) {
    this.content = content;
    return this;
  }

  @Override
  public WxCpXmlOutTextMessage build() {
    WxCpXmlOutTextMessage m = new WxCpXmlOutTextMessage();
    setCommon(m);
    m.setContent(this.content);
    return m;
  }
}
