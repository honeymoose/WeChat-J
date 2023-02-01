package com.ossez.wechat.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import org.apache.commons.lang3.StringUtils;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */
public class NetworkCheck {
    @JsonProperty("action")
    private String action = "all";
    @JsonProperty("check_operator")
    private String checkOperator = StringUtils.upperCase("DEFAULT");

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getCheckOperator() {
        return checkOperator;
    }

    public void setCheckOperator(String checkOperator) {
        this.checkOperator = checkOperator;
    }
}
