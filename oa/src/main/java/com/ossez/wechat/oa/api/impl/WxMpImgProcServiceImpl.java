package com.ossez.wechat.oa.api.impl;

import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.service.WxImgProcService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.bean.imgproc.WxImgProcAiCropResult;
import com.ossez.wechat.common.bean.imgproc.WxImgProcQrCodeResult;
import com.ossez.wechat.common.bean.imgproc.WxImgProcSuperResolutionResult;
import com.ossez.wechat.common.requestexecuter.ocr.OcrDiscernRequestExecutor;
import org.apache.commons.lang3.StringUtils;

import java.io.File;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;

import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.AI_CROP;
import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.FILE_AI_CROP;
import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.FILE_QRCODE;
import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.FILE_SUPER_RESOLUTION;
import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.QRCODE;
import static com.ossez.wechat.common.enums.WxMpApiUrl.ImgProc.SUPER_RESOLUTION;

/**
 * 图像处理接口实现.
 * @author Theo Nie
 */
@RequiredArgsConstructor
public class WxMpImgProcServiceImpl implements WxImgProcService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public WxImgProcQrCodeResult qrCode(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    String result = this.weChatOfficialAccountService.get(String.format(QRCODE.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), imgUrl),
      null);
    return WxImgProcQrCodeResult.fromJson(result);
  }

  @Override
  public WxImgProcQrCodeResult qrCode(File imgFile) throws WxErrorException {
    String result = this.weChatOfficialAccountService.execute(OcrDiscernRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      FILE_QRCODE.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), imgFile);
    return WxImgProcQrCodeResult.fromJson(result);
  }

  @Override
  public WxImgProcSuperResolutionResult superResolution(String imgUrl) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    final String result = this.weChatOfficialAccountService.get(String.format(SUPER_RESOLUTION.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), imgUrl), null);
    return WxImgProcSuperResolutionResult.fromJson(result);
  }

  @Override
  public WxImgProcSuperResolutionResult superResolution(File imgFile) throws WxErrorException {
    String result = this.weChatOfficialAccountService.execute(OcrDiscernRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      FILE_SUPER_RESOLUTION.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), imgFile);
    return WxImgProcSuperResolutionResult.fromJson(result);
  }

  @Override
  public WxImgProcAiCropResult aiCrop(String imgUrl) throws WxErrorException {
    return this.aiCrop(imgUrl, "");
  }

  @Override
  public WxImgProcAiCropResult aiCrop(String imgUrl, String ratios) throws WxErrorException {
    try {
      imgUrl = URLEncoder.encode(imgUrl, StandardCharsets.UTF_8.name());
    } catch (UnsupportedEncodingException e) {
      //ignore
    }

    if (StringUtils.isEmpty(ratios)) {
      ratios = "";
    }

    final String result = this.weChatOfficialAccountService.get(String.format(AI_CROP.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()),
      imgUrl, ratios), null);
    return WxImgProcAiCropResult.fromJson(result);
  }

  @Override
  public WxImgProcAiCropResult aiCrop(File imgFile) throws WxErrorException {
    return this.aiCrop(imgFile, "");
  }

  @Override
  public WxImgProcAiCropResult aiCrop(File imgFile, String ratios) throws WxErrorException {
    if (StringUtils.isEmpty(ratios)) {
      ratios = "";
    }

    String result = this.weChatOfficialAccountService.execute(OcrDiscernRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      String.format(FILE_AI_CROP.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), ratios), imgFile);
    return WxImgProcAiCropResult.fromJson(result);
  }
}
