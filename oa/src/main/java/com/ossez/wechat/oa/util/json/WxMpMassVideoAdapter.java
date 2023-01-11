package com.ossez.wechat.oa.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ossez.wechat.oa.bean.WxMpMassVideo;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxMpMassVideoAdapter implements JsonSerializer<WxMpMassVideo> {

  @Override
  public JsonElement serialize(WxMpMassVideo message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("media_id", message.getMediaId());
    messageJson.addProperty("description", message.getDescription());
    messageJson.addProperty("title", message.getTitle());
    return messageJson;
  }

}
