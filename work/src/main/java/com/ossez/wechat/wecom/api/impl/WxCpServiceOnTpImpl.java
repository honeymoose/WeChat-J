package com.ossez.wechat.wecom.api.impl;

import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.model.WeChatAccessToken;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

/**
 * <pre>
 *  默认接口实现类，使用apache httpclient实现，配合第三方应用service使用
 * Created by zhenjun cai.
 * </pre>
 *
 * @author zhenjun cai
 */
@RequiredArgsConstructor
public class WxCpServiceOnTpImpl extends WxCpServiceApacheHttpClientImpl {
  private final WxCpTpService wxCpTpService;

  @Override
  public String getAccessToken(boolean forceRefresh) throws WxErrorException {
    if (!this.configStorage.isAccessTokenExpired() && !forceRefresh) {
      return this.configStorage.getAccessToken();
    }
    //access token通过第三方应用service获取
    //corpSecret对应企业永久授权码
    WeChatAccessToken accessToken = wxCpTpService.getCorpToken(this.configStorage.getCorpId(),
      this.configStorage.getCorpSecret());

    this.configStorage.updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
    return this.configStorage.getAccessToken();
  }

}
