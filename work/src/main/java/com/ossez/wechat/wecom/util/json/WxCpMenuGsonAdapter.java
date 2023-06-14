package com.ossez.wechat.wecom.util.json;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonElement;
import com.google.gson.JsonParseException;
import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.util.json.WxMenuGsonAdapter;

import java.lang.reflect.Type;

/**
 * <pre>
 * 企业号菜单json转换适配器
 * Created by Binary Wang on 2017-6-25.
 * @author  <a href="https://github.com/binarywang">Binary Wang</a> </pre>
 */
public class WxCpMenuGsonAdapter extends WxMenuGsonAdapter {

//  @Override
//  public WxMenu deserialize(JsonElement json, Type typeOfT, JsonDeserializationContext context) throws JsonParseException {
//    return this.buildMenuFromJson(json.getAsJsonObject().get("button").getAsJsonArray());
//  }
}
