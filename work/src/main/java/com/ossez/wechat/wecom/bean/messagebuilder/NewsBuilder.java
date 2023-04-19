package com.ossez.wechat.wecom.bean.messagebuilder;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.wecom.bean.article.NewArticle;
import com.ossez.wechat.wecom.bean.message.WxCpMessage;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
 * 图文消息builder
 * <pre>
 * 用法:
 * WxCustomMessage m = WxCustomMessage.NEWS().addArticle(article).toUser(...).build();
 * </pre>
 *
 * @author Daniel Qian
 */
public final class NewsBuilder extends BaseBuilder<NewsBuilder> {

  private List<NewArticle> articles = new ArrayList<>();

  /**
   * Instantiates a new News builder.
   */
  public NewsBuilder() {
    this.msgType = WeChatConstant.MsgType.NEWS;
  }

  /**
   * Add article news builder.
   *
   * @param articles the articles
   * @return the news builder
   */
  public NewsBuilder addArticle(NewArticle... articles) {
    Collections.addAll(this.articles, articles);
    return this;
  }

  /**
   * Articles news builder.
   *
   * @param articles the articles
   * @return the news builder
   */
  public NewsBuilder articles(List<NewArticle> articles) {
    this.articles = articles;
    return this;
  }

  @Override
  public WxCpMessage build() {
    WxCpMessage m = super.build();
    m.setArticles(this.articles);
    return m;
  }
}
