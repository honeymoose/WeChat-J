package com.ossez.wechat.oa.api.impl;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.oa.api.WxMpMarketingService;
import com.ossez.wechat.oa.api.WeChatOfficialAccountService;
import com.ossez.wechat.oa.bean.marketing.WxMpAdLeadFilter;
import com.ossez.wechat.oa.bean.marketing.WxMpAdLeadResult;
import com.ossez.wechat.oa.bean.marketing.WxMpUserAction;
import com.ossez.wechat.oa.bean.marketing.WxMpUserActionSet;
import org.apache.commons.lang3.time.DateFormatUtils;

import java.io.IOException;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.Date;
import java.util.List;

import static com.ossez.wechat.common.enums.WxMpApiUrl.Marketing.*;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Slf4j
@RequiredArgsConstructor
public class WxMpMarketingServiceImpl implements WxMpMarketingService {
  private final WeChatOfficialAccountService weChatOfficialAccountService;

  @Override
  public long addUserActionSets(String type, String name, String description) throws WxErrorException {
    JsonObject json = new JsonObject();
    json.addProperty("type", type);
    json.addProperty("name", name);
    json.addProperty("description", description);
    String responseContent = weChatOfficialAccountService.post(USER_ACTION_SETS_ADD, json.toString());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("data").getAsJsonObject().get("user_action_set_id").getAsLong();
  }

  @Override
  public List<WxMpUserActionSet> getUserActionSets(Long userActionSetId) throws WxErrorException {
    String responseContent = weChatOfficialAccountService.get(USER_ACTION_SETS_GET, "version=v1.0&user_action_set_id=" + userActionSetId);
    return WxMpUserActionSet.fromJson(responseContent);
  }

  @Override
  public void addUserAction(List<WxMpUserAction> actions) throws WxErrorException {
    weChatOfficialAccountService.post(USER_ACTIONS_ADD, WxMpUserAction.listToJson(actions));
  }

  @Override
  public WxMpAdLeadResult getAdLeads(Date beginDate, Date endDate, List<WxMpAdLeadFilter> filtering, Integer page, Integer pageSize)
    throws WxErrorException, IOException {
    Date today = new Date();
    if (beginDate == null) {
      beginDate = today;
    }
    if (endDate == null) {
      endDate = today;
    }
    String params = "version=v1.0";
    JsonObject dateRange = new JsonObject();
    dateRange.addProperty("begin_date", DateFormatUtils.format(beginDate, "yyyy-MM-dd"));
    dateRange.addProperty("end_date", DateFormatUtils.format(endDate, "yyyy-MM-dd"));
    params += "&date_range=" + URLEncoder.encode(dateRange.toString(), StandardCharsets.UTF_8.name());
    params += "&page=" + page;
    params += "&page_size=" + pageSize;
    if (filtering != null) {
      JsonArray filterJson = new JsonArray();
      for (WxMpAdLeadFilter filter : filtering) {
        filterJson.add(filter.toJsonObject());
      }
      params += "&filtering=" + URLEncoder.encode(filterJson.toString(), StandardCharsets.UTF_8.name());
    }
    String responseContent = weChatOfficialAccountService.get(WECHAT_AD_LEADS_GET, params);
    return WxMpAdLeadResult.fromJson(responseContent);
  }
}
