package com.ossez.wechat.pay.service.impl;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.pay.bean.request.*;
import com.ossez.wechat.pay.bean.result.*;
import com.ossez.wechat.pay.constant.WxPayConstants;
import com.ossez.wechat.pay.exception.WxPayException;
import com.ossez.wechat.pay.service.WxEntrustPapService;
import com.ossez.wechat.pay.service.WxPayService;
import com.ossez.wechat.pay.util.SignUtils;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.net.URLEncoder;

/**
 * @author chenliang
 * created on  2021-08-02 4:53 下午
 */
@Slf4j
@RequiredArgsConstructor
public class WxEntrustPapServiceImpl implements WxEntrustPapService {

  private final WxPayService payService;


  @Override
  public String mpSign(WxMpEntrustRequest wxMpEntrustRequest) throws WxPayException {
    StringBuilder signStrTemp = new StringBuilder(payService.getPayBaseUrl() + "/papay/entrustweb");
    signStrTemp.append("?appid=").append(wxMpEntrustRequest.getAppid());
    signStrTemp.append("&contract_code=").append(wxMpEntrustRequest.getContractCode());
    signStrTemp.append("&contract_display_account=").append(URLEncoder.encode(wxMpEntrustRequest.getContractDisplayAccount()));
    signStrTemp.append("&mch_id=").append(wxMpEntrustRequest.getMchId()).append("&notify_url=").append(URLEncoder.encode(wxMpEntrustRequest.getNotifyUrl()));
    signStrTemp.append("&plan_id=").append(wxMpEntrustRequest.getPlanId()).append("&outerid=").append(URLEncoder.encode(wxMpEntrustRequest.getOuterId()));
    signStrTemp.append("&request_serial=").append(wxMpEntrustRequest.getRequestSerial()).append("&timestamp=").append(wxMpEntrustRequest.getTimestamp());
    signStrTemp.append("&version=").append(wxMpEntrustRequest.getVersion()).append("&return_web=").append(wxMpEntrustRequest.getReturnWeb()).append("&sign=").append(wxMpEntrustRequest.getSign());

    return signStrTemp.toString();
  }

  @Override
  public String maSign(WxMaEntrustRequest wxMaEntrustRequest) throws WxPayException {
    wxMaEntrustRequest.checkAndSign(payService.getConfig());
    wxMaEntrustRequest.setNotifyUrl(URLEncoder.encode(wxMaEntrustRequest.getNotifyUrl()));
    return wxMaEntrustRequest.toString();
  }

  @Override
  public WxH5EntrustResult h5Sign(WxH5EntrustRequest wxH5EntrustRequest) throws WxPayException {
    wxH5EntrustRequest.checkAndSign(payService.getConfig());
    // 微信最新接口signType不能参与签名，否则报错：签约参数签名校验错误
    wxH5EntrustRequest.setSignType(null);

    String sign = SignUtils.createSign(wxH5EntrustRequest, WxPayConstants.SignType.HMAC_SHA256, payService.getConfig().getMchKey(), null);
    /**
     * https://api.mch.weixin.qq.com/papay/h5entrustweb?appid=wxxxxx&contract_code=001
     * &contract_display_account=name1&mch_id=1223816102&notify_url=www.qq.com%2Ftest%2Fpapay&plan_id=106
     * &request_serial=123&return_appid= wxcbda96de0b165542&clientip=12.1.1.12&timestamp=1414488825
     * &version=1.0&sign= 130C7B07DD3B8074F7BF8BEF5C9A86487A1C57478F8C55587876B9C782F72036
     */
    String url = payService.getPayBaseUrl() + "/papay/h5entrustweb";

    StringBuilder strBuilder = new StringBuilder(url);
    strBuilder.append("?appid=").append(wxH5EntrustRequest.getAppid());
    strBuilder.append("&contract_code=").append(wxH5EntrustRequest.getContractCode());
    strBuilder.append("&contract_display_account=").append(URLEncoder.encode(wxH5EntrustRequest.getContractDisplayAccount()));
    strBuilder.append("&mch_id=").append(wxH5EntrustRequest.getMchId()).append("&notify_url=").append(URLEncoder.encode(wxH5EntrustRequest.getNotifyUrl()));
    strBuilder.append("&plan_id=").append(wxH5EntrustRequest.getPlanId()).append("&outerid=").append(URLEncoder.encode(wxH5EntrustRequest.getOuterId()));
    strBuilder.append("&return_appid=").append(wxH5EntrustRequest.getReturnAppid());
    strBuilder.append("&clientip=").append(wxH5EntrustRequest.getClientIp());
    strBuilder.append("&request_serial=").append(wxH5EntrustRequest.getRequestSerial()).append("&timestamp=").append(wxH5EntrustRequest.getTimestamp());
    strBuilder.append("&version=").append(wxH5EntrustRequest.getVersion()).append("&sign=").append(sign);

    log.debug("h5纯签约请求URL：{}", strBuilder.toString());

    String responseContent = payService.getV3(strBuilder.toString());
    WxH5EntrustResult result = BaseWxPayResult.fromXML(responseContent, WxH5EntrustResult.class);
    result.checkResult(payService, wxH5EntrustRequest.getSignType(), true);
    return result;
  }

  @Override
  public WxPayEntrustResult paySign(WxPayEntrustRequest wxPayEntrustRequest) throws WxPayException {
    wxPayEntrustRequest.checkAndSign(payService.getConfig());

    String url = payService.getPayBaseUrl() + "/pay/contractorder";
    String responseContent = payService.post(url, wxPayEntrustRequest.toXML(), false);
    WxPayEntrustResult result = BaseWxPayResult.fromXML(responseContent, WxPayEntrustResult.class);
    result.checkResult(payService, wxPayEntrustRequest.getSignType(), true);

    return result;
  }

  @Override
  public WxWithholdResult withhold(WxWithholdRequest wxWithholdRequest) throws WxPayException {
    wxWithholdRequest.checkAndSign(payService.getConfig());
    String url = payService.getPayBaseUrl() + "/pay/pappayapply";
    String responseContent = payService.post(url, wxWithholdRequest.toXML(), false);
    WxWithholdResult result = BaseWxPayResult.fromXML(responseContent, WxWithholdResult.class);
    result.checkResult(payService, wxWithholdRequest.getSignType(), true);
    return result;
  }

  @Override
  public String preWithhold(WxPreWithholdRequest wxPreWithholdRequest) throws WxPayException {
    String requestParam = WxGsonBuilder.create().toJson(wxPreWithholdRequest);
    String url = payService.getPayBaseUrl() + "/v3/papay/contracts/%s/notify";  // %s为{contract_id}
    String httpResponse = payService.postV3(String.format(url, wxPreWithholdRequest.getContractId()), requestParam);
    return httpResponse;
  }

  @Override
  public WxSignQueryResult querySign(WxSignQueryRequest wxSignQueryRequest) throws WxPayException {
    wxSignQueryRequest.checkAndSign(payService.getConfig());
    String url = payService.getPayBaseUrl() + "/papay/querycontract";
    String responseContent = payService.post(url, wxSignQueryRequest.toXML(), false);
    WxSignQueryResult result = BaseWxPayResult.fromXML(responseContent, WxSignQueryResult.class);
    result.checkResult(payService, wxSignQueryRequest.getSignType(), true);
    return result;
  }

  @Override
  public WxTerminationContractResult terminationContract(WxTerminatedContractRequest wxTerminatedContractRequest) throws WxPayException {
    wxTerminatedContractRequest.checkAndSign(payService.getConfig());
    String url = payService.getPayBaseUrl() + "/papay/deletecontract";
    String responseContent = payService.post(url, wxTerminatedContractRequest.toXML(), false);
    WxTerminationContractResult terminationContractResult = BaseWxPayResult.fromXML(responseContent, WxTerminationContractResult.class);
    terminationContractResult.checkResult(payService, wxTerminatedContractRequest.getSignType(), true);
    return terminationContractResult;
  }

  @Override
  public WxWithholdOrderQueryResult papOrderQuery(WxWithholdOrderQueryRequest wxWithholdOrderQueryRequest) throws WxPayException {
    wxWithholdOrderQueryRequest.checkAndSign(payService.getConfig());
    String url = payService.getPayBaseUrl() + "/pay/paporderquery";
    String responseContent = payService.post(url, wxWithholdOrderQueryRequest.toXML(), false);
    WxWithholdOrderQueryResult wxWithholdOrderQueryResult = BaseWxPayResult.fromXML(responseContent, WxWithholdOrderQueryResult.class);
    wxWithholdOrderQueryResult.checkResult(payService, wxWithholdOrderQueryRequest.getSignType(), true);
    return wxWithholdOrderQueryResult;
  }
}
