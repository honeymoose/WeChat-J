package com.ossez.wechat.wecom.bean.oa.templatedata;

import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template content.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateContent implements Serializable {
  private static final long serialVersionUID = -5640250983775840865L;

  private List<TemplateControls> controls;
}
