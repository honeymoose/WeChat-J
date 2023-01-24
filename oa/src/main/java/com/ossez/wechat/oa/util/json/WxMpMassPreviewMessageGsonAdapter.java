package com.ossez.wechat.oa.util.json;

import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonSerializationContext;
import com.google.gson.JsonSerializer;
import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.bean.WxMpMassPreviewMessage;

import java.lang.reflect.Type;

/**
 * @author miller
 */
public class WxMpMassPreviewMessageGsonAdapter implements JsonSerializer<WxMpMassPreviewMessage> {
  @Override
  public JsonElement serialize(WxMpMassPreviewMessage wxMpMassPreviewMessage, Type type, JsonSerializationContext jsonSerializationContext) {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("towxname", wxMpMassPreviewMessage.getToWxUserName());
    jsonObject.addProperty("touser", wxMpMassPreviewMessage.getToWxUserOpenid());
    if (WeChatConstant.MassMsgType.MPNEWS.equals(wxMpMassPreviewMessage.getMsgType())) {
      JsonObject news = new JsonObject();
      news.addProperty("media_id", wxMpMassPreviewMessage.getMediaId());
      jsonObject.add(WeChatConstant.MassMsgType.MPNEWS, news);
    }
    if (WeChatConstant.MassMsgType.TEXT.equals(wxMpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("content", wxMpMassPreviewMessage.getContent());
      jsonObject.add(WeChatConstant.MassMsgType.TEXT, sub);
    }
    if (WeChatConstant.MassMsgType.VOICE.equals(wxMpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxMpMassPreviewMessage.getMediaId());
      jsonObject.add(WeChatConstant.MassMsgType.VOICE, sub);
    }
    if (WeChatConstant.MassMsgType.IMAGE.equals(wxMpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxMpMassPreviewMessage.getMediaId());
      jsonObject.add(WeChatConstant.MassMsgType.IMAGE, sub);
    }
    if (WeChatConstant.MassMsgType.MPVIDEO.equals(wxMpMassPreviewMessage.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", wxMpMassPreviewMessage.getMediaId());
      jsonObject.add(WeChatConstant.MassMsgType.MPVIDEO, sub);
    }
    jsonObject.addProperty("msgtype", wxMpMassPreviewMessage.getMsgType());
    return jsonObject;
  }
}
