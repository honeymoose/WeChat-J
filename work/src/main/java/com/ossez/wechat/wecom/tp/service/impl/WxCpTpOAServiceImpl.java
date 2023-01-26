package com.ossez.wechat.wecom.tp.service.impl;

import com.google.gson.JsonObject;
import com.ossez.wechat.common.exception.WxErrorException;
import com.ossez.wechat.common.util.json.GsonParser;
import com.ossez.wechat.wecom.constant.WxCpApiPathConsts;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import com.ossez.wechat.wecom.bean.oa.WxCpApprovalDetailResult;
import com.ossez.wechat.wecom.bean.oa.WxCpOaApplyEventRequest;
import com.ossez.wechat.wecom.bean.oa.WxCpOaApprovalTemplateResult;
import com.ossez.wechat.wecom.tp.service.WxCpTpOAService;
import com.ossez.wechat.wecom.tp.service.WxCpTpService;

/**
 * 企业微信 OA 接口实现
 *
 * @author Element  created on  2019-04-06 11:20
 */
@RequiredArgsConstructor
public class WxCpTpOAServiceImpl implements WxCpTpOAService {
    private final WxCpTpService mainService;


    @Override
    public String apply(WxCpOaApplyEventRequest request, String corpId) throws WxErrorException {
        String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.APPLY_EVENT) +
                "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);

        String responseContent = this.mainService.post(url, request.toJson());
        return GsonParser.parse(responseContent).get("sp_no").getAsString();
    }

    @Override
    public WxCpOaApprovalTemplateResult getTemplateDetail(@NonNull String templateId, String corpId) throws WxErrorException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("template_id", templateId);
        String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.GET_TEMPLATE_DETAIL) +
                "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
        String responseContent = this.mainService.post(url, jsonObject.toString());
        return WxCpGsonBuilder.create().fromJson(responseContent, WxCpOaApprovalTemplateResult.class);
    }

    @Override
    public String copyTemplate(@NonNull String openTemplateId, String corpId) throws WxErrorException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("open_template_id", openTemplateId);
        String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.COPY_TEMPLATE) +
                "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
        String responseContent = this.mainService.post(url, jsonObject.toString());
        return GsonParser.parse(responseContent).get("template_id").getAsString();
    }

    @Override
    public WxCpApprovalDetailResult getApprovalDetail(@NonNull String spNo, String corpId) throws WxErrorException {
        JsonObject jsonObject = new JsonObject();
        jsonObject.addProperty("sp_no", spNo);
        final String url = mainService.getWxCpTpConfigStorage().getApiUrl(WxCpApiPathConsts.Oa.GET_APPROVAL_DETAIL) +
                "?access_token=" + mainService.getWxCpTpConfigStorage().getAccessToken(corpId);
        String responseContent = this.mainService.post(url, jsonObject.toString());
        return WxCpGsonBuilder.create().fromJson(responseContent, WxCpApprovalDetailResult.class);
    }
}
