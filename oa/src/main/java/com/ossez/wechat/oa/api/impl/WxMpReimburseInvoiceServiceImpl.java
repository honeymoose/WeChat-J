package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.oa.bean.invoice.reimburse.*;
import lombok.AllArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WxMpReimburseInvoiceService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;

import java.util.List;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.Invoice.*;

/**
 * 电子发票报销方相关接口实现
 * 接口文档: https://developers.weixin.qq.com/doc/offiaccount/WeChat_Invoice/E_Invoice/Reimburser_API_List.html
 * @author <a href="https://github.com/mr-xiaoyu">xiaoyu</a>
 * @since 2021-03-23
 */
@AllArgsConstructor
public class WxMpReimburseInvoiceServiceImpl implements WxMpReimburseInvoiceService {

  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public InvoiceInfoResponse getInvoiceInfo(InvoiceInfoRequest request) throws WxErrorException {
    return InvoiceInfoResponse.fromJson(this.weChatOfficialAccountService.post(GET_INVOICE_INFO,request.toJson()));
  }

  @Override
  public List<InvoiceInfoResponse> getInvoiceBatch(InvoiceBatchRequest request) throws WxErrorException {
    return InvoiceInfoResponse.toList(this.weChatOfficialAccountService.post(GET_INVOICE_BATCH,request.toJson()));
  }

  @Override
  public void updateInvoiceStatus(UpdateInvoiceStatusRequest request) throws WxErrorException {
    this.weChatOfficialAccountService.post(UPDATE_INVOICE_STATUS,request.toJson());
  }

  @Override
  public void updateStatusBatch(UpdateStatusBatchRequest request) throws WxErrorException {
    this.weChatOfficialAccountService.post(UPDATE_STATUS_BATCH,request.toJson());
  }
}
