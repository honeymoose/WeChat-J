package com.ossez.wechat.wecom.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ossez.wechat.common.model.entity.menu.WxMenu;
import com.ossez.wechat.common.exception.WxError;
import com.ossez.wechat.common.util.json.WxErrorAdapter;
import com.ossez.wechat.wecom.bean.WxCpChat;
import com.ossez.wechat.wecom.bean.WxCpDepart;
import com.ossez.wechat.wecom.bean.WxCpTag;
import com.ossez.wechat.wecom.bean.WxCpUser;
import com.ossez.wechat.wecom.bean.kf.WxCpKfGetCorpStatisticResp;

import java.util.Objects;

/**
 * The type Wx cp gson builder.
 *
 * @author Daniel Qian
 */
public class WxCpGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxCpChat.class, new WxCpChatGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpDepart.class, new WxCpDepartGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpUser.class, new WxCpUserGsonAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxCpMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpTag.class, new WxCpTagGsonAdapter());
    INSTANCE.registerTypeAdapter(WxCpKfGetCorpStatisticResp.StatisticList.class, new StatisticListAdapter());
  }

  /**
   * Create gson.
   *
   * @return the gson
   */
  public static Gson create() {
    if (Objects.isNull(GSON_INSTANCE)) {
      synchronized (INSTANCE) {
        if (Objects.isNull(GSON_INSTANCE)) {
          GSON_INSTANCE = INSTANCE.create();
        }
      }
    }
    return GSON_INSTANCE;
  }

}
