package com.github.binarywang.wxpay.service;

import com.github.binarywang.wxpay.bean.transfer.*;
import com.github.binarywang.wxpay.exception.WxPayException;

/**
 * 商家转账到零钱
 *
 * @author zhongjun
 * created on  2022/6/17
 **/
public interface TransferService {

  /**
   * <pre>
   *
   * 发起商家转账API
   *
   * 请求方式：POST（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_1.shtml">发起商家转账API</a>
   * </pre>
   *
   * @param request 转账请求参数
   * @return TransferBatchesResult 转账结果
   * @throws WxPayException .
   */
  TransferBatchesResult transferBatches(TransferBatchesRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 微信批次单号查询批次单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_2.shtml">微信批次单号查询批次单API</a>
   * </pre>
   *
   * @param request 查询请求参数
   * @return TransferBatchesResult 查询结果
   * @throws WxPayException .
   */
  QueryTransferBatchesResult transferBatchesBatchId(QueryTransferBatchesRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 微信明细单号查询明细单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/batch-id/{batch_id}/details/detail-id/{detail_id}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_3.shtml">微信明细单号查询明细单API</a>
   * </pre>
   *
   * @param batchId  微信批次单号
   * @param detailId 微信明细单号
   * @return TransferBatchDetailResult 查询结果
   * @throws WxPayException .
   */
  TransferBatchDetailResult transferBatchesBatchIdDetail(String batchId, String detailId) throws WxPayException;

  /**
   * <pre>
   *
   * 商家批次单号查询批次单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_5.shtml">商家批次单号查询批次单API</a>
   * </pre>
   *
   * @param request 查询请求参数
   * @return TransferBatchesResult 查询结果
   * @throws WxPayException .
   * @throws WxPayException .
   */
  QueryTransferBatchesResult transferBatchesOutBatchNo(QueryTransferBatchesRequest request) throws WxPayException;

  /**
   * <pre>
   *
   * 商家明细单号查询明细单API
   *
   * 请求方式：GET（HTTPS）
   * 请求地址：<a href="https://api.mch.weixin.qq.com/v3/transfer/batches/out-batch-no/{out_batch_no}/details/out-detail-no/{out_detail_no}">请求地址</a>
   *
   * 文档地址：<a href="https://pay.weixin.qq.com/wiki/doc/apiv3/apis/chapter4_3_6.shtml">商家明细单号查询明细单API</a>
   * </pre>
   *
   * @param outBatchNo  商家明细单号
   * @param outDetailNo 商家批次单号
   * @return TransferBatchDetailResult 查询结果
   * @throws WxPayException .
   */
  TransferBatchDetailResult transferBatchesOutBatchNoDetail(String outBatchNo, String outDetailNo) throws WxPayException;

}
