package com.ossez.wechat.common.model.entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.experimental.Accessors;

@Accessors(chain = true)
public class WeChatUser {

    @JsonProperty(value = "openid", required = true)
    private String openId;

    @JsonProperty(required = true)
    private String nickname;

    private Integer sex;

    private String language;

    private String city;

    private String province;

    private String country;

    @JsonProperty(value = "headimgurl")
    private String headImgURL;

    @JsonProperty(value = "unionid", required = true)
    private String unionId;

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getLanguage() {
        return language;
    }

    public void setLanguage(String language) {
        this.language = language;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadImgURL() {
        return headImgURL;
    }

    public void setHeadImgURL(String headImgURL) {
        this.headImgURL = headImgURL;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }
}


