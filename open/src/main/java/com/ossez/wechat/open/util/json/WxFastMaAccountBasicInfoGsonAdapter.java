package com.ossez.wechat.open.util.json;

import com.google.gson.*;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.open.bean.result.WxFastMaAccountBasicInfoResult;

import java.lang.reflect.Type;

/**
 * .
 *
 * @author Hipple
 * @since 2019/1/23 15:02
 */
public class WxFastMaAccountBasicInfoGsonAdapter implements JsonDeserializer<WxFastMaAccountBasicInfoResult> {
  @Override
  public WxFastMaAccountBasicInfoResult deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext context)
    throws JsonParseException {
    WxFastMaAccountBasicInfoResult accountBasicInfo = new WxFastMaAccountBasicInfoResult();
    JsonObject jsonObject = jsonElement.getAsJsonObject();

    accountBasicInfo.setAppId(GsonHelper.getString(jsonObject, "appid"));
    accountBasicInfo.setAccountType(GsonHelper.getInteger(jsonObject, "account_type"));
    accountBasicInfo.setPrincipalType(GsonHelper.getInteger(jsonObject, "principal_type"));
    accountBasicInfo.setPrincipalName(GsonHelper.getString(jsonObject, "principal_name"));
    accountBasicInfo.setRealnameStatus(GsonHelper.getInteger(jsonObject, "realname_status"));
    accountBasicInfo.setNickname(GsonHelper.getString(jsonObject, "nickname"));

    WxFastMaAccountBasicInfoResult.NicknameInfo nicknameInfo = WxOpenGsonBuilder.create()
      .fromJson(jsonObject.get("nickname_info"),
        new TypeToken<WxFastMaAccountBasicInfoResult.NicknameInfo>() {
        }.getType());
    accountBasicInfo.setNicknameInfo(nicknameInfo);

    WxFastMaAccountBasicInfoResult.WxVerifyInfo verifyInfo = WxOpenGsonBuilder.create()
      .fromJson(jsonObject.get("wx_verify_info"),
        new TypeToken<WxFastMaAccountBasicInfoResult.WxVerifyInfo>() {
        }.getType());
    accountBasicInfo.setWxVerifyInfo(verifyInfo);

    WxFastMaAccountBasicInfoResult.SignatureInfo signatureInfo = WxOpenGsonBuilder.create()
      .fromJson(jsonObject.get("signature_info"),
        new TypeToken<WxFastMaAccountBasicInfoResult.SignatureInfo>() {
        }.getType());
    accountBasicInfo.setSignatureInfo(signatureInfo);

    WxFastMaAccountBasicInfoResult.HeadImageInfo headImageInfo = WxOpenGsonBuilder.create()
      .fromJson(jsonObject.get("head_image_info"),
        new TypeToken<WxFastMaAccountBasicInfoResult.HeadImageInfo>() {
        }.getType());
    accountBasicInfo.setHeadImageInfo(headImageInfo);

    return accountBasicInfo;
  }
}
