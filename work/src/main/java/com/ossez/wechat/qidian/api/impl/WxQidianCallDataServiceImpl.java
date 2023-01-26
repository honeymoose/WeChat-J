package com.ossez.wechat.qidian.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.qidian.bean.call.GetSwitchBoardListResponse;
import com.ossez.wechat.qidian.enums.WxQidianApiUrl;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.qidian.api.WxQidianCallDataService;
import com.ossez.wechat.qidian.api.WxQidianService;

@Slf4j
@RequiredArgsConstructor
public class WxQidianCallDataServiceImpl implements WxQidianCallDataService {
  private final WxQidianService wxQidianService;

  @Override
  public GetSwitchBoardListResponse getSwitchBoardList() throws WxErrorException {
    String result = this.wxQidianService.get(WxQidianApiUrl.CallData.GET_SWITCH_BOARD_LIST, null);
    return GetSwitchBoardListResponse.fromJson(result);
  }

}
