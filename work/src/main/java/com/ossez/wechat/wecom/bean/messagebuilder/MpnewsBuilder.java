package com.ossez.wechat.wecom.bean.messagebuilder;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.wecom.bean.article.MpnewsArticle;
import com.ossez.wechat.wecom.bean.message.WxCpMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * mpnews类型的图文消息builder
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.MPNEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author Binary Wang
 */
public final class MpnewsBuilder extends BaseBuilder<MpnewsBuilder> {
  private List<MpnewsArticle> articles = new ArrayList<>();

  private String mediaId;

  /**
   * Instantiates a new Mpnews builder.
   */
  public MpnewsBuilder() {
    this.msgType = WeChatConstant.MsgType.MPNEWS;
  }

  /**
   * Media id mpnews builder.
   *
   * @param mediaId the media id
   * @return the mpnews builder
   */
  public MpnewsBuilder mediaId(String mediaId) {
    this.mediaId = mediaId;
    return this;
  }

  /**
   * Add article mpnews builder.
   *
   * @param articles the articles
   * @return the mpnews builder
   */
  public MpnewsBuilder addArticle(MpnewsArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  /**
   * Articles mpnews builder.
   *
   * @param articles the articles
   * @return the mpnews builder
   */
  public MpnewsBuilder articles(List<MpnewsArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setMpnewsArticles(this.articles);
    if (this.mediaId != null) {
      m.setMediaId(this.mediaId);
    }

    return m;
  }
}
