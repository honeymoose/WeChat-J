package com.ossez.wechat.oa.bean.material;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import lombok.Data;

/**
 * 图文素材.
 *
 * @author codepiano
 */
@Data
public class WxMpMaterialNews implements Serializable {
  private static final long serialVersionUID = -3283203652013494976L;

  private Date createTime;
  private Date updateTime;

  private List<WxMpNewsArticle> articles = new ArrayList<>();

  public List<WxMpNewsArticle> getArticles() {
    return this.articles;
  }

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
    return this.toJson();
  }

}
