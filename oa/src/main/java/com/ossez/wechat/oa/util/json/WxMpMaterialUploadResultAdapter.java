package com.ossez.wechat.oa.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.oa.bean.material.WxMpMaterialUploadResult;

import java.lang.reflect.Type;

/**
 * @author codepiano
 */
public class WxMpMaterialUploadResultAdapter implements JsonDeserializer<WxMpMaterialUploadResult> {

  @Override
  public WxMpMaterialUploadResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMaterialUploadResult uploadResult = new WxMpMaterialUploadResult();
    JsonObject uploadResultJsonObject = json.getAsJsonObject();

    if (uploadResultJsonObject.get("url") != null && !uploadResultJsonObject.get("url").isJsonNull()) {
      uploadResult.setUrl(GsonHelper.getAsString(uploadResultJsonObject.get("url")));
    }
    if (uploadResultJsonObject.get("media_id") != null && !uploadResultJsonObject.get("media_id").isJsonNull()) {
      uploadResult.setMediaId(GsonHelper.getAsString(uploadResultJsonObject.get("media_id")));
    }
    return uploadResult;
  }

}
