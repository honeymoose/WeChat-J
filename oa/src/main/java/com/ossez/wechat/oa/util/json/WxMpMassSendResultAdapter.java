package com.ossez.wechat.oa.util.json;

import com.google.gson.*;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.oa.bean.result.WxMpMassSendResult;

import java.lang.reflect.Type;

/**
 * @author Daniel Qian
 */
public class WxMpMassSendResultAdapter implements JsonDeserializer<WxMpMassSendResult> {

  @Override
  public WxMpMassSendResult deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
    WxMpMassSendResult sendResult = new WxMpMassSendResult();
    JsonObject sendResultJsonObject = json.getAsJsonObject();

    if (sendResultJsonObject.get("errcode") != null && !sendResultJsonObject.get("errcode").isJsonNull()) {
      sendResult.setErrorCode(GsonHelper.getAsString(sendResultJsonObject.get("errcode")));
    }
    if (sendResultJsonObject.get("errmsg") != null && !sendResultJsonObject.get("errmsg").isJsonNull()) {
      sendResult.setErrorMsg(GsonHelper.getAsString(sendResultJsonObject.get("errmsg")));
    }
    if (sendResultJsonObject.get("msg_id") != null && !sendResultJsonObject.get("msg_id").isJsonNull()) {
      sendResult.setMsgId(GsonHelper.getAsString(sendResultJsonObject.get("msg_id")));
    }
    if (sendResultJsonObject.get("msg_data_id") != null && !sendResultJsonObject.get("msg_data_id").isJsonNull()) {
      sendResult.setMsgDataId(GsonHelper.getAsString(sendResultJsonObject.get("msg_data_id")));
    }
    return sendResult;
  }

}
