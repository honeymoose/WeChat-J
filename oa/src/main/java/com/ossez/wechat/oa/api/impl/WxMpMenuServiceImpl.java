package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.oa.bean.menu.WxMpGetSelfMenuInfoResult;
import com.ossez.wechat.oa.bean.menu.WxMpMenu;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpMenuService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.common.enums.WxMpApiUrl;

import static com.ossez.wechat.common.enums.WxMpApiUrl.Menu.*;

/**
 * Created by Binary Wang on 2016/7/21.
 *
 * @author Binary Wang
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpMenuServiceImpl implements WxMpMenuService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public String menuCreate(WxMenu menu) throws WxErrorException {
    String menuJson = menu.toJson();
    WxMpApiUrl.Menu url = MENU_CREATE;
    if (menu.getMatchRule() != null) {
      url = MENU_ADDCONDITIONAL;
    }

    log.debug("开始创建菜单：{}", menuJson);

    String result = this.weChatOfficialAccountService.post(url, menuJson);
    log.debug("创建菜单：{},结果：{}", menuJson, result);

    if (menu.getMatchRule() != null) {
      return GsonParser.parse(result).get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public String menuCreate(String json) throws WxErrorException {
    JsonObject jsonObject = GsonParser.parse(json);
    WxMpApiUrl.Menu url = MENU_CREATE;
    if (jsonObject.get("matchrule") != null) {
      url = MENU_ADDCONDITIONAL;
    }

    String result = this.weChatOfficialAccountService.post(url, json);
    if (jsonObject.get("matchrule") != null) {
      return GsonParser.parse(result).get("menuid").getAsString();
    }

    return null;
  }

  @Override
  public void menuDelete() throws WxErrorException {
    String result = this.weChatOfficialAccountService.get(MENU_DELETE, null);
    log.debug("删除菜单结果：{}", result);
  }

  @Override
  public void menuDelete(String menuId) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("menuid", menuId);
    String result = this.weChatOfficialAccountService.post(MENU_DELCONDITIONAL, jsonObject.toString());
    log.debug("根据MeunId({})删除个性化菜单结果：{}", menuId, result);
  }

  @Override
  public WxMpMenu menuGet() throws WxErrorException {
    try {
      String resultContent = this.weChatOfficialAccountService.get(MENU_GET, null);
      return WxMpMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据
      if (e.getError().getErrorCode() == 46003) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMenu menuTryMatch(String userid) throws WxErrorException {
    JsonObject jsonObject = new JsonObject();
    jsonObject.addProperty("user_id", userid);
    try {
      String resultContent = this.weChatOfficialAccountService.post(MENU_TRYMATCH, jsonObject.toString());
      return WxMenu.fromJson(resultContent);
    } catch (WxErrorException e) {
      // 46003 不存在的菜单数据；46002 不存在的菜单版本
      if (e.getError().getErrorCode() == 46003
        || e.getError().getErrorCode() == 46002) {
        return null;
      }
      throw e;
    }
  }

  @Override
  public WxMpGetSelfMenuInfoResult getSelfMenuInfo() throws WxErrorException {
    String resultContent = this.weChatOfficialAccountService.get(GET_CURRENT_SELFMENU_INFO, null);
    return WxMpGetSelfMenuInfoResult.fromJson(resultContent);
  }
}
