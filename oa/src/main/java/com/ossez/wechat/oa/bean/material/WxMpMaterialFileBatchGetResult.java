package com.ossez.wechat.oa.bean.material;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import lombok.Data;

/**
 * @author codepiano
 */
@Data
public class WxMpMaterialFileBatchGetResult implements Serializable {
  private static final long serialVersionUID = -560388368297267884L;

  private int totalCount;
  private int itemCount;
  private List<WxMaterialFileBatchGetNewsItem> items;

  @Override
  public String toString() {
    return WxMpGsonBuilder.create().toJson(this);
  }

  @Data
  public static class WxMaterialFileBatchGetNewsItem implements Serializable {
    private static final long serialVersionUID = -8300080343204117459L;

    private String mediaId;
    private Date updateTime;
    private String name;
    private String url;

    @Override
    public String toString() {
      return WxMpGsonBuilder.create().toJson(this);
    }
  }
}
