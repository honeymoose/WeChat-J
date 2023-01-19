package com.ossez.wechat.oa.api.impl;

import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.oa.bean.guide.WxMpGuideCardMaterialInfo;
import com.ossez.wechat.oa.bean.guide.WxMpGuideImgMaterialInfoList;
import com.ossez.wechat.oa.bean.guide.WxMpGuideWordMaterialInfoList;
import lombok.AllArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.api.WxMpGuideMaterialService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/13/013
 */
@AllArgsConstructor
public class WxMpGuideMaterialServiceImpl implements WxMpGuideMaterialService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WeChatOfficialAccountService mpService;

  @Override
  public void setGuideCardMaterial(String mediaId, int type, String title, String path, String appId) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("type", type);
    body.put("media_id", mediaId);
    body.put("title", title);
    body.put("path", path);
    body.put("appid", appId);
    this.mpService.post(WxMpApiUrl.Guide.SET_GUIDE_CARD_MATERIAL, body);
  }

  @Override
  public List<WxMpGuideCardMaterialInfo> getGuideCardMaterial(int type) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_CARD_MATERIAL, GsonHelper.buildJsonObject("type", type));
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("card_list"),
      new TypeToken<List<WxMpGuideCardMaterialInfo>>() {
      }.getType());
  }

  @Override
  public void delGuideCardMaterial(int type, String title, String path, String appId) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("type", type);
    body.put("title", title);
    body.put("path", path);
    body.put("appid", appId);
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_CARD_MATERIAL, body);
  }

  @Override
  public void setGuideImageMaterial(String mediaId, int type) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.SET_GUIDE_IMAGE_MATERIAL,
      GsonHelper.buildJsonObject("media_id", mediaId, "type", type));
  }

  @Override
  public WxMpGuideImgMaterialInfoList getGuideImageMaterial(int type, int start, int num) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_IMAGE_MATERIAL,
      GsonHelper.buildJsonObject("type", type, "start", start, "num", num));
    return WxMpGuideImgMaterialInfoList.fromJson(returnString);
  }

  @Override
  public void delGuideImageMaterial(int type, String picUrl) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_IMAGE_MATERIAL,
      GsonHelper.buildJsonObject("type", type, "picurl", picUrl));
  }

  @Override
  public void setGuideWordMaterial(int type, String word) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.SET_GUIDE_WORD_MATERIAL,
      GsonHelper.buildJsonObject("type", type, "word", word));
  }

  @Override
  public WxMpGuideWordMaterialInfoList getGuideWordMaterial(int type, int start, int num) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_WORD_MATERIAL,
      GsonHelper.buildJsonObject("type", type, "start", start, "num", num));
    return WxMpGuideWordMaterialInfoList.fromJson(returnString);
  }

  @Override
  public void delGuideWordMaterial(int type, String word) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.DEL_GUIDE_WORD_MATERIAL,
      GsonHelper.buildJsonObject("type", type, "word", word));
  }
}
