package com.ossez.wechat.common.model.entity;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * oauth2用户个人信息.
 * <pre>
 * {
 * "openid":"OPENID",
 * "nickname":"NICKNAME",
 * "sex":1,
 * "province":"PROVINCE",
 * "city":"CITY",
 * "country":"COUNTRY",
 * "headimgurl": "https://thirdwx.qlogo.cn/mmopen/g3MonUZtNHkdmzicIlibx6iaFqAc56vxLSUfpb6n5WKSYVY0ChQKkiaJSgQ1dZuTOgvLLrhJbERQQ4eMsv84eavHiaiceqxibJxCfHe/0",
 * "privilege":[
 * "PRIVILEGE1",
 * "PRIVILEGE2"
 * ],
 * "unionid": " o6_bmasdasdsad6_2sgVt7hMZOPfL"
 *
 * }
 *  </pre>
 *
 * @author YuCheng
 */
@Data
public class WeChatOAuth2UserInfo implements Serializable {
    private static final long serialVersionUID = 3181943506448954725L;

    @JsonProperty("openid")
    private String openid;
    @JsonProperty("nickname")
    private String nickname;
    @JsonProperty("sex")
    private Integer sex;
    @JsonProperty("city")
    private String city;
    @JsonProperty("province")
    private String province;
    @JsonProperty("country")
    private String country;
    @JsonProperty("headimgurl")
    private String headImgUrl;

    @JsonProperty("unionid")
    private String unionId;

    @JsonProperty("privilege")
    private String[] privileges;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
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

    public String getHeadImgUrl() {
        return headImgUrl;
    }

    public void setHeadImgUrl(String headImgUrl) {
        this.headImgUrl = headImgUrl;
    }

    public String getUnionId() {
        return unionId;
    }

    public void setUnionId(String unionId) {
        this.unionId = unionId;
    }

    public String[] getPrivileges() {
        return privileges;
    }

    public void setPrivileges(String[] privileges) {
        this.privileges = privileges;
    }
}
