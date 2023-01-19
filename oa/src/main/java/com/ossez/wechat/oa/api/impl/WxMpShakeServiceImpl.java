package com.ossez.wechat.oa.api.impl;

import com.ossez.wechat.oa.bean.WxMpShakeInfoResult;
import com.ossez.wechat.oa.bean.WxMpShakeQuery;
import com.ossez.wechat.oa.bean.shake.*;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.enums.WxType;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.api.WxMpShakeService;

import static com.ossez.wechat.oa.enums.WxMpApiUrl.ShakeAround.*;

/**
 * Created by rememberber on 2017/6/5.
 *
 * @author rememberber
 */
@RequiredArgsConstructor
public class WxMpShakeServiceImpl implements WxMpShakeService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public WxMpShakeInfoResult getShakeInfo(WxMpShakeQuery wxMpShakeQuery) throws WxErrorException {
    String postData = wxMpShakeQuery.toJsonString();
    String responseContent = this.weChatOfficialAccountService.post(SHAKEAROUND_USER_GETSHAKEINFO, postData);
    return WxMpShakeInfoResult.fromJson(responseContent);
  }

  @Override
  public WxMpShakeAroundPageAddResult pageAdd(WxMpShakeAroundPageAddQuery shakeAroundPageAddQuery)
    throws WxErrorException {
    String postData = shakeAroundPageAddQuery.toJsonString();
    String responseContent = this.weChatOfficialAccountService.post(SHAKEAROUND_PAGE_ADD, postData);
    return WxMpShakeAroundPageAddResult.fromJson(responseContent);
  }

  @Override
  public WxError deviceBindPageQuery(WxMpShakeAroundDeviceBindPageQuery shakeAroundDeviceBindPageQuery)
    throws WxErrorException {
    String postData = shakeAroundDeviceBindPageQuery.toJsonString();
    String responseContent = this.weChatOfficialAccountService.post(SHAKEAROUND_DEVICE_BINDPAGE, postData);
    return WxError.fromJson(responseContent, WxType.MP);
  }

  @Override
  public WxMpShakeAroundRelationSearchResult relationSearch(WxMpShakeAroundRelationSearchQuery searchQuery)
    throws WxErrorException {
    String postData = searchQuery.toJsonString();
    String responseContent = this.weChatOfficialAccountService.post(SHAKEAROUND_RELATION_SEARCH, postData);
    return WxMpShakeAroundRelationSearchResult.fromJson(responseContent);
  }
}
