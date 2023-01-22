package com.ossez.wechat.common.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.model.WeChatAccessToken;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxAccessTokenAdapter implements JsonDeserializer<WeChatAccessToken> {

  @Override
  public WeChatAccessToken deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WeChatAccessToken accessToken = new WeChatAccessToken();
    JsonObject accessTokenJsonObject = json.getAsJsonObject();

    if (accessTokenJsonObject.get("access_token") != null && !accessTokenJsonObject.get("access_token").isJsonNull()) {
      accessToken.setAccessToken(GsonHelper.getAsString(accessTokenJsonObject.get("access_token")));
    }
    if (accessTokenJsonObject.get("expires_in") != null && !accessTokenJsonObject.get("expires_in").isJsonNull()) {
      accessToken.setExpiresIn(GsonHelper.getAsPrimitiveInt(accessTokenJsonObject.get("expires_in")));
    }
    return accessToken;
  }

}
