package com.ossez.wechat.common.model.entity.menu;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.common.model.req.MenuRequest;
import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;

/**
 * menu button.
 *
 * @author Daniel Qian
 */
@Data
public class MenuButton implements Serializable {
  private static final long serialVersionUID = -1070939403109776555L;

  public MenuButton(List<MenuButton> subButtonList, String type, String name, String key, String url) {
    this.subButtonList = subButtonList;
    this.type = type;
    this.name = name;
    this.key = key;
    this.url = url;
  }

  @JsonProperty(value = "sub_button")
  private List<MenuButton> subButtonList;
  @JsonProperty("type")
  private String type;
  @JsonProperty("name")
  private String name;
  @JsonProperty("key")
  private String key;
  @JsonProperty("url")
  private String url;
}
