package com.ossez.wechat.wecom.api.impl;

import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.wecom.api.WxCpMenuService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;

/**
 * <pre>
 * 菜单管理相关接口.
 * Created by Binary Wang on 2017-6-25.
 * </pre>
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a>
 */
@RequiredArgsConstructor
public class WxCpMenuServiceImpl implements WxCpMenuService {
  private final WxCpService mainService;

  @Override
  public void create(WxMenu menu) throws WxErrorException {
    this.create(this.mainService.getWxCpConfigStorage().getAgentId(), menu);
  }

  @Override
  public void create(Integer agentId, WxMenu menu) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Menu.MENU_CREATE), agentId);
    this.mainService.post(url, menu.toJson());
  }

  @Override
  public void delete() throws WxErrorException {
    this.delete(this.mainService.getWxCpConfigStorage().getAgentId());
  }

  @Override
  public void delete(Integer agentId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Menu.MENU_DELETE), agentId);
    this.mainService.get(url, null);
  }

  @Override
  public WxMenu get() throws WxErrorException {
    return this.get(this.mainService.getWxCpConfigStorage().getAgentId());
  }

  @Override
  public WxMenu get(Integer agentId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Menu.MENU_GET), agentId);
    try {
      String resultContent = this.mainService.get(url, null);
      return WxCpGsonBuilder.create().fromJson(resultContent, WxMenu.class);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据
      if (e.getError().getErrorCode() == 46003) {
        return null;
      }
      throw e;
    }
  }
}
