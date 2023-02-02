package com.ossez.wechat.common.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */
public class WeChatResponseStatus {
    @JsonProperty("errcode")
    private Integer errorCode;
    @JsonProperty("errmsg")
    private String errorMsg;

    public Integer getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(Integer errorCode) {
        this.errorCode = errorCode;
    }

    public String getErrorMsg() {
        return errorMsg;
    }

    public void setErrorMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }
}
