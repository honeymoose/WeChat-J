package com.ossez.wechat.wecom.bean.message;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.ossez.wechat.wecom.constant.WxCpConsts;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import com.ossez.wechat.wecom.bean.article.NewArticle;

import java.io.Serializable;
import java.util.List;

/**
 * 微信群机器人消息
 *
 * @author yr  created on  2020-08-20
 */
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
@Data
public class WxCpGroupRobotMessage implements Serializable {
  private static final long serialVersionUID = -4301684507150486556L;

  /**
   * 消息类型
   */
  private String msgType;

  /**
   * 文本内容，最长不超过2048个字节，markdown内容，最长不超过4096个字节，必须是utf8编码
   * 必填
   */
  private String content;
  /**
   * userid的列表，提醒群中的指定成员(@某个成员)，@all表示提醒所有人，如果开发者获取不到userid，可以使用mentioned_mobile_list
   */
  private List<String> mentionedList;
  /**
   * 手机号列表，提醒手机号对应的群成员(@某个成员)，@all表示提醒所有人
   */
  private List<String> mentionedMobileList;
  /**
   * 图片内容的base64编码
   */
  private String base64;
  /**
   * 图片内容（base64编码前）的md5值
   */
  private String md5;
  /**
   * 图文消息，一个图文消息支持1到8条图文
   */
  private List<NewArticle> articles;

  /**
   * 文件id
   */
  private String mediaId;

  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    JsonObject messageJson = new JsonObject();
    messageJson.addProperty("msgtype", this.getMsgType());

    switch (this.getMsgType()) {
      case WxCpConsts.GroupRobotMsgType.TEXT: {
        JsonObject text = new JsonObject();
        JsonArray uidJsonArray = new JsonArray();
        JsonArray mobileJsonArray = new JsonArray();

        text.addProperty("content", this.getContent());

        if (this.getMentionedList() != null) {
          for (String item : this.getMentionedList()) {
            uidJsonArray.add(item);
          }
        }
        if (this.getMentionedMobileList() != null) {
          for (String item : this.getMentionedMobileList()) {
            mobileJsonArray.add(item);
          }
        }
        text.add("mentioned_list", uidJsonArray);
        text.add("mentioned_mobile_list", mobileJsonArray);
        messageJson.add("text", text);
        break;
      }
      case WxCpConsts.GroupRobotMsgType.MARKDOWN: {
        JsonObject text = new JsonObject();
        text.addProperty("content", this.getContent());
        messageJson.add("markdown", text);
        break;
      }
      case WxCpConsts.GroupRobotMsgType.IMAGE: {
        JsonObject text = new JsonObject();
        text.addProperty("base64", this.getBase64());
        text.addProperty("md5", this.getMd5());
        messageJson.add("image", text);
        break;
      }
      case WxCpConsts.GroupRobotMsgType.NEWS: {
        JsonObject text = new JsonObject();
        JsonArray array = new JsonArray();
        for (NewArticle article : this.getArticles()) {
          JsonObject articleJson = new JsonObject();
          articleJson.addProperty("title", article.getTitle());
          articleJson.addProperty("description", article.getDescription());
          articleJson.addProperty("url", article.getUrl());
          articleJson.addProperty("picurl", article.getPicUrl());
          array.add(articleJson);
        }
        text.add("articles", array);
        messageJson.add("news", text);
        break;
      }
      case WxCpConsts.GroupRobotMsgType.FILE: {
        JsonObject file = new JsonObject();
        file.addProperty("media_id", this.getMediaId());
        messageJson.add("file", file);
        break;
      }
      default:

    }

    return messageJson.toString();
  }
}
