package com.ossez.wechat.common.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * https://mp.weixin.qq.com/wiki?t=resource/res_main&id=mp1421140842
 *
 * @author Daniel Qian
 */
public class WeChatOAuth2AccessToken implements Serializable {
    private static final long serialVersionUID = 5755678830089329526L;

    @JsonProperty("access_token")
    private String accessToken;

    @JsonProperty("expires_in")
    private int expiresIn = -1;

    @JsonProperty("refresh_token")
    private String refreshToken;

    @JsonProperty("openid")
    private String openId;

    @JsonProperty("scope")
    private String scope;

    @JsonProperty("is_snapshotuser")
    private Integer snapshotUser;

    /**
     * https://mp.weixin.qq.com/cgi-bin/announce?action=getannouncement&announce_id=11513156443eZYea&version=&lang=zh_CN.
     * 本接口在scope参数为snsapi_base时不再提供unionID字段。
     */
    @SerializedName("unionid")
    private String unionId;

    public static WeChatOAuth2AccessToken fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, WeChatOAuth2AccessToken.class);
    }

    @Override
    public String toString() {
        return WxGsonBuilder.create().toJson(this);
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

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }

    public Integer getSnapshotUser() {
        return snapshotUser;
    }

    public void setSnapshotUser(Integer snapshotUser) {
        this.snapshotUser = snapshotUser;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}
