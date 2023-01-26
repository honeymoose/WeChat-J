package com.ossez.wechat.wecom.bean.license.order;

import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;
import com.ossez.wechat.wecom.bean.license.WxCpTpLicenseOrder;

/**
 * 订单详情结果
 * 文档：https://developer.work.weixin.qq.com/document/path/95648
 *
 * @author Totoro  created on  2022/06/27 11:56:03
 */
@EqualsAndHashCode(callSuper = true)
@Data
public class WxCpTpLicenseOrderInfoResp extends WxCpBaseResp {

  private static final long serialVersionUID = 7000171280773370910L;

  private WxCpTpLicenseOrder order;


  /**
   * From json wx cp tp license order info resp.
   *
   * @param json the json
   * @return the wx cp tp license order info resp
   */
  public static WxCpTpLicenseOrderInfoResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpTpLicenseOrderInfoResp.class);
  }


}
