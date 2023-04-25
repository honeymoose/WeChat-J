package com.ossez.wechat.common.model.req;

import java.util.List;

public class ButtonBuilder {
    private List<MenuRequest.Button> subButtonList;
    private String type;
    private String name;
    private String key;
    private String url;

    public ButtonBuilder setSubButtonList(List<MenuRequest.Button> subButtonList) {
        this.subButtonList = subButtonList;
        return this;
    }

    public ButtonBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public ButtonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public ButtonBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public ButtonBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public MenuRequest.Button createButton() {
        return new MenuRequest.Button(subButtonList, type, name, key, url);
    }
}