package com.ossez.wechat.open.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.open.bean.WxOpenAuthorizerAccessToken;

import java.lang.reflect.Type;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenAuthorizerAccessTokenGsonAdapter implements JsonDeserializer<WxOpenAuthorizerAccessToken> {
  @Override
  public WxOpenAuthorizerAccessToken deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
    WxOpenAuthorizerAccessToken authorizerAccessToken = new WxOpenAuthorizerAccessToken();
    JsonObject jsonObject = jsonElement.getAsJsonObject();
    authorizerAccessToken.setAuthorizerAccessToken(GsonHelper.getString(jsonObject, "authorizer_access_token"));
    authorizerAccessToken.setAuthorizerRefreshToken(GsonHelper.getString(jsonObject, "authorizer_refresh_token"));
    authorizerAccessToken.setExpiresIn(GsonHelper.getPrimitiveInteger(jsonObject, "expires_in"));
    return authorizerAccessToken;
  }
}
