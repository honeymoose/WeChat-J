package com.ossez.wechat.wecom.tp.service.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonPrimitive;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.wecom.config.WxCpTpConfigStorage;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpTpTag;
import com.ossez.wechat.wecom.bean.WxCpTpTagAddOrRemoveUsersResult;
import com.ossez.wechat.wecom.bean.WxCpTpTagGetResult;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;
import com.ossez.wechat.wecom.tp.service.WxCpTpTagService;

import java.util.List;

/**
 * <pre>
 *   企业微信第三方开发-标签相关接口，部分照搬了WxCpTagServiceImpl
 * </pre>
 *
 * @author zhangq <zhangq002@gmail.com>
 * @since 2021 -02-14 16:02
 */
@RequiredArgsConstructor
public class WxCpTpTagServiceImpl implements WxCpTpTagService {
  private final WxCpTpService mainService;

  @Override
  public String create(String name, Integer id) throws WxErrorException {
    JsonObject o = new JsonObject();
    o.addProperty("tagname", name);

    if (id != null) {
      o.addProperty("tagid", id);
    }
    return this.create(o);
  }

  private String create(JsonObject param) throws WxErrorException {
    String url = getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_CREATE);
    String responseContent = this.mainService.post(url, param.toString());
    JsonObject jsonObject = GsonParser.parse(responseContent);
    return jsonObject.get("tagid").getAsString();
  }

  @Override
  public void update(String tagId, String tagName) throws WxErrorException {
    String url = getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_UPDATE);
    JsonObject o = new JsonObject();
    o.addProperty("tagid", tagId);
    o.addProperty("tagname", tagName);
    this.mainService.post(url, o.toString());
  }

  @Override
  public void delete(String tagId) throws WxErrorException {
    String url = String.format(getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_DELETE), tagId);
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpTpTag> listAll() throws WxErrorException {
    String url = getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_LIST);
    String responseContent = this.mainService.get(url, null);
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create().fromJson(tmpJson.get("taglist"), new TypeToken<List<WxCpTpTag>>() {
      // do nothing
    }.getType());
  }

  @Override
  public WxCpTpTagGetResult get(String tagId) throws WxErrorException {
    if (tagId == null) {
      throw new IllegalArgumentException("缺少tagId参数");
    }

    String url = String.format(getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_GET), tagId);
    String responseContent = this.mainService.get(url, null);
    return WxCpTpTagGetResult.deserialize(responseContent);
  }

  @Override
  public WxCpTpTagAddOrRemoveUsersResult addUsers2Tag(String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException {
    String url = getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_ADD_TAG_USERS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTpTagAddOrRemoveUsersResult.deserialize(this.mainService.post(url, jsonObject.toString()));
  }

  @Override
  public WxCpTpTagAddOrRemoveUsersResult removeUsersFromTag(String tagId, List<String> userIds, List<String> partyIds)
    throws WxErrorException {
    String url = getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tag.TAG_DEL_TAG_USERS);
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("tagid", tagId);
    this.addUserIdsAndPartyIdsToJson(userIds, partyIds, jsonObject);

    return WxCpTpTagAddOrRemoveUsersResult.deserialize(this.mainService.post(url, jsonObject.toString()));
  }

  private void addUserIdsAndPartyIdsToJson(List<String> userIds, List<String> partyIds, JsonObject jsonObject) {
    if (userIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : userIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("userlist", jsonArray);
    }

    if (partyIds != null) {
      JsonArray jsonArray = new JsonArray();
      for (String userId : partyIds) {
        jsonArray.add(new JsonPrimitive(userId));
      }
      jsonObject.add("partylist", jsonArray);
    }
  }

  @SuppressWarnings("deprecation")
  private WxCpTpConfigStorage getWxCpTpConfigStorage() {
    return this.mainService.getWxCpTpConfigStorage();
  }
}
