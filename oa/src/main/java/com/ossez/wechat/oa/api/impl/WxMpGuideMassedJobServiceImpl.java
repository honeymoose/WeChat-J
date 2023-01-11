package com.ossez.wechat.oa.api.impl;

import com.google.gson.reflect.TypeToken;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMassed;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMassedInfo;
import com.ossez.wechat.oa.bean.guide.WxMpGuideMaterialInfo;
import lombok.AllArgsConstructor;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.oa.api.WxMpGuideMassedJobService;
import com.ossez.wechat.oa.api.WxMpService;
import com.ossez.wechat.oa.enums.WxMpApiUrl;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

/**
 * @author <a href="https://www.sacoc.cn">广州跨界-宋心成</a>
 * created on  2021/5/13/013
 */
@AllArgsConstructor
public class WxMpGuideMassedJobServiceImpl implements WxMpGuideMassedJobService {
  private static final String ACCOUNT = "guide_account";
  private static final String OPENID = "guide_openid";
  private final WxMpService mpService;

  @Override
  public WxMpGuideMassed addGuideMassedJob(String account, String openid, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("task_name", taskName);
    body.put("task_remark", taskRemark);
    body.put("push_time", pushTime);
    body.put("openid", userOpenIds);
    body.put("material", materialInfos);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.ADD_GUIDE_MASSED_JOB, body);
    return WxMpGuideMassed.fromJson(GsonParser.parse(returnString).getAsJsonArray("task_result").get(0));
  }

  @Override
  public List<WxMpGuideMassedInfo> getGuideMassedJobList(String account, String openid, List<Integer> taskStatus, Integer offset, Integer limit) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put(ACCOUNT, account);
    body.put(OPENID, openid);
    body.put("task_status", taskStatus);
    body.put("offset", offset);
    body.put("limit", limit);
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_MASSED_JOB_LIST, body);
    return WxGsonBuilder.create().fromJson(GsonParser.parse(returnString).getAsJsonArray("list"),
      new TypeToken<List<WxMpGuideMassedInfo>>() {
      }.getType());
  }

  @Override
  public WxMpGuideMassedInfo getGuideMassedJob(String taskId) throws WxErrorException {
    String returnString = this.mpService.post(WxMpApiUrl.Guide.GET_GUIDE_MASSED_JOB, GsonHelper.buildJsonObject("task_id", taskId));
    return WxMpGuideMassedInfo.fromJson(GsonParser.parse(returnString).get("job"));
  }

  @Override
  public void updateGuideMassedJob(String taskId, String taskName, String taskRemark, Long pushTime, List<String> userOpenIds, List<WxMpGuideMaterialInfo> materialInfos) throws WxErrorException {
    Map<String, Object> body = new LinkedHashMap<>();
    body.put("task_id", taskId);
    body.put("task_name", taskName);
    body.put("task_remark", taskRemark);
    body.put("push_time", pushTime);
    body.put("openid", userOpenIds);
    body.put("material", materialInfos);
    this.mpService.post(WxMpApiUrl.Guide.UPDATE_GUIDE_MASSED_JOB, body);
  }

  @Override
  public void cancelGuideMassedJob(String taskId) throws WxErrorException {
    this.mpService.post(WxMpApiUrl.Guide.CANCEL_GUIDE_MASSED_JOB, GsonHelper.buildJsonObject("task_id", taskId));
  }
}
