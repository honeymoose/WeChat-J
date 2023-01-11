package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaSafetyRiskControlService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.safety.request.WxMaUserSafetyRiskRankRequest;
import cn.binarywang.wx.miniapp.bean.safety.response.WxMaUserSafetyRiskRankResponse;
import cn.binarywang.wx.miniapp.constant.WxMaConstants;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.InstantDelivery.SafetyRiskControl.GET_USER_RISK_RANK;

/**
 * @author azouever
 */

@RequiredArgsConstructor
public class WxMaSafetyRiskControlServiceImpl implements WxMaSafetyRiskControlService {

  private final WxMaService service;

  @Override
  public WxMaUserSafetyRiskRankResponse getUserRiskRank(WxMaUserSafetyRiskRankRequest wxMaUserSafetyRiskRankRequest) throws WxErrorException {
    String responseContent = this.service.post(GET_USER_RISK_RANK, wxMaUserSafetyRiskRankRequest.toJson());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    if (jsonObject.get(WxMaConstants.ERRCODE).getAsInt() != 0) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return WxMaUserSafetyRiskRankResponse.fromJson(responseContent);
  }
}
