package com.ossez.wechat.pay.service.impl;

import com.ossez.wechat.pay.bean.ecommerce.SignatureHeader;
import com.ossez.wechat.pay.exception.WxPayException;
import com.ossez.wechat.pay.service.WxPayService;
import com.ossez.wechat.pay.testbase.ApiTestModule;
import com.google.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

/**
 * 测试类
 *
 * @author yuanbo
 * @create 2022-04-26-22:33 PM
 */
@Test
@Guice(modules = ApiTestModule.class)
public class ProfitSharingV3ServiceImplTest {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());

  @Inject
  private WxPayService payService;

  @Test
  public void testProfitSharingNotifyData() throws WxPayException {
    SignatureHeader header = new SignatureHeader();
    header.setSerialNo("Wechatpay-Serial");
    header.setTimeStamp("Wechatpay-Timestamp");
    header.setNonce("Wechatpay-Nonce");
    header.setSigned("Wechatpay-Signature");
    String data = "body";
    this.logger.info(this.payService.getProfitSharingV3Service().getProfitSharingNotifyData(data,header).toString());
  }
}
