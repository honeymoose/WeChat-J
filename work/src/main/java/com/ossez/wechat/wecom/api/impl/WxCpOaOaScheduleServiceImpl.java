package com.ossez.wechat.wecom.api.impl;

import com.google.common.collect.ImmutableMap;
import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.wecom.api.WxCpOaScheduleService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.oa.WxCpOaSchedule;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 企业微信日程接口实现类.
 *
 * @author <a href="https://github.com/binarywang">Binary Wang</a> created on  2020-12-25
 */
@Slf4j
@RequiredArgsConstructor
public class WxCpOaOaScheduleServiceImpl implements WxCpOaScheduleService {
  private final WxCpService cpService;

  @Override
  public String add(WxCpOaSchedule schedule, Integer agentId) throws WxErrorException {
    Map<String, Serializable> param;
    if (agentId == null) {
      param = ImmutableMap.of("schedule", schedule);
    } else {
      param = ImmutableMap.of("schedule", schedule, "agentid", agentId);
    }

    return this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.SCHEDULE_ADD),
      WxCpGsonBuilder.create().toJson(param));
  }

  @Override
  public void update(WxCpOaSchedule schedule) throws WxErrorException {
    this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.SCHEDULE_UPDATE),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("schedule", schedule)));
  }

  @Override
  public List<WxCpOaSchedule> getDetails(List<String> scheduleIds) throws WxErrorException {
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.SCHEDULE_GET),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("schedule_id_list", scheduleIds)));
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("schedule_list"),
      new TypeToken<List<WxCpOaSchedule>>() {
      }.getType());
  }

  @Override
  public void delete(String scheduleId) throws WxErrorException {
    this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.SCHEDULE_DEL),
      WxCpGsonBuilder.create().toJson(ImmutableMap.of("schedule_id", scheduleId)));
  }

  @Override
  public List<WxCpOaSchedule> listByCalendar(String calId, Integer offset, Integer limit) throws WxErrorException {
    final Map<String, Object> param = new HashMap<>(3);
    param.put("cal_id", calId);
    if (offset != null) {
      param.put("offset", offset);
    }
    if (limit != null) {
      param.put("limit", limit);
    }
    final String response = this.cpService.post(this.cpService.getWxCpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.SCHEDULE_LIST),
      WxCpGsonBuilder.create().toJson(param));
    return WxCpGsonBuilder.create().fromJson(GsonParser.parse(response).get("schedule_list"),
      new TypeToken<List<WxCpOaSchedule>>() {
      }.getType());
  }
}
