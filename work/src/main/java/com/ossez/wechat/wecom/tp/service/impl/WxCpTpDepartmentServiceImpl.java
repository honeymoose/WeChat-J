package com.ossez.wechat.wecom.tp.service.impl;

import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.wecom.api.impl.WxCpDepartmentServiceImpl;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpTpDepart;
import com.ossez.wechat.wecom.tp.service.WxCpTpDepartmentService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

import java.util.List;

/**
 * The type Wx cp tp department service.
 *
 * @author uianz
 * @description corp from {@link WxCpDepartmentServiceImpl )} 唯一不同在于获取部门列表时需要传对应企业的accessToken
 * @since 2020 /12/23 下午 02:39
 */
@RequiredArgsConstructor
public class WxCpTpDepartmentServiceImpl implements WxCpTpDepartmentService {
  private final WxCpTpService mainService;

  @Override
  public Long create(WxCpTpDepart depart) throws WxErrorException {
    String url = this.mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Department.DEPARTMENT_CREATE);
    String responseContent = this.mainService.post(url, depart.toJson());
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    return GsonHelper.getAsLong(tmpJsonObject.get("id"));
  }

  @Override
  public void update(WxCpTpDepart group) throws WxErrorException {
    String url = this.mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Department.DEPARTMENT_UPDATE);
    this.mainService.post(url, group.toJson());
  }

  @Override
  public void delete(Long departId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Department.DEPARTMENT_DELETE), departId);
    this.mainService.get(url, null);
  }

  @Override
  public List<WxCpTpDepart> list(Long id, String corpId) throws WxErrorException {
    String url = this.mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Department.DEPARTMENT_LIST);
    url += "?access_token=" + this.mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
    if (id != null) {
      url += "&id=" + id;
    }
    String responseContent = this.mainService.get(url, null);
    JsonObject tmpJsonObject = GsonParser.parse(responseContent);
    return WxCpGsonBuilder.create()
      .fromJson(tmpJsonObject.get("department"),
        new TypeToken<List<WxCpTpDepart>>() {
        }.getType()
      );
  }

  @Override
  public List<WxCpTpDepart> list(String corpId) throws WxErrorException {
    return list(null, corpId);
  }
}
