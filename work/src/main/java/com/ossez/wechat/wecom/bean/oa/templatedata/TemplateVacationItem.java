package com.ossez.wechat.wecom.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;

/**
 * The type Template vacation item.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateVacationItem implements Serializable {

  private static final long serialVersionUID = 4510594801023791319L;

  private Integer id;

  private TemplateTitle name;
}
