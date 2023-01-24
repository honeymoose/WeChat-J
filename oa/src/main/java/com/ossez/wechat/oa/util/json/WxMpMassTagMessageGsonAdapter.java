package com.ossez.wechat.oa.util.json;

import com.google.gson.*;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.bean.WxMpMassTagMessage;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * 群发消息json转换适配器.
 *
 * @author chanjarster
 */
public class WxMpMassTagMessageGsonAdapter implements JsonSerializer<WxMpMassTagMessage> {

  @Override
  public JsonElement serialize(WxMpMassTagMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();

    JsonObject filter = new JsonObject();
    if (message.isSendAll() || null == message.getTagId()) {
      filter.addProperty("is_to_all", true);
    } else {
      filter.addProperty("is_to_all", false);
      filter.addProperty("tag_id", message.getTagId());
    }
    messageJson.add("filter", filter);

    if (WeChatConstant.MassMsgType.MPNEWS.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WeChatConstant.MassMsgType.MPNEWS, sub);
    }
    if (WeChatConstant.MassMsgType.TEXT.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("content", message.getContent());
      messageJson.add(WeChatConstant.MassMsgType.TEXT, sub);
    }
    if (WeChatConstant.MassMsgType.VOICE.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WeChatConstant.MassMsgType.VOICE, sub);
    }
    if (WeChatConstant.MassMsgType.IMAGE.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      List<String> mediaIds = message.getMediaIds();
      if (mediaIds != null && !mediaIds.isEmpty() ) {
        JsonArray json = new JsonArray();
        mediaIds.forEach(json::add);
        sub.add("media_ids", json);
        messageJson.add(WeChatConstant.MassMsgType.IMAGES, sub);
      } else {
        String mediaId = message.getMediaId();
        sub.addProperty("media_id", mediaId);
        messageJson.add(WeChatConstant.MassMsgType.IMAGE, sub);
      }
    }
    if (WeChatConstant.MassMsgType.MPVIDEO.equals(message.getMsgType())) {
      JsonObject sub = new JsonObject();
      sub.addProperty("media_id", message.getMediaId());
      messageJson.add(WeChatConstant.MassMsgType.MPVIDEO, sub);
    }
    messageJson.addProperty("msgtype", message.getMsgType());
    messageJson.addProperty("send_ignore_reprint", message.isSendIgnoreReprint() ? 1 : 0);

    if (StringUtils.isNotEmpty(message.getClientMsgId())) {
      messageJson.addProperty("clientmsgid", message.getClientMsgId());
    }

    return messageJson;
  }

}
