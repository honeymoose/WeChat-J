package com.ossez.wechat.oa.util.json;

import java.lang.reflect.Type;

import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.ossez.wechat.common.util.json.GsonHelper;
import com.ossez.wechat.oa.bean.card.WxMpCard;

/**
 * Created by YuJian on 15/11/11.
 *
 * @author YuJian
 * @version 15/11/11
 */
public class WxMpCardGsonAdapter implements JsonDeserializer<WxMpCard> {

  @Override
  public WxMpCard deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext
    jsonDeserializationContext) throws JsonParseException {
    WxMpCard card = new WxMpCard();

    JsonObject jsonObject = jsonElement.getAsJsonObject();

    card.setCardId(GsonHelper.getString(jsonObject, "card_id"));
    card.setBeginTime(GsonHelper.getLong(jsonObject, "begin_time"));
    card.setEndTime(GsonHelper.getLong(jsonObject, "end_time"));
    card.setUserCardStatus(GsonHelper.getString(jsonObject, "user_card_status"));
    card.setMembershipNumber(GsonHelper.getString(jsonObject, "membership_number"));
    card.setCode(GsonHelper.getString(jsonObject, "code"));
    card.setBonus(GsonHelper.getInteger(jsonObject, "bonus"));

    return card;
  }

}
