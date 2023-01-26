package com.ossez.wechat.common.enums;

/**
 * The language for WeChat API can support
 *
 * @author YuCheng Hu
 */
public enum Language {
    ZH_CN("zh_CN"), EN_US("en_US");

    private String code;

    Language(String code) {
        this.code = code;
    }

    public String getCode() {
        return code;
    }
}
