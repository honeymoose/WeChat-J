package com.github.binarywang.wxpay.service.impl;

import com.github.binarywang.wxpay.bean.media.ImageUploadResult;
import com.github.binarywang.wxpay.exception.WxPayException;
import com.github.binarywang.wxpay.service.MerchantMediaService;
import com.github.binarywang.wxpay.service.WxPayService;
import com.github.binarywang.wxpay.testbase.ApiTestModule;
import com.google.inject.Inject;
import lombok.extern.slf4j.Slf4j;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.File;
import java.io.IOException;

/**
 * <pre>
 *  媒体文件上传测试类
 * </pre>
 *
 * @author zhouyongshen
 */
@Slf4j
@Test
@Guice(modules = ApiTestModule.class)
public class MerchantMediaServiceImplTest {

  @Inject
  private  WxPayService wxPayService;

  @Test
  public  void testImageUploadV3() throws WxPayException, IOException {

    MerchantMediaService merchantMediaService=new MerchantMediaServiceImpl(wxPayService);

      String filePath="你的图片文件的路径地址";
//    String filePath="WxJava/images/banners/wiki.jpg";

    File file=new File(filePath);

    ImageUploadResult imageUploadResult = merchantMediaService.imageUploadV3(file);
    String mediaId = imageUploadResult.getMediaId();

    log.info("mediaId1：[{}]",mediaId);

    File file2=new File(filePath);

    ImageUploadResult imageUploadResult2 = merchantMediaService.imageUploadV3(file2);
    String mediaId2 = imageUploadResult2.getMediaId();

    log.info("mediaId2：[{}]",mediaId2);

  }
}
