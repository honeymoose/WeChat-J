package com.ossez.wechat.oa.builder.kefu;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.bean.kefu.WxMpKefuMessage;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxMpKefuMessage m = WxMpKefuMessage.MPNEWSARTICLE().articleId("xxxxx").toUser(...).build();
 * </pre>
 *
 * @author <a href="https://github.com/leejuncheng">JCLee</a>
 */
public final class MpNewsArticleBuilder extends BaseBuilder<MpNewsArticleBuilder>{
  private String articleId;

  public MpNewsArticleBuilder() {
    this.msgType = WeChatConstant.MsgType.MP_NEWS_ARTICLE;
  }

  public MpNewsArticleBuilder articleId(String articleId) {
    this.articleId = articleId;
    return this;
  }

  @Override
  public WxMpKefuMessage build() {
    WxMpKefuMessage m = super.build();
    m.setMpNewsArticleId(this.articleId);
    return m;
  }
}
