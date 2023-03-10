package com.ossez.wechat.wecom.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Template date range.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateDateRange implements Serializable {

  private static final long serialVersionUID = -9209035461466543180L;

  /**
   * 时间刻度：hour-精确到分钟, halfday—上午/下午
   */
  private String type;
}
