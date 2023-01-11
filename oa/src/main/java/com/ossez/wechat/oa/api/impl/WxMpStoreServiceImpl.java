package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.oa.bean.store.WxMpStoreBaseInfo;
import com.ossez.wechat.oa.bean.store.WxMpStoreInfo;
import com.ossez.wechat.oa.bean.store.WxMpStoreListResult;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.BeanUtils;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.api.WxMpStoreService;
import com.ossez.wechat.oa.enums.WxMpApiUrl;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.util.List;

/**
 * Created by Binary Wang on 2016/9/26.
 *
 * @author binarywang (https://github.com/binarywang)
 */
@RequiredArgsConstructor
public class WxMpStoreServiceImpl implements WxMpStoreService {
  private final WxMpService wxMpService;

  @Override
  public void add(WxMpStoreBaseInfo request) throws WxErrorException {
    BeanUtils.checkRequiredFields(request);

    String response = this.wxMpService.post(WxMpApiUrl.Store.POI_ADD_URL, request.toJson());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreBaseInfo get(String poiId) throws WxErrorException {
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxMpService.post(WxMpApiUrl.Store.POI_GET_URL, paramObject.toString());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
    return WxMpStoreBaseInfo.fromJson(GsonParser.parse(response)
      .get("business").getAsJsonObject().get("base_info").toString());
  }

  @Override
  public void delete(String poiId) throws WxErrorException {
    JsonObject paramObject = new JsonObject();
    paramObject.addProperty("poi_id", poiId);
    String response = this.wxMpService.post(WxMpApiUrl.Store.POI_DEL_URL, paramObject.toString());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public WxMpStoreListResult list(int begin, int limit)
    throws WxErrorException {
    JsonObject params = new JsonObject();
    params.addProperty("begin", begin);
    params.addProperty("limit", limit);
    String response = this.wxMpService.post(WxMpApiUrl.Store.POI_LIST_URL, params.toString());

    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpStoreListResult.fromJson(response);
  }

  @Override
  public List<WxMpStoreInfo> listAll() throws WxErrorException {
    int limit = 50;
    WxMpStoreListResult list = this.list(0, limit);
    List<WxMpStoreInfo> stores = list.getBusinessList();
    if (list.getTotalCount() > limit) {
      int begin = limit;
      WxMpStoreListResult followingList = this.list(begin, limit);
      while (followingList.getBusinessList().size() > 0) {
        stores.addAll(followingList.getBusinessList());
        begin += limit;
        if (begin >= list.getTotalCount()) {
          break;
        }
        followingList = this.list(begin, limit);
      }
    }

    return stores;
  }

  @Override
  public void update(WxMpStoreBaseInfo request) throws WxErrorException {
    String response = this.wxMpService.post(WxMpApiUrl.Store.POI_UPDATE_URL, request.toJson());
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }
  }

  @Override
  public List<String> listCategories() throws WxErrorException {
    String response = this.wxMpService.get(WxMpApiUrl.Store.POI_GET_WX_CATEGORY_URL, null);
    WxError wxError = WxError.fromJson(response, WxType.MP);
    if (wxError.getErrorCode() != 0) {
      throw new WxErrorException(wxError);
    }

    return WxMpGsonBuilder.create().fromJson(
      GsonParser.parse(response).get("category_list"),
      new TypeToken<List<String>>() {
      }.getType());
  }

}
