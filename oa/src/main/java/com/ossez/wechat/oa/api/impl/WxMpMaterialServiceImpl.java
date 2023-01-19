package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.oa.bean.material.*;
import com.ossez.wechat.oa.util.requestexecuter.material.*;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.api.WxConsts;
import com.ossez.wechat.common.bean.result.WxMediaUploadResult;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.fs.FileUtils;
import com.ossez.wechat.common.util.http.BaseMediaDownloadRequestExecutor;
import com.ossez.wechat.common.util.http.MediaUploadRequestExecutor;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.api.WxMpMaterialService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;
import com.ossez.wechat.oa.util.requestexecuter.media.MediaImgUploadRequestExecutor;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.Material.*;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author Binary Wang
 */
@RequiredArgsConstructor
public class WxMpMaterialServiceImpl implements WxMpMaterialService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public WxMediaUploadResult mediaUpload(String mediaType, String fileType, InputStream inputStream) throws WxErrorException {
    File tmpFile = null;
    try {
      tmpFile = FileUtils.createTmpFile(inputStream, UUID.randomUUID().toString(), fileType);
      return this.mediaUpload(mediaType, tmpFile);
    } catch (IOException e) {
      throw new WxErrorException(WxError.builder().errorCode(-1).errorMsg(e.getMessage()).build(), e);
    } finally {
      if (tmpFile != null) {
        tmpFile.delete();
      }
    }
  }

  @Override
  public WxMediaUploadResult mediaUpload(String mediaType, File file) throws WxErrorException {
    String url = String.format(MEDIA_UPLOAD_URL.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), mediaType);
    return this.weChatOfficialAccountService.execute(MediaUploadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()), url, file);
  }

  @Override
  public File mediaDownload(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(
      BaseMediaDownloadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp(), this.weChatOfficialAccountService.getWxMpConfigStorage().getTmpDirFile()),
      MEDIA_GET_URL,
      "media_id=" + mediaId);
  }

  @Override
  public File jssdkMediaDownload(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(
      BaseMediaDownloadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp(), this.weChatOfficialAccountService.getWxMpConfigStorage().getTmpDirFile()),
      JSSDK_MEDIA_GET_URL,
      "media_id=" + mediaId);
  }

  @Override
  public WxMediaImgUploadResult mediaImgUpload(File file) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(MediaImgUploadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()), IMG_UPLOAD_URL, file);
  }

  @Override
  public WxMpMaterialUploadResult materialFileUpload(String mediaType, WxMpMaterial material) throws WxErrorException {
    String url = String.format(MATERIAL_ADD_URL.getUrl(this.weChatOfficialAccountService.getWxMpConfigStorage()), mediaType);
    return this.weChatOfficialAccountService.execute(MaterialUploadRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()), url, material);
  }

  @Override
  public WxMpMaterialUploadResult materialNewsUpload(WxMpMaterialNews news) throws WxErrorException {
    if (news == null || news.isEmpty()) {
      throw new IllegalArgumentException("news is empty!");
    }
    String responseContent = this.weChatOfficialAccountService.post(NEWS_ADD_URL, news.toJson());
    return WxMpMaterialUploadResult.fromJson(responseContent);
  }

  @Override
  public InputStream materialImageOrVoiceDownload(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(MaterialVoiceAndImageDownloadRequestExecutor
      .create(this.weChatOfficialAccountService.getRequestHttp(), this.weChatOfficialAccountService.getWxMpConfigStorage().getTmpDirFile()),
      MATERIAL_GET_URL, mediaId);
  }

  @Override
  public WxMpMaterialVideoInfoResult materialVideoInfo(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(MaterialVideoInfoRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      MATERIAL_GET_URL, mediaId);
  }

  @Override
  public WxMpMaterialNews materialNewsInfo(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(MaterialNewsInfoRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      MATERIAL_GET_URL, mediaId);
  }

  @Override
  public boolean materialNewsUpdate(WxMpMaterialArticleUpdate wxMpMaterialArticleUpdate) throws WxErrorException {
    String responseText = this.weChatOfficialAccountService.post(NEWS_UPDATE_URL, wxMpMaterialArticleUpdate.toJson());
    WxError wxError = WxError.fromJson(responseText, WxType.MP);
    if (wxError.getErrorCode() == 0) {
      return true;
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public boolean materialDelete(String mediaId) throws WxErrorException {
    return this.weChatOfficialAccountService.execute(MaterialDeleteRequestExecutor.create(this.weChatOfficialAccountService.getRequestHttp()),
      MATERIAL_DEL_URL, mediaId);
  }

  @Override
  public WxMpMaterialCountResult materialCount() throws WxErrorException {
    String responseText = this.weChatOfficialAccountService.get(MATERIAL_GET_COUNT_URL, null);
    WxError wxError = WxError.fromJson(responseText, WxType.MP);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialCountResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialNewsBatchGetResult materialNewsBatchGet(int offset, int count) throws WxErrorException {
    Map<String, Object> params = new HashMap<>(4);
    params.put("type", WxConsts.MaterialType.NEWS);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.weChatOfficialAccountService.post(MATERIAL_BATCHGET_URL, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText, WxType.MP);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialNewsBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpMaterialFileBatchGetResult materialFileBatchGet(String type, int offset, int count) throws WxErrorException {
    Map<String, Object> params = new HashMap<>(4);
    params.put("type", type);
    params.put("offset", offset);
    params.put("count", count);
    String responseText = this.weChatOfficialAccountService.post(MATERIAL_BATCHGET_URL, WxGsonBuilder.create().toJson(params));
    WxError wxError = WxError.fromJson(responseText, WxType.MP);
    if (wxError.getErrorCode() == 0) {
      return WxMpGsonBuilder.create().fromJson(responseText, WxMpMaterialFileBatchGetResult.class);
    } else {
      throw new WxErrorException(wxError);
    }
  }

}
