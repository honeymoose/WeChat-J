package com.ossez.wechat.oa.bean.device;

import lombok.Data;
import lombok.EqualsAndHashCode;
import com.ossez.wechat.common.util.json.WxGsonBuilder;

import java.util.List;

/**
 * @author keungtung.
 * created on  10/12/2016
 */
@Data
@EqualsAndHashCode(callSuper = false)
public class WxDeviceAuthorizeResult extends AbstractDeviceBean {
  private static final long serialVersionUID = 9105294570912249811L;

  private List<BaseResp> resp;

  public static WxDeviceAuthorizeResult fromJson(String response) {
    return WxGsonBuilder.create().fromJson(response, WxDeviceAuthorizeResult.class);
  }

}
