/**
 * 对公众平台发送给公众账号的消息加解密示例代码.
 *
 * @copyright Copyright (c) 1998-2014 Tencent Inc.
 * <p>
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */

// ------------------------------------------------------------------------

/**
 * 针对org.apache.commons.codec.binary.Base64，
 * 需要导入架包commons-codec-1.9（或commons-codec-1.8等其他版本）
 * 官方下载地址：http://commons.apache.org/proper/commons-codec/download_codec.cgi
 */
package com.ossez.wechat.oa.util.crypto;

import com.ossez.wechat.common.util.crypto.WxCryptUtil;
import com.ossez.wechat.common.config.ConfigStorage;
import org.apache.commons.lang3.StringUtils;

import java.util.Base64;

public class WxMpCryptUtil extends WxCryptUtil {

  /**
   * 构造函数
   *
   * @param configStorage
   */
  public WxMpCryptUtil(ConfigStorage configStorage) {
    /*
     * @param token          公众平台上，开发者设置的token
     * @param encodingAesKey 公众平台上，开发者设置的EncodingAESKey
     * @param appId          公众平台appid
     */
    String encodingAesKey = configStorage.getAesKey();
    String token = configStorage.getToken();
    String appId = configStorage.getAppId();

    this.token = token;
    this.appidOrCorpid = appId;
    this.aesKey = Base64.getDecoder().decode(StringUtils.remove(encodingAesKey, " "));
  }

}
