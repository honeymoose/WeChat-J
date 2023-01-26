package com.ossez.wechat.wecom.tp.service.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpTpContactSearch;
import com.ossez.wechat.wecom.bean.WxCpTpContactSearchResp;
import com.ossez.wechat.wecom.tp.service.WxCpTpContactService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

/**
 * The type Wx cp tp contact service.
 *
 * @author uianz
 * @description
 * @since 2020 /12/23 下午 02:39
 */
@RequiredArgsConstructor
public class WxCpTpContactServiceImpl implements WxCpTpContactService {

  private final WxCpTpService mainService;

  @Override
  public WxCpTpContactSearchResp contactSearch(WxCpTpContactSearch wxCpTpContactSearch) throws WxErrorException {
    String responseText =
      mainService.post(mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Tp.CONTACT_SEARCH) + "?provider_access_token=" + mainService.getWxCpProviderToken(), wxCpTpContactSearch.toJson());
    return WxCpTpContactSearchResp.fromJson(responseText);
  }

}
