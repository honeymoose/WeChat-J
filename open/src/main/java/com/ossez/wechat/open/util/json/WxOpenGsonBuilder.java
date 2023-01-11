package com.ossez.wechat.open.util.json;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.ossez.wechat.open.bean.WxOpenAuthorizerAccessToken;
import com.ossez.wechat.open.bean.WxOpenComponentAccessToken;
import com.ossez.wechat.open.bean.auth.WxOpenAuthorizationInfo;
import com.ossez.wechat.open.bean.auth.WxOpenAuthorizerInfo;
import com.ossez.wechat.open.bean.result.*;

import java.util.Objects;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenGsonBuilder {

  private static final GsonBuilder INSTANCE = new GsonBuilder();
  private static volatile Gson GSON_INSTANCE;

  static {
    INSTANCE.disableHtmlEscaping();
    INSTANCE.registerTypeAdapter(WxOpenComponentAccessToken.class, new WxOpenComponentAccessTokenGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerAccessToken.class, new WxOpenAuthorizerAccessTokenGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizationInfo.class, new WxOpenAuthorizationInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerInfo.class, new WxOpenAuthorizerInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenQueryAuthResult.class, new WxOpenQueryAuthResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerInfoResult.class, new WxOpenAuthorizerInfoResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerOptionResult.class, new WxOpenAuthorizerOptionResultGsonAdapter());
    INSTANCE.registerTypeAdapter(WxFastMaAccountBasicInfoResult.class, new WxFastMaAccountBasicInfoGsonAdapter());
    INSTANCE.registerTypeAdapter(WxOpenAuthorizerListResult.class, new WxOpenAuthorizerListResultGsonAdapter());
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
