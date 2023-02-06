package com.ossez.wechat.common.model;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */

public class WeChatAccessToken {


    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn = -1;

    public static WeChatAccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WeChatAccessToken.class);
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public int getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(int expiresIn) {
        this.expiresIn = expiresIn;
    }

}
