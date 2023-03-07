package com.ossez.wechat.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WeChatStatus Response Object
 *
 * @author YuCheng Hu
 */

public class WeChatStatus {

    @JsonProperty("errcode")
    private int errCode = 0;

    @JsonProperty("errmsg")
    private String errMsg;

    public int getErrCode() {
        return errCode;
    }

    public void setErrCode(int errCode) {
        this.errCode = errCode;
    }

    public String getErrMsg() {
        return errMsg;
    }

    public void setErrMsg(String errMsg) {
        this.errMsg = errMsg;
    }
}
