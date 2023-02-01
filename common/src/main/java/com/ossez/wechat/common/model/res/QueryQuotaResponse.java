package com.ossez.wechat.common.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */
public class QueryQuotaResponse {
    @JsonProperty("errcode")
    private Integer errorCode;
    @JsonProperty("errmsg")
    private String errorMsg;
    @JsonProperty("quota")
    private Quota quota;

    private static class Quota {
        @JsonProperty("daily_limit")
        private String dailyLimit;
        @JsonProperty("used")
        private String used;
        @JsonProperty("remain")
        private String remain;

        public String getDailyLimit() {
            return dailyLimit;
        }

        public void setDailyLimit(String dailyLimit) {
            this.dailyLimit = dailyLimit;
        }

        public String getUsed() {
            return used;
        }

        public void setUsed(String used) {
            this.used = used;
        }

        public String getRemain() {
            return remain;
        }

        public void setRemain(String remain) {
            this.remain = remain;
        }
    }

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
