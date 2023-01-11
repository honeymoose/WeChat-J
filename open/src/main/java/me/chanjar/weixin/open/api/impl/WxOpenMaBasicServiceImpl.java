package me.chanjar.weixin.open.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import me.chanjar.weixin.common.error.WxErrorException;
import me.chanjar.weixin.open.api.WxOpenMaBasicService;
import me.chanjar.weixin.open.bean.ma.WxFastMaCategory;
import me.chanjar.weixin.open.bean.result.*;
import me.chanjar.weixin.open.util.json.WxOpenGsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 微信第三方平台 小程序基础信息接口
 *
 * @author <a href="https://www.sacoc.cn">广州跨界</a>
 */
public class WxOpenMaBasicServiceImpl implements WxOpenMaBasicService {

  private final WxMaService wxMaService;

  public WxOpenMaBasicServiceImpl(WxMaService wxMaService) {
    this.wxMaService = wxMaService;
  }


  @Override
  public WxFastMaAccountBasicInfoResult getAccountBasicInfo() throws WxErrorException {
    String response = wxMaService.get(OPEN_GET_ACCOUNT_BASIC_INFO, "");
    return WxOpenGsonBuilder.create().fromJson(response, WxFastMaAccountBasicInfoResult.class);
  }

  @Override
  public WxFastMaSetNickameResult setNickname(String nickname, String idCard, String license, String namingOtherStuff1, String namingOtherStuff2) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("nick_name", nickname);
    params.addProperty("id_card", idCard);
    params.addProperty("license", license);
    params.addProperty("naming_other_stuff_1", namingOtherStuff1);
    params.addProperty("naming_other_stuff_2", namingOtherStuff2);
    String response = wxMaService.post(OPEN_SET_NICKNAME, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxFastMaSetNickameResult.class);
  }

  @Override
  public WxFastMaQueryNicknameStatusResult querySetNicknameStatus(String auditId) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("audit_id", auditId);
    String response = wxMaService.post(OPEN_API_WXA_QUERYNICKNAME, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxFastMaQueryNicknameStatusResult.class);
  }

  @Override
  public WxFastMaCheckNickameResult checkWxVerifyNickname(String nickname) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("nick_name", nickname);
    String response = wxMaService.post(OPEN_CHECK_WX_VERIFY_NICKNAME, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxFastMaCheckNickameResult.class);
  }

  @Override
  public WxOpenResult modifyHeadImage(String headImgMediaId, float x1, float y1, float x2, float y2) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("head_img_media_id", headImgMediaId);
    params.addProperty("x1", x1);
    params.addProperty("y1", y1);
    params.addProperty("x2", x2);
    params.addProperty("y2", y2);
    String response = wxMaService.post(OPEN_MODIFY_HEADIMAGE, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult modifySignature(String signature) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("signature", signature);
    String response = wxMaService.post(OPEN_MODIFY_SIGNATURE, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult componentRebindAdmin(String taskid) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("taskid", taskid);
    String response = wxMaService.post(OPEN_COMPONENT_REBIND_ADMIN, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public String getAllCategories() throws WxErrorException {
    return wxMaService.get(OPEN_GET_ALL_CATEGORIES, "");
  }

  @Override
  public WxOpenResult addCategory(List<WxFastMaCategory> categoryList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>();
    map.put("categories", categoryList);
    String response = wxMaService.post(OPEN_ADD_CATEGORY, WxOpenGsonBuilder.create().toJson(map));
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxOpenResult deleteCategory(int first, int second) throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("first", first);
    params.addProperty("second", second);
    String response = wxMaService.post(OPEN_DELETE_CATEGORY, params);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  @Override
  public WxFastMaBeenSetCategoryResult getCategory() throws WxErrorException {
    String response = wxMaService.get(OPEN_GET_CATEGORY, "");
    return WxOpenGsonBuilder.create().fromJson(response, WxFastMaBeenSetCategoryResult.class);
  }

  @Override
  public WxOpenResult modifyCategory(WxFastMaCategory category) throws WxErrorException {
    String response = wxMaService.post(OPEN_MODIFY_CATEGORY, category);
    return WxOpenGsonBuilder.create().fromJson(response, WxOpenResult.class);
  }

  private JsonArray toJsonArray(List<String> strList) {
    JsonArray jsonArray = new JsonArray();
    if (strList != null && !strList.isEmpty()) {
      for (String str : strList) {
        jsonArray.add(str);
      }
    }
    return jsonArray;
  }
}
