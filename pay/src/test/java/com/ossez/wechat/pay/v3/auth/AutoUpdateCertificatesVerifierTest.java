package com.ossez.wechat.pay.v3.auth;

import com.ossez.wechat.pay.bean.merchanttransfer.TransferCreateRequest;
import com.ossez.wechat.pay.bean.merchanttransfer.TransferCreateRequest.TransferDetailList;
import com.ossez.wechat.pay.bean.merchanttransfer.TransferCreateResult;
import com.ossez.wechat.pay.exception.WxPayException;
import com.ossez.wechat.pay.service.WxPayService;
import com.ossez.wechat.pay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.apache.http.util.Asserts;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 商家转账到零钱（直连商户）- 商户号配置信息错误时健壮性判断单元测试
 * @author imyzt
 * created on  2022/10/23
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class AutoUpdateCertificatesVerifierTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  @Test
  public void testVerify() throws WxPayException {
    TransferDetailList transferDetailList = new TransferDetailList();
    transferDetailList.setOutDetailNo("test")
      .setOpenid("test")
      .setTransferAmount(1)
      .setOutDetailNo("test")
      .setUserName("test");
    TransferCreateRequest req = TransferCreateRequest.builder()
      .appid("wxd930ea5d5a258f4f")
      .batchName("test")
      .outBatchNo("")
      .totalAmount(1)
      .totalNum(1)
      .transferDetailList(Lists.newArrayList(transferDetailList))
      .build();
    TransferCreateResult transfer = payService.getMerchantTransferService().createTransfer(req);
    Asserts.notNull(transfer, "transfer");

    // 商户未申请过证书。请到商户平台上申请证书授权机构颁发的证书。详情可参考:http://kf.qq.com/faq/180824JvUZ3i180824YvMNJj.html

  }
}
