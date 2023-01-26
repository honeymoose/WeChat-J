package com.ossez.wechat.wecom.api.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.api.WxCpExportService;
import com.ossez.wechat.wecom.api.WxCpService;
import com.ossez.wechat.wecom.bean.export.WxCpExportRequest;
import com.ossez.wechat.wecom.bean.export.WxCpExportResult;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;

import static com.ossez.wechat.wecom.constant.WxCpApiPathConsts.Export.*;

/**
 * 异步导出接口
 *
 * @author <a href="https://github.com/zhongjun96">zhongjun</a> created on  2022/4/21
 */
@RequiredArgsConstructor
public class WxCpExportServiceImpl implements WxCpExportService {

  private final WxCpService mainService;

  @Override
  public String simpleUser(WxCpExportRequest params) throws WxErrorException {
    return export(SIMPLE_USER, params);
  }

  @Override
  public String user(WxCpExportRequest params) throws WxErrorException {
    return export(USER, params);
  }

  @Override
  public String department(WxCpExportRequest params) throws WxErrorException {
    return export(DEPARTMENT, params);
  }

  @Override
  public String tagUser(WxCpExportRequest params) throws WxErrorException {
    return export(TAG_USER, params);
  }

  @Override
  public WxCpExportResult getResult(String jobId) throws WxErrorException {
    String url = String.format(this.mainService.getWxCpConfigStorage().getApiUrl(GET_RESULT), jobId);
    String responseContent = this.mainService.get(url, null);
    return WxCpGsonBuilder.create().fromJson(responseContent, WxCpExportResult.class);
  }

  private String export(String path, WxCpExportRequest params) throws WxErrorException {
    String url = this.mainService.getWxCpConfigStorage().getApiUrl(path);
    String responseContent = this.mainService.post(url, params.toJson());
    JsonObject tmpJson = GsonParser.parse(responseContent);
    return tmpJson.get("jobid").getAsString();
  }
}
