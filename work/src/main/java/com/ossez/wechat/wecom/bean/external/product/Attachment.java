package com.ossez.wechat.wecom.bean.external.product;

import com.ossez.wechat.wecom.constant.WxCpConsts;
import lombok.Data;

import java.io.Serializable;

/**
 * 商品画册附件
 *
 * @author <a href="https://github.com/Loading-Life">Lo_ading</a>
 */
@Data
public class Attachment implements Serializable {
  private static final long serialVersionUID = -4545283630169056262L;

  /**
   * NOTE: 20211110 字段接口未返回
   */
  private String type;

  /**
   * 附件类型，目前仅支持image
   */
  private Image image;

  /**
   * Sets image.
   *
   * @param image the image
   */
  public void setImage(Image image) {
    this.image = image;
    this.type = WxCpConsts.ProductAttachmentType.IMAGE;
  }
}
