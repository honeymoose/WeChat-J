package com.ossez.wechat.oa.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.oa.bean.material.WxMpMaterialVideoInfoResult;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxMpMaterialVideoInfoResultAdapter implements JsonDeserializer<WxMpMaterialVideoInfoResult> {

  @Override
  public WxMpMaterialVideoInfoResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMaterialVideoInfoResult uploadResult = new WxMpMaterialVideoInfoResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("title") != null && !uploadResultJsonObject.get("title").isJsonNull()) {
      uploadResult.setTitle(GsonHelper.getAsString(uploadResultJsonObject.get("title")));
    }
    if (uploadResultJsonObject.get("description") != null && !uploadResultJsonObject.get("description").isJsonNull()) {
      uploadResult.setDescription(GsonHelper.getAsString(uploadResultJsonObject.get("description")));
    }
    if (uploadResultJsonObject.get("down_url") != null && !uploadResultJsonObject.get("down_url").isJsonNull()) {
      uploadResult.setDownUrl(GsonHelper.getAsString(uploadResultJsonObject.get("down_url")));
    }
    return uploadResult;
  }

}
