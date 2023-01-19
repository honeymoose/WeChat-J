package com.ossez.wechat.oa.api.impl;

import com.google.inject.Inject;

import com.ossez.wechat.common.api.WxConsts;
import com.ossez.wechat.common.bean.result.WxMediaUploadResult;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.test.ApiTestModule;
import com.ossez.wechat.oa.api.test.TestConfigStorage;
import com.ossez.wechat.oa.api.test.TestConstants;
import com.ossez.wechat.oa.bean.WxMpMassNews;
import com.ossez.wechat.oa.bean.WxMpMassOpenIdsMessage;
import com.ossez.wechat.oa.bean.WxMpMassTagMessage;
import com.ossez.wechat.oa.bean.WxMpMassVideo;
import com.ossez.wechat.oa.bean.result.WxMpMassUploadResult;
import com.ossez.wechat.oa.bean.material.WxMpNewsArticle;
import com.ossez.wechat.oa.bean.result.WxMpMassSendResult;
import org.testng.Assert;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Guice;
import org.testng.annotations.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;

import static org.testng.Assert.assertNotNull;

/**
 * 测试群发消息
 *
 * @author chanjarster
 */
@Test
@Guice(modules = ApiTestModule.class)
public class WxMpMassMessageServiceImplTest {
  @Inject
  protected WeChatOfficialAccountService wxService;

  @Test
  public void testTextMassOpenIdsMessageSend() throws WxErrorException {
    // 发送群发消息
    TestConfigStorage configProvider = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
    massMessage.setMsgType(WxConsts.MassMsgType.TEXT);
    massMessage.setContent("测试群发消息\n欢迎欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
    massMessage.getToUsers().add(configProvider.getOpenid());

    WxMpMassSendResult massResult = this.wxService.getMassMessageService().massOpenIdsMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test(dataProvider = "massMessages")
  public void testMediaMassOpenIdsMessageSend(String massMsgType, String mediaId) throws WxErrorException {
    // 发送群发消息
    TestConfigStorage configProvider = (TestConfigStorage) this.wxService.getWxMpConfigStorage();
    WxMpMassOpenIdsMessage massMessage = new WxMpMassOpenIdsMessage();
    massMessage.setMsgType(massMsgType);
    massMessage.setMediaId(mediaId);
    massMessage.getToUsers().add(configProvider.getOpenid());

    WxMpMassSendResult massResult = this.wxService.getMassMessageService().massOpenIdsMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test
  public void testImagesMassOpenIdsMessageSend() throws WxErrorException {
    // 发送群发消息
    List<String> massMsg = new ArrayList<>();
    for (int i = 0; i < 2; i++) {
      try (InputStream inputStream = ClassLoader
        .getSystemResourceAsStream(i + ".jpeg")) {
        WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService().mediaUpload(WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, inputStream);
        Assert.assertNotNull(uploadMediaRes);
        Assert.assertNotNull(uploadMediaRes.getMediaId());
        massMsg.add(uploadMediaRes.getMediaId());
      } catch (IOException e) {
        e.printStackTrace();
      }
    }
    WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(WxConsts.MassMsgType.IMAGE);
    massMessage.setMediaIds(new ArrayList<>(massMsg));
    massMessage.setSendAll(true);
    WxMpMassSendResult massResult = this.wxService.getMassMessageService().massGroupMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test
  public void testTextMassGroupMessageSend() throws WxErrorException {
    WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(WxConsts.MassMsgType.TEXT);
    massMessage.setContent("测试群发消息\n欢迎欢迎\n换行测试\n超链接:<a href=\"http://www.baidu.com\">Hello World</a>");
    massMessage
      .setTagId(this.wxService.getUserTagService().tagGet().get(0).getId());

    WxMpMassSendResult massResult = this.wxService.getMassMessageService().massGroupMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @Test(dataProvider = "massMessages")
  public void testMediaMassGroupMessageSend(String massMsgType, String mediaId) throws WxErrorException {
    WxMpMassTagMessage massMessage = new WxMpMassTagMessage();
    massMessage.setMsgType(massMsgType);
    massMessage.setMediaId(mediaId);
    massMessage.setTagId(this.wxService.getUserTagService().tagGet().get(0).getId());

    WxMpMassSendResult massResult = this.wxService.getMassMessageService().massGroupMessageSend(massMessage);
    Assert.assertNotNull(massResult);
    Assert.assertNotNull(massResult.getMsgId());
  }

  @DataProvider
  public Object[][] massMessages() throws WxErrorException, IOException {
    Object[][] messages = new Object[4][];

    /*
     * 视频素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.mp4")) {
      // 上传视频到媒体库
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MediaFileType.VIDEO, TestConstants.FILE_MP4, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());

      // 把视频变成可被群发的媒体
      WxMpMassVideo video = new WxMpMassVideo();
      video.setTitle("测试标题");
      video.setDescription("测试描述");
      video.setMediaId(uploadMediaRes.getMediaId());
      WxMpMassUploadResult uploadResult = this.wxService.getMassMessageService().massVideoUpload(video);
      Assert.assertNotNull(uploadResult);
      Assert.assertNotNull(uploadResult.getMediaId());
      messages[0] = new Object[]{WxConsts.MassMsgType.MPVIDEO, uploadResult.getMediaId()};
    }

    /*
     * 图片素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.jpeg")) {
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[1] = new Object[]{WxConsts.MassMsgType.IMAGE, uploadMediaRes.getMediaId()
      };
    }

    /*
     * 语音素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.mp3")) {
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MediaFileType.VOICE, TestConstants.FILE_MP3, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[2] = new Object[]{WxConsts.MassMsgType.VOICE, uploadMediaRes.getMediaId()};
    }

    /*
     * 图文素材
     */
    try (InputStream inputStream = ClassLoader
      .getSystemResourceAsStream("mm.jpeg")) {
      // 上传照片到媒体库
      WxMediaUploadResult uploadMediaRes = this.wxService.getMaterialService()
        .mediaUpload(WxConsts.MediaFileType.IMAGE, TestConstants.FILE_JPG, inputStream);
      Assert.assertNotNull(uploadMediaRes);
      Assert.assertNotNull(uploadMediaRes.getMediaId());

      // 上传图文消息
      WxMpMassNews news = new WxMpMassNews();
      WxMpNewsArticle article1 = new WxMpNewsArticle();
      article1.setTitle("标题1");
      article1.setContent("内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1内容1");
      article1.setThumbMediaId(uploadMediaRes.getMediaId());
      news.addArticle(article1);

      WxMpNewsArticle article2 = new WxMpNewsArticle();
      article2.setTitle("标题2");
      article2.setContent("内容2内容2内容2内容2内容2内容2内容2内容2内2内容2内容2内容2内容2内容2内容2内容2内容2内容2");
      article2.setThumbMediaId(uploadMediaRes.getMediaId());
      article2.setShowCoverPic(true);
      article2.setAuthor("作者2");
      article2.setContentSourceUrl("www.baidu.com");
      article2.setDigest("摘要2");
      news.addArticle(article2);

      WxMpMassUploadResult massUploadResult = this.wxService.getMassMessageService()
        .massNewsUpload(news);
      Assert.assertNotNull(massUploadResult);
      Assert.assertNotNull(uploadMediaRes.getMediaId());
      messages[3] = new Object[]{WxConsts.MassMsgType.MPNEWS, massUploadResult.getMediaId()};
    }

    return messages;
  }

  @Test
  public void testMassDelete() throws Exception {
    this.wxService.getMassMessageService().delete(1L, 2);
  }

}
