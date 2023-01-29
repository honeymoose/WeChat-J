package com.ossez.wechat.oa.api.impl;

import lombok.AllArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpDraftService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.draft.WxMpAddDraft;
import com.ossez.wechat.oa.bean.draft.WxMpDraftArticles;
import com.ossez.wechat.oa.bean.draft.WxMpDraftInfo;
import com.ossez.wechat.oa.bean.draft.WxMpDraftList;
import com.ossez.wechat.oa.bean.draft.WxMpUpdateDraft;
import com.ossez.wechat.common.enums.WxMpApiUrl;

import java.util.ArrayList;
import java.util.List;

/**
 * 草稿箱能力-service实现类.
 *
 * @author dragon
 * created on  2021-10-22
 */
@AllArgsConstructor
public class WxMpDraftServiceImpl implements WxMpDraftService {

  private static final String MEDIA_ID = "media_id";
  private static final String ERRCODE_SUCCESS = "0";
  private static final String ERRCODE = "errcode";
  private final WeChatOfficialAccountService mpService;

  @Override
  public String addDraft(String title, String content, String thumbMediaId) throws WxErrorException {
    List<WxMpDraftArticles> draftArticleList = new ArrayList<>();
    WxMpDraftArticles draftArticle = WxMpDraftArticles.builder()
      .title(title).content(content).thumbMediaId(thumbMediaId).build();
    WxMpAddDraft addDraft = WxMpAddDraft.builder().articles(draftArticleList).build();
    draftArticleList.add(draftArticle);
    return addDraft(addDraft);
  }

  @Override
  public String addDraft(WxMpAddDraft addDraft) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.ADD_DRAFT, addDraft);
    return GsonParser.parse(json).get(MEDIA_ID).getAsString();
  }

  @Override
  public Boolean updateDraft(WxMpUpdateDraft updateDraftInfo) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.UPDATE_DRAFT, updateDraftInfo);
    return GsonParser.parse(json).get(ERRCODE).getAsString().equals(ERRCODE_SUCCESS);
  }

  @Override
  public WxMpDraftInfo getDraft(String mediaId) throws WxErrorException {
    return WxMpDraftInfo.fromJson(this.mpService.post(WxMpApiUrl.Draft.GET_DRAFT,
      GsonHelper.buildJsonObject(MEDIA_ID, mediaId)));
  }

  @Override
  public Boolean delDraft(String mediaId) throws WxErrorException {
    String json = this.mpService.post(WxMpApiUrl.Draft.DEL_DRAFT,
      GsonHelper.buildJsonObject(MEDIA_ID, mediaId));
    return GsonParser.parse(json).get(ERRCODE).getAsString().equals(ERRCODE_SUCCESS);
  }

  @Override
  public WxMpDraftList listDraft(int offset, int count, int noContent) throws WxErrorException {
    return WxMpDraftList.fromJson(this.mpService.post(WxMpApiUrl.Draft.LIST_DRAFT,
      GsonHelper.buildJsonObject("offset", offset, "count", count, "no_content", noContent)));
  }

  @Override
  public WxMpDraftList listDraft(int offset, int count) throws WxErrorException {
    return listDraft(offset, count, 0);
  }

  @Override
  public Long countDraft() throws WxErrorException {
    String json = this.mpService.get(WxMpApiUrl.Draft.COUNT_DRAFT, null);
    return Long.valueOf(GsonParser.parse(json).get("total_count").toString());
  }
}
