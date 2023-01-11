package com.ossez.wechat.oa.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.oa.bean.material.WxMediaImgUploadResult;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxMediaImgUploadResultGsonAdapter implements JsonDeserializer<WxMediaImgUploadResult> {
  @Override
  public WxMediaImgUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMediaImgUploadResult wxMediaImgUploadResult = new WxMediaImgUploadResult();
    JsonObject jsonObject = json.getAsJsonObject();
    if (null != jsonObject.get("url") && !jsonObject.get("url").isJsonNull()) {
      wxMediaImgUploadResult.setUrl(GsonHelper.getAsString(jsonObject.get("url")));
    }
    return wxMediaImgUploadResult;
  }
}
