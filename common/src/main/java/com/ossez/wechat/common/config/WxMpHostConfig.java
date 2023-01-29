package com.ossez.wechat.common.config;

import com.ossez.wechat.common.constant.WeChatConstant;

/**
 * 微信接口地址域名部分的自定义设置信息.
 */
public class WxMpHostConfig {

    /**
     * 对应于：https://api.weixin.qq.com
     */
    private String apiHost;

    /**
     * 对应于：https://open.weixin.qq.com
     */
    private String openHost;

    /**
     * 对应于：https://mp.weixin.qq.com
     */
    private String mpHost;

    public static String buildUrl(WxMpHostConfig hostConfig, String prefix, String path) {
        if (hostConfig == null) {
            return prefix + path;
        }

        if (hostConfig.getApiHost() != null && prefix.equals(WeChatConstant.ENDPOINT_WECHAT)) {
            return hostConfig.getApiHost() + path;
        }

        if (hostConfig.getMpHost() != null && prefix.equals(WeChatConstant.ENDPOINT_MP)) {
            return hostConfig.getMpHost() + path;
        }

        if (hostConfig.getOpenHost() != null && prefix.equals(WeChatConstant.ENDPOINT_OPEN)) {
            return hostConfig.getOpenHost() + path;
        }

        return prefix + path;
    }

    public String getApiHost() {
        return apiHost;
    }

    public void setApiHost(String apiHost) {
        this.apiHost = apiHost;
    }

    public String getOpenHost() {
        return openHost;
    }

    public void setOpenHost(String openHost) {
        this.openHost = openHost;
    }

    public String getMpHost() {
        return mpHost;
    }

    public void setMpHost(String mpHost) {
        this.mpHost = mpHost;
    }
}
