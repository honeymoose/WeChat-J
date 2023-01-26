package com.ossez.wechat.qidian.api;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.qidian.bean.call.GetSwitchBoardListResponse;

/**
 * 通话数据相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianCallDataService {
  public GetSwitchBoardListResponse getSwitchBoardList() throws WxErrorException;
}
