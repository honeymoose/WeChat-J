package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.http.SimplePostRequestExecutor;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.api.WxMpUserBlacklistService;
import com.ossez.wechat.oa.bean.result.WxMpUserBlacklistGetResult;
import com.ossez.wechat.oa.util.json.WxMpGsonBuilder;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.UserBlacklist.*;

/**
 * @author miller
 */
@RequiredArgsConstructor
public class WxMpUserBlacklistServiceImpl implements WxMpUserBlacklistService {
  private final WxMpService wxMpService;

  @Override
  public WxMpUserBlacklistGetResult getBlacklist(String nextOpenid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("begin_openid", nextOpenid);
    String responseContent = this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()),
      GETBLACKLIST, jsonObject.toString());
    return WxMpUserBlacklistGetResult.fromJson(responseContent);
  }

  @Override
  public void pushToBlacklist(List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("openid_list", openidList);
    this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()), BATCHBLACKLIST,
      WxMpGsonBuilder.create().toJson(map));
  }

  @Override
  public void pullFromBlacklist(List<String> openidList) throws WxErrorException {
    Map<String, Object> map = new HashMap<>(2);
    map.put("openid_list", openidList);
    this.wxMpService.execute(SimplePostRequestExecutor.create(this.wxMpService.getRequestHttp()), BATCHUNBLACKLIST,
      WxMpGsonBuilder.create().toJson(map));
  }
}
