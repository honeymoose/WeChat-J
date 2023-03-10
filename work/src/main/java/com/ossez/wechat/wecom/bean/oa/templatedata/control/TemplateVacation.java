package com.ossez.wechat.wecom.bean.oa.templatedata.control;

import lombok.Data;
import com.ossez.wechat.wecom.bean.oa.templatedata.TemplateVacationItem;

import java.io.Serializable;
import java.util.List;

/**
 * The type Template vacation.
 *
 * @author gyv12345 @163.com
 */
@Data
public class TemplateVacation implements Serializable {

  private List<TemplateVacationItem> item;

}
