package cn.binarywang.wx.miniapp.api.impl;

import cn.binarywang.wx.miniapp.api.WxMaService;
import cn.binarywang.wx.miniapp.api.WxMaSettingService;
import cn.binarywang.wx.miniapp.bean.WxMaDomainAction;
import cn.binarywang.wx.miniapp.json.WxMaGsonBuilder;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;

import java.util.HashMap;
import java.util.Map;

import static cn.binarywang.wx.miniapp.constant.WxMaApiUrlConstants.Setting.*;

/**
 * @author <a href="https://github.com/charmingoh">Charming</a>
 * @since 2018-04-27 15:46
 */
@RequiredArgsConstructor
public class WxMaSettingServiceImpl implements WxMaSettingService {
  private final WxMaService wxMaService;

  @Override
  public WxMaDomainAction modifyDomain(WxMaDomainAction domainAction) throws WxErrorException {
    String responseContent = this.wxMaService.post(MODIFY_DOMAIN_URL, domainAction.toJson());
    return WxMaDomainAction.fromJson(responseContent);
  }

  @Override
  public WxMaDomainAction setWebViewDomain(WxMaDomainAction domainAction) throws WxErrorException {
    String responseContent = this.wxMaService.post(SET_WEB_VIEW_DOMAIN_URL, domainAction.toJson());
    return WxMaDomainAction.fromJson(responseContent);
  }

  @Override
  public void bindTester(String wechatId) throws WxErrorException {
    Map<String, Object> param = new HashMap<>(1);
    param.put("wechatid", wechatId);
    this.wxMaService.post(BIND_TESTER_URL, WxMaGsonBuilder.create().toJson(param));
  }

  @Override
  public void unbindTester(String wechatId) throws WxErrorException {
    Map<String, Object> param = new HashMap<>(1);
    param.put("wechatid", wechatId);
    this.wxMaService.post(UNBIND_TESTER_URL, WxMaGsonBuilder.create().toJson(param));
  }
}
