package com.ossez.wechat.oa.bean.kefu;

import com.ossez.wechat.common.constant.WeChatConstant;
import org.testng.Assert;
import org.testng.annotations.Test;

@Test
public class WxMpKefuMessageTest {

  public void testTextReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.TEXT);
    reply.setContent("sfsfdsdf");
    Assert
      .assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testTextBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.TEXT().toUser("OPENID").content("sfsfdsdf").build();
    Assert
      .assertEquals(reply.toJson(), "{\"touser\":\"OPENID\",\"msgtype\":\"text\",\"text\":{\"content\":\"sfsfdsdf\"}}");
  }

  public void testImageReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.IMAGE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testImageBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.IMAGE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"image\",\"image\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.VOICE);
    reply.setMediaId("MEDIA_ID");
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVoiceBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.VOICE().toUser("OPENID").mediaId("MEDIA_ID").build();
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"voice\",\"voice\":{\"media_id\":\"MEDIA_ID\"}}");
  }

  public void testVideoReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.VIDEO);
    reply.setMediaId("MEDIA_ID");
    reply.setThumbMediaId("MEDIA_ID");
    reply.setTitle("TITLE");
    reply.setDescription("DESCRIPTION");
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testVideoBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.VIDEO().toUser("OPENID").title("TITLE").mediaId("MEDIA_ID")
      .thumbMediaId("MEDIA_ID").description("DESCRIPTION").build();
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"video\",\"video\":{\"media_id\":\"MEDIA_ID\",\"thumb_media_id\":\"MEDIA_ID\",\"title\":\"TITLE\",\"description\":\"DESCRIPTION\"}}");
  }

  public void testMusicReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.MUSIC);
    reply.setThumbMediaId("MEDIA_ID");
    reply.setDescription("DESCRIPTION");
    reply.setTitle("TITLE");
    reply.setMusicUrl("MUSIC_URL");
    reply.setHqMusicUrl("HQ_MUSIC_URL");
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"music\",\"music\":{\"title\":\"TITLE\",\"description\":\"DESCRIPTION\",\"thumb_media_id\":\"MEDIA_ID\",\"musicurl\":\"MUSIC_URL\",\"hqmusicurl\":\"HQ_MUSIC_URL\"}}");
  }

  public void testMusicBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.MUSIC()
      .toUser("OPENID")
      .title("TITLE")
      .thumbMediaId("MEDIA_ID")
      .description("DESCRIPTION")
      .musicUrl("MUSIC_URL")
      .hqMusicUrl("HQ_MUSIC_URL")
      .build();
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"music\",\"music\":{\"title\":\"TITLE\",\"description\":\"DESCRIPTION\",\"thumb_media_id\":\"MEDIA_ID\",\"musicurl\":\"MUSIC_URL\",\"hqmusicurl\":\"HQ_MUSIC_URL\"}}");
  }

  public void testNewsReply() {
    WxMpKefuMessage reply = new WxMpKefuMessage();
    reply.setToUser("OPENID");
    reply.setMsgType(WeChatConstant.MsgType.NEWS);

    WxMpKefuMessage.WxArticle article1 = new WxMpKefuMessage.WxArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");
    reply.getArticles().add(article1);

    WxMpKefuMessage.WxArticle article2 = new WxMpKefuMessage.WxArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");
    reply.getArticles().add(article2);

    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

  public void testNewsBuild() {
    WxMpKefuMessage.WxArticle article1 = new WxMpKefuMessage.WxArticle();
    article1.setUrl("URL");
    article1.setPicUrl("PIC_URL");
    article1.setDescription("Is Really A Happy Day");
    article1.setTitle("Happy Day");

    WxMpKefuMessage.WxArticle article2 = new WxMpKefuMessage.WxArticle();
    article2.setUrl("URL");
    article2.setPicUrl("PIC_URL");
    article2.setDescription("Is Really A Happy Day");
    article2.setTitle("Happy Day");

    WxMpKefuMessage reply = WxMpKefuMessage.NEWS().toUser("OPENID").addArticle(article1).addArticle(article2).build();

    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"news\",\"news\":{\"articles\":[{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"},{\"title\":\"Happy Day\",\"description\":\"Is Really A Happy Day\",\"url\":\"URL\",\"picurl\":\"PIC_URL\"}]}}");
  }

  public void testMiniProgramPageBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.MINIPROGRAMPAGE()
      .toUser("OPENID")
      .title("title")
      .appId("appid")
      .pagePath("pagepath")
      .thumbMediaId("thumb_media_id")
      .build();

    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"miniprogrampage\",\"miniprogrampage\":{\"title\":\"title\",\"appid\":\"appid\",\"pagepath\":\"pagepath\",\"thumb_media_id\":\"thumb_media_id\"}}");
  }

  public void testMsgMenuBuild() {
    WxMpKefuMessage reply = WxMpKefuMessage.MSGMENU()
      .toUser("OPENID")
      .addMenus(new WxMpKefuMessage.MsgMenu("101", "msgmenu1"),
        new WxMpKefuMessage.MsgMenu("102", "msgmenu2"))
      .headContent("head_content")
      .tailContent("tail_content")
      .build();

    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"msgmenu\",\"msgmenu\":{\"head_content\":\"head_content\",\"list\":[{\"id\":\"101\",\"content\":\"msgmenu1\"},{\"id\":\"102\",\"content\":\"msgmenu2\"}],\"tail_content\":\"tail_content\"}}");
  }

  public void testMpNewsArticleBuilder() {
    WxMpKefuMessage reply = WxMpKefuMessage.MPNEWSARTICLE()
      .toUser("OPENID")
      .articleId("ARTICLE_ID")
      .build();
    Assert.assertEquals(reply.toJson(),
      "{\"touser\":\"OPENID\",\"msgtype\":\"mpnewsarticle\",\"mpnewsarticle\":{\"article_id\":\"ARTICLE_ID\"}}");
  }

}
