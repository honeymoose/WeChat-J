package com.ossez.wechat.oa.bean;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Data;
import com.ossez.wechat.oa.bean.material.WxMpNewsArticle;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

/**
 * 群发时用到的图文消息素材.
 *
 * @author chanjarster
 */
@Data
public class WxMpMassNews implements Serializable {
  private static final long serialVersionUID = 565937155013581016L;

  private List<WxMpNewsArticle> articles = new ArrayList<>();

  public void addArticle(WxMpNewsArticle article) {
    this.articles.add(article);
  }

  public String toJson() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  public boolean isEmpty() {
    return this.articles == null || this.articles.isEmpty();
  }

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

}
