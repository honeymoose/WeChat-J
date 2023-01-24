package com.ossez.wechat.oa.util.json;

import com.google.gson.*;

import com.ossez.wechat.common.constant.WeChatConstant;
import com.ossez.wechat.oa.bean.WxMpMassOpenIdsMessage;
import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Type;
import java.util.List;

/**
 * @author someone
 */
public class WxMpMassOpenIdsMessageGsonAdapter implements JsonSerializer<WxMpMassOpenIdsMessage> {

  @Override
  public JsonElement serialize(WxMpMassOpenIdsMessage message, Type typeOfSrc, JsonSerializationContext context) {
    JsonObject messageJson = new JsonObject();

    JsonArray toUsers = new JsonArray();
    for (String openId : message.getToUsers()) {
      toUsers.add(new JsonPrimitive(openId));
    }
    messageJson.add("touser", toUsers);

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

    /*
    开发者可以对群发接口的 send_ignore_reprint 参数进行设置，指定待群发的文章被判定为转载时，是否继续群发。
    当 send_ignore_reprint 参数设置为1时，文章被判定为转载时，且原创文允许转载时，将继续进行群发操作。
    当 send_ignore_reprint 参数设置为0时，文章被判定为转载时，将停止群发操作。
    send_ignore_reprint 默认为0。
     */
    messageJson.addProperty("send_ignore_reprint", message.isSendIgnoreReprint() ? 1 : 0);

    if (StringUtils.isNotEmpty(message.getClientMsgId())) {
      messageJson.addProperty("clientmsgid", message.getClientMsgId());
    }

    return messageJson;
  }

  public static void main(String[] args) {

  }
}
