package com.ossez.wechat.open.util;

import com.ossez.wechat.common.util.crypto.WxCryptUtil;
import com.ossez.wechat.open.api.WxOpenConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
public class WxOpenCryptUtil extends WxCryptUtil {
  /**
   * 构造函数
   *
   * @param wxOpenConfigStorage
   */
  public WxOpenCryptUtil(WxOpenConfigStorage wxOpenConfigStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = wxOpenConfigStorage.getComponentAesKey();
    String token = wxOpenConfigStorage.getComponentToken();
    String appId = wxOpenConfigStorage.getComponentAppId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = Base64.getDecoder().decode(StringUtils.remove(encodingAesKey, " "));
  }
}
