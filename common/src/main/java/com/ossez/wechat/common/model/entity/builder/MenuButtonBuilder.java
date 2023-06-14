package com.ossez.wechat.common.model.entity.builder;

import com.ossez.wechat.common.model.entity.menu.MenuButton;
import com.ossez.wechat.common.model.req.MenuRequest;

import java.util.List;

public class MenuButtonBuilder {
    private List<MenuButton> subButtonList;
    private String type;
    private String name;
    private String key;
    private String url;

    public MenuButtonBuilder setSubButtonList(List<MenuButton> subButtonList) {
        this.subButtonList = subButtonList;
        return this;
    }

    public MenuButtonBuilder setType(String type) {
        this.type = type;
        return this;
    }

    public MenuButtonBuilder setName(String name) {
        this.name = name;
        return this;
    }

    public MenuButtonBuilder setKey(String key) {
        this.key = key;
        return this;
    }

    public MenuButtonBuilder setUrl(String url) {
        this.url = url;
        return this;
    }

    public MenuButton createMenuButton() {
        return new MenuButton(subButtonList, type, name, key, url);
    }
}