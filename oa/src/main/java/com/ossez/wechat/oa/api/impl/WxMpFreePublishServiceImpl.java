package com.ossez.wechat.oa.api.impl;

import lombok.AllArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpFreePublishService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.freepublish.WxMpFreePublishInfo;
import com.ossez.wechat.oa.bean.freepublish.WxMpFreePublishList;
import com.ossez.wechat.oa.bean.freepublish.WxMpFreePublishStatus;
import com.ossez.wechat.common.enums.WxMpApiUrl;

/**
 * 发布能力-service实现类.
 *
 * @author dragon
 * created on  2021-10-23
 */
@AllArgsConstructor
public class WxMpFreePublishServiceImpl implements WxMpFreePublishService {

  private static final String MEDIA_ID = "media_id";
  private static final String PUBLISH_ID = "publish_id";
  private static final String ARTICLE_ID = "article_id";
  private static final String ERRCODE_SUCCESS = "0";
  private static final String ERRCODE = "errcode";
  private final WeChatOfficialAccountService mpService;

  @Override
  public String submit(String mediaId) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.FreePublish.SUBMIT,
      GsonHelper.buildJsonObject(MEDIA_ID, mediaId));
    return GsonParser.parse(json).get(PUBLISH_ID).toString();
  }

  @Override
  public WxMpFreePublishStatus getPushStatus(String publishId) throws WxErrorException {
    return WxMpFreePublishStatus.fromJson(this.mpService.post(WxMpApiUrl.FreePublish.GET_PUSH_STATUS,
      GsonHelper.buildJsonObject(PUBLISH_ID, publishId)));
  }

  @Override
  public Boolean deletePush(String articleId, Integer index) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.FreePublish.DEL_PUSH,
      GsonHelper.buildJsonObject(ARTICLE_ID, articleId, "index", index));
    return GsonParser.parse(json).get(ERRCODE).toString().equals(ERRCODE_SUCCESS);
  }

  @Override
  public Boolean deletePushAllArticle(String articleId) throws WxErrorException {
    // index字段不填或填0会删除全部文章
    return deletePush(articleId, 0);
  }

  @Override
  public WxMpFreePublishInfo getArticleFromId(String articleId) throws WxErrorException {
    return WxMpFreePublishInfo.fromJson(this.mpService.post(WxMpApiUrl.FreePublish.GET_ARTICLE,
      GsonHelper.buildJsonObject(ARTICLE_ID, articleId)));
  }

  @Override
  public WxMpFreePublishList getPublicationRecords(int offset, int count, int noContent) throws WxErrorException {
    return WxMpFreePublishList.fromJson(this.mpService.post(WxMpApiUrl.FreePublish.BATCH_GET,
      GsonHelper.buildJsonObject("offset", offset, "count", count, "no_content", noContent)));
  }

  @Override
  public WxMpFreePublishList getPublicationRecords(int offset, int count) throws WxErrorException {
    return getPublicationRecords(offset, count, 0);
  }
}
