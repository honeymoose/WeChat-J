package com.ossez.wechat.wecom.tp.service.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.config.WxCpTpConfigStorage;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpTpUnionidToExternalUseridResult;
import com.ossez.wechat.wecom.tp.service.WxCpTpIdConvertService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;


/**
 * @author cocoa
 */
@RequiredArgsConstructor
public class WxCpTpIdConvertServiceImpl implements WxCpTpIdConvertService {
  private final WxCpTpService mainService;

  @Override
  public WxCpTpUnionidToExternalUseridResult unionidToExternalUserid(String cropId, String unionid, String openid, Integer subjectType) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("unionid", unionid);
    json.addProperty("openid", openid);
    if (subjectType != null) {
      json.addProperty("subject_type", subjectType);
    }
    WxCpTpConfigStorage wxCpTpConfigStorage = mainService.getWxCpTpConfigStorage();
    String accessToken = wxCpTpConfigStorage.getAccessToken(cropId);
    String url = wxCpTpConfigStorage.getApiUrl("/cgi-bin/idconvert/unionid_to_external_userid");
    url += "?access_token=" + accessToken;
    String responseContent = this.mainService.post(url, json.toString());
    return WxCpTpUnionidToExternalUseridResult.fromJson(responseContent);
  }


}
