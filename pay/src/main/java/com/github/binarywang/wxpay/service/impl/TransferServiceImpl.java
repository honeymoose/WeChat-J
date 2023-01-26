package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.TransferService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.v3.util.RsaCryptoUtil;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import lombok.RequiredArgsConstructor;

import java.security.cert.X509Certificate;
import java.util.List;

/**
 * 商家转账到零钱
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
@RequiredArgsConstructor
public class TransferServiceImpl implements TransferService {

  private static final Gson GSON = new GsonBuilder().create();
  private final WxPayService payService;

  @Override
  public TransferBatchesResult transferBatches(TransferBatchesRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches", this.payService.getPayBaseUrl());
    List<TransferBatchesRequest.TransferDetail> transferDetailList = request.getTransferDetailList();
    X509Certificate validCertificate = this.payService.getConfig().getVerifier().getValidCertificate();
    for (TransferBatchesRequest.TransferDetail detail : transferDetailList) {
      RsaCryptoUtil.encryptFields(detail, validCertificate);
    }
    String result = this.payService.postV3WithWechatpaySerial(url, GSON.toJson(request));
    return GSON.fromJson(result, TransferBatchesResult.class);
  }

  @Override
  public QueryTransferBatchesResult transferBatchesBatchId(QueryTransferBatchesRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/batch-id/%s?need_query_detail=%s&offset=%s&limit=%s&detail_status=%s",
      this.payService.getPayBaseUrl(), request.getBatchId(), request.getNeedQueryDetail(), request.getOffset(), request.getLimit(), request.getDetailStatus());
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, QueryTransferBatchesResult.class);
  }

  @Override
  public TransferBatchDetailResult transferBatchesBatchIdDetail(String batchId, String detailId) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/batch-id/%s/details/detail-id/%s", this.payService.getPayBaseUrl(), batchId, detailId);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBatchDetailResult.class);
  }

  @Override
  public QueryTransferBatchesResult transferBatchesOutBatchNo(QueryTransferBatchesRequest request) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/out-batch-no/%s?need_query_detail=%s&offset=%s&limit=%s&detail_status=%s",
      this.payService.getPayBaseUrl(), request.getOutBatchNo(), request.getNeedQueryDetail(), request.getOffset(), request.getLimit(), request.getDetailStatus());
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, QueryTransferBatchesResult.class);
  }

  @Override
  public TransferBatchDetailResult transferBatchesOutBatchNoDetail(String outBatchNo, String outDetailNo) throws WxPayException {
    String url = String.format("%s/v3/transfer/batches/out-batch-no/%s/details/out-detail-no/%s", this.payService.getPayBaseUrl(), outBatchNo, outDetailNo);
    String result = this.payService.getV3(url);
    return GSON.fromJson(result, TransferBatchDetailResult.class);
  }
}
