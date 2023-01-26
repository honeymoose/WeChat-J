package com.ossez.wechat.wecom.bean.oa;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.oa.templatedata.TemplateContent;
import com.ossez.wechat.wecom.bean.oa.templatedata.TemplateTitle;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批模板详情
 *
 * @author gyv12345 @163.com
 */
@Data
public class WxCpOaApprovalTemplateResult implements Serializable {
  private static final long serialVersionUID = 6690547131189343887L;

  @SerializedName("errcode")
  private Integer errCode;

  @SerializedName("errmsg")
  private String errMsg;

  @SerializedName("template_names")
  private List<TemplateTitle> templateNames;

  @SerializedName("template_content")
  private TemplateContent templateContent;

}
