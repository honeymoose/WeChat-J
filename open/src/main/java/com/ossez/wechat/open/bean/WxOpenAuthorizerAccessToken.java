package com.ossez.wechat.open.bean;

import com.ossez.wechat.open.util.json.WxOpenGsonBuilder;
import lombok.Data;

import java.io.Serializable;

/**
 * @author <a href="https://github.com/007gzs">007</a>
 */
@Data
public class WxOpenAuthorizerAccessToken implements Serializable {
  private static final long serialVersionUID = -4069745419280727420L;

  private String authorizerAccessToken;

  private String authorizerRefreshToken;

  private int expiresIn = -1;

  public static WxOpenAuthorizerAccessToken fromJson(String json) {
    return WxOpenGsonBuilder.create().fromJson(json, WxOpenAuthorizerAccessToken.class);
  }
}
