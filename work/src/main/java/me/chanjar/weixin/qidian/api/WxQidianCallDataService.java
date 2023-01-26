package me.chanjar.weixin.qidian.api;

import com.ossez.wechat.common.exception.WxErrorException;
import me.chanjar.weixin.qidian.bean.call.GetSwitchBoardListResponse;

/**
 * 通话数据相关操作接口.
 *
 * @author alegria
 */
public interface WxQidianCallDataService {
  public GetSwitchBoardListResponse getSwitchBoardList() throws WxErrorException;
}
