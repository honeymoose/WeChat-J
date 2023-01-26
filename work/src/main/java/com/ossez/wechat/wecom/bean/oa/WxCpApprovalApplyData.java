package com.ossez.wechat.wecom.bean.oa;

import com.ossez.wechat.wecom.bean.oa.applydata.ApplyDataContent;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

/**
 * 审批申请数据
 *
 * @author element
 */
@Data
public class WxCpApprovalApplyData implements Serializable {

  private static final long serialVersionUID = 4061352949894274704L;

  private List<ApplyDataContent> contents;

}
