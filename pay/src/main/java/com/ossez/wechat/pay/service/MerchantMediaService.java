package com.ossez.wechat.pay.service;

import com.ossez.wechat.pay.bean.media.ImageUploadResult;
import com.ossez.wechat.pay.exception.WxPayException;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;

/**
 * <pre>
 * 微信支付通用媒体接口.
 * </pre>
 *
 * @author zhouyongshen
 */
public interface MerchantMediaService {
  /**
   * <pre>
   * 通用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/upload
   * </pre>
   *
   * @param imageFile 需要上传的图片文件
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult imageUploadV3(File imageFile) throws WxPayException, IOException;

  /**
   * <pre>
   * 通用接口-图片上传API
   * 文档详见: https://pay.weixin.qq.com/wiki/doc/apiv3/wxpay/tool/chapter3_1.shtml
   * 接口链接：https://api.mch.weixin.qq.com/v3/merchant/media/upload
   * </pre>
   *
   * @param inputStream 需要上传的图片文件流
   * @param fileName 需要上传的图片文件名
   * @return ImageUploadResult 微信返回的媒体文件标识Id。示例值：6uqyGjGrCf2GtyXP8bxrbuH9-aAoTjH-rKeSl3Lf4_So6kdkQu4w8BYVP3bzLtvR38lxt4PjtCDXsQpzqge_hQEovHzOhsLleGFQVRF-U_0
   * @throws WxPayException the wx pay exception
   */
  ImageUploadResult imageUploadV3(InputStream inputStream, String fileName) throws WxPayException, IOException;


}
