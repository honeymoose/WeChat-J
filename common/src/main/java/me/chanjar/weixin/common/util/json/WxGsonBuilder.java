package me.chanjar.weixin.common.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import me.chanjar.weixin.common.bean.WxAccessToken;
import me.chanjar.weixin.common.bean.WxNetCheckResult;
import me.chanjar.weixin.common.bean.menu.WxMenu;
import me.chanjar.weixin.common.error.WxError;
import me.chanjar.weixin.common.bean.result.WxMediaUploadResult;
import java.util.Objects;

/**
 * .
 * @author chanjarster
 */
public class WxGsonBuilder {
  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxAccessToken.class, new WxAccessTokenAdapter());
    INSTANCE.registerTypeAdapter(WxError.class, new WxErrorAdapter());
    INSTANCE.registerTypeAdapter(WxMenu.class, new WxMenuGsonAdapter());
    INSTANCE.registerTypeAdapter(WxMediaUploadResult.class, new WxMediaUploadResultAdapter());
    INSTANCE.registerTypeAdapter(WxNetCheckResult.class, new WxNetCheckResultGsonAdapter());

  }

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
