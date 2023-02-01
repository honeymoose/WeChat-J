package com.ossez.wechat.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import org.apache.commons.lang3.StringUtils;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */
public class QueryQuota {
    @JsonProperty("cgi_path")
    private String cgiPath = "/cgi-bin/message/custom/send";

    public String getCgiPath() {
        return cgiPath;
    }

    public void setCgiPath(String cgiPath) {
        this.cgiPath = cgiPath;
    }
}
