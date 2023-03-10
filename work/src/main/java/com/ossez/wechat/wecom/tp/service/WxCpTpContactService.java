package com.ossez.wechat.wecom.tp.service;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.bean.WxCpTpContactSearch;
import com.ossez.wechat.wecom.bean.WxCpTpContactSearchResp;

/**
 * The interface Wx cp tp contact service.
 *
 * @author uianz
 * @description
 * @since 2020 /12/23 下午 02:39
 */
public interface WxCpTpContactService {

  /**
   * https://work.weixin.qq.com/api/doc/90001/90143/91844
   * 通讯录单个搜索
   *
   * @param wxCpTpContactSearch the wx cp tp contact search
   * @return wx cp tp contact search resp
   * @throws WxErrorException the wx error exception
   */
  WxCpTpContactSearchResp contactSearch(WxCpTpContactSearch wxCpTpContactSearch) throws WxErrorException;
}
