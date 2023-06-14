package com.ossez.wechat.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ossez.wechat.common.model.entity.menu.MenuButton;
import com.ossez.wechat.common.model.res.DataCubeArticle;

import java.io.Serializable;
import java.util.List;

/**
 * CustomMessage
 *
 * @author YuCheng
 */
public class MenuRequest implements Serializable {
    private static final long serialVersionUID = -9196732086954365246L;
    @JsonProperty(value = "button", required = true)
    private List<MenuButton> buttonList;


    public List<MenuButton> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<MenuButton> buttonList) {
        this.buttonList = buttonList;
    }
}
