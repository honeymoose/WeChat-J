package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaImmediateDeliveryService;
import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AbnormalConfirmResponse;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.AddOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.BindAccountResponse;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.CancelOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.FollowWaybillRequest;
import cn.binarywang.wx.miniapp.bean.delivery.FollowWaybillResponse;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.GetOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderRequest;
import cn.binarywang.wx.miniapp.bean.delivery.MockUpdateOrderResponse;
import cn.binarywang.wx.miniapp.bean.delivery.QueryFollowTraceRequest;
import cn.binarywang.wx.miniapp.bean.delivery.QueryFollowTraceResponse;
import cn.binarywang.wx.miniapp.bean.delivery.QueryWaybillTraceRequest;
import cn.binarywang.wx.miniapp.bean.delivery.QueryWaybillTraceResponse;
import cn.binarywang.wx.miniapp.bean.delivery.TraceWaybillRequest;
import cn.binarywang.wx.miniapp.bean.delivery.TraceWaybillResponse;
import cn.binarywang.wx.miniapp.bean.delivery.base.WxMaDeliveryBaseResponse;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants;
import cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.InstantDelivery;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;

/**
 * ?????????????????????????????????.
 * <pre>
 *     ???????????????https://developers.weixin.qq.com/miniprogram/dev/platform-capabilities/industry/immediate-delivery/overview.html
 * </pre>
 *
 * @author Luo
 * @version 1.0
 * created on  2021-10-13 16:40
 */
@RequiredArgsConstructor
public class WxMaImmediateDeliveryServiceImpl implements WxMaImmediateDeliveryService {

  /**
   * ???????????????.
   */
  public static final String ERR_CODE = "errcode";

  /**
   * ?????????????????????.
   */
  public static final String SF_ERR_CODE = "resultcode";

  /**
   * ????????????????????????.
   */
  public static final String SF_ERR_MSG = "resultmsg";

  /**
   * ?????????????????????.
   */
  public static final int SUCCESS_CODE = 0;

  private final WxMaService wxMaService;

  /**
   * ?????????????????????.
   * <pre>
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getBindAccount.html
   * </pre>
   *
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public BindAccountResponse getBindAccount() throws WxErrorException {
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.GET_BIND_ACCOUNT, "{}"),
      BindAccountResponse.class);
  }

  /**
   * ??????????????????.
   * <pre>
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.addOrder.html
   * </pre>
   *
   * @param request request
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public AddOrderResponse addOrder(final AddOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.PlaceAnOrder.ADD_ORDER, request),
      AddOrderResponse.class);
  }

  /**
   * ?????????????????????.
   * <pre>
   * ????????????????????????????????????????????????????????????????????????????????????????????????
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.getOrder.html
   * </pre>
   *
   * @param request request
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public GetOrderResponse getOrder(final GetOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.GET_ORDER, request),
      GetOrderResponse.class);
  }

  /**
   * ?????????????????????.
   * <pre>
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.cancelOrder.html
   * </pre>
   *
   * @param request request
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public CancelOrderResponse cancelOrder(final CancelOrderRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.Cancel.CANCEL_ORDER, request),
      CancelOrderResponse.class);
  }

  /**
   * ?????????????????????????????????????????????.
   * <pre>
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.abnormalConfirm.html
   * </pre>
   *
   * @param request request
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public AbnormalConfirmResponse abnormalConfirm(final AbnormalConfirmRequest request) throws WxErrorException {
    request.getDeliverySign();
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.Cancel.ABNORMAL_CONFIRM, request),
      AbnormalConfirmResponse.class);
  }

  /**
   * ???????????????????????????????????????, ???????????????????????????????????????????????????????????????????????????.
   * <pre>
   * ???????????????https://developers.weixin.qq.com/miniprogram/dev/api-backend/open-api/immediate-delivery/by-business/immediateDelivery.mockUpdateOrder.html
   * </pre>
   *
   * @param request request
   * @return ??????
   * @throws WxErrorException ??????
   */
  @Override
  public MockUpdateOrderResponse mockUpdateOrder(final MockUpdateOrderRequest request) throws WxErrorException {
    return this.parse(this.wxMaService.post(WxMaApiUrlConstants.InstantDelivery.MOCK_UPDATE_ORDER, request),
      MockUpdateOrderResponse.class);
  }

  @Override
  public TraceWaybillResponse traceWaybill(
    TraceWaybillRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.TRACE_WAYBILL_URL, request);
    TraceWaybillResponse response = TraceWaybillResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;

  }

  @Override
  public QueryWaybillTraceResponse queryWaybillTrace(
    QueryWaybillTraceRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.QUERY_WAYBILL_TRACE_URL, request);
    QueryWaybillTraceResponse response = QueryWaybillTraceResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public FollowWaybillResponse followWaybill(
    FollowWaybillRequest request) throws WxErrorException {
    String responseContent = this.wxMaService.post(InstantDelivery.FOLLOW_WAYBILL_URL, request);
    FollowWaybillResponse response = FollowWaybillResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  @Override
  public QueryFollowTraceResponse queryFollowTrace(
    QueryFollowTraceRequest request) throws WxErrorException  {
    String responseContent = this.wxMaService.post(InstantDelivery.QUERY_FOLLOW_TRACE_URL, request);
    QueryFollowTraceResponse response = QueryFollowTraceResponse.fromJson(responseContent);
    if (response.getErrcode() == -1) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    return response;
  }

  /**
   * ????????????.
   *
   * @param responseContent ????????????
   * @param valueType       ??????
   * @param <T>             ??????
   * @return ??????
   * @throws WxErrorException ??????
   */
  private <T extends WxMaDeliveryBaseResponse> T parse(final String responseContent, final Class<T> valueType) throws WxErrorException {
    if (StringUtils.isBlank(responseContent)) {
      throw new RuntimeException("the responseContent cannot be empty");
    }
    // ?????????Json??????
    JsonObject jsonObject = GsonParser.parse(responseContent);
    // ??????????????????????????? ??? errcode==0 ?????? ????????? ???????????? ????????? resultcode ?????????
    JsonElement element = jsonObject.get(ERR_CODE);
    // ???????????????????????????????????????
    if (!ObjectUtils.isEmpty(element) && SUCCESS_CODE != element.getAsInt()) {
      throw new WxErrorException(WxError.fromJson(responseContent, WxType.MiniApp));
    }
    // ??????????????????????????????
    JsonElement delivery = jsonObject.get(SF_ERR_CODE);
    if (!ObjectUtils.isEmpty(delivery) && SUCCESS_CODE != delivery.getAsInt()) {
      throw new WxErrorException(jsonObject.get(SF_ERR_MSG).getAsString());
    }
    // ???????????????????????????
    return WxMaDeliveryBaseResponse.fromJson(responseContent, valueType);
  }

}
