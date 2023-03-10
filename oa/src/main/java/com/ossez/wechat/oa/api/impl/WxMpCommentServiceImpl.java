package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WxMpCommentService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.comment.WxMpCommentListVo;

import static com.ossez.wechat.common.enums.WxMpApiUrl.Comment.*;

/**
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 * created on  2019-06-16
 */
@RequiredArgsConstructor
public class WxMpCommentServiceImpl implements WxMpCommentService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public void open(String msgDataId, Integer index) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msg_data_id", msgDataId);
    if (index != null) {
      json.addProperty("index", index);
    }

    this.weChatOfficialAccountService.post(OPEN, json.toString());
  }

  @Override
  public void close(String msgDataId, Integer index) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msg_data_id", msgDataId);
    if (index != null) {
      json.addProperty("index", index);
    }

    this.weChatOfficialAccountService.post(CLOSE, json.toString());
  }

  @Override
  public WxMpCommentListVo list(String msgDataId, Integer index, int begin, int count, int type) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("msg_data_id", msgDataId);
    json.addProperty("begin", begin);
    json.addProperty("count", count);
    json.addProperty("type", type);

    if (index != null) {
      json.addProperty("index", index);
    }

    return WxMpCommentListVo.fromJson(this.weChatOfficialAccountService.post(LIST, json.toString()));
  }

  @Override
  public void markElect(String msgDataId, Integer index, Long userCommentId) throws WxErrorException {
    JsonObject json = this.buildJson(msgDataId, index, userCommentId);
    this.weChatOfficialAccountService.post(MARK_ELECT, json.toString());
  }

  @Override
  public void unmarkElect(String msgDataId, Integer index, Long userCommentId) throws WxErrorException {
    JsonObject json = this.buildJson(msgDataId, index, userCommentId);
    this.weChatOfficialAccountService.post(UNMARK_ELECT, json.toString());
  }

  @Override
  public void delete(String msgDataId, Integer index, Long userCommentId) throws WxErrorException {
    JsonObject json = this.buildJson(msgDataId, index, userCommentId);

    this.weChatOfficialAccountService.post(DELETE, json.toString());
  }

  @Override
  public void replyAdd(String msgDataId, Integer index, Long userCommentId, String content) throws WxErrorException {
    JsonObject json = this.buildJson(msgDataId, index, userCommentId);
    json.addProperty("content", content);

    this.weChatOfficialAccountService.post(REPLY_ADD, json.toString());
  }

  @Override
  public void replyDelete(String msgDataId, Integer index, Long userCommentId) throws WxErrorException {
    JsonObject json = this.buildJson(msgDataId, index, userCommentId);
    this.weChatOfficialAccountService.post(REPLY_DELETE, json.toString());
  }

  private JsonObject buildJson(String msgDataId, Integer index, Long userCommentId) {
    JsonObject json = new JsonObject();
    json.addProperty("msg_data_id", msgDataId);
    json.addProperty("user_comment_id", userCommentId);
    if (index != null) {
      json.addProperty("index", index);
    }
    return json;
  }
}
