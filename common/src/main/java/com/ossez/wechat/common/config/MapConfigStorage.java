package com.ossez.wechat.common.config;

import com.ossez.wechat.common.config.DefaultConfigStorage;
import lombok.Data;
import com.ossez.wechat.common.model.WeChatAccessToken;

import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Brayden Wong
 * created on  2021/1/16
 * 提供accesstoken保存在concurrenthashmap中的实现，支持高并发。仅限于单机部署。
 */
public class MapConfigStorage extends DefaultConfigStorage {

  private static final long serialVersionUID = 5311395137835650104L;

  private static final ConcurrentHashMap<String, String> CONCURRENT_HASH_MAP = new ConcurrentHashMap<>(1);

  private static final String MAP_KEY = "access_token";


  @Override
  public String getAccessToken() {
    return CONCURRENT_HASH_MAP.get(MAP_KEY);
  }

  @Override
  public void setAccessToken(String accessToken) {
    CONCURRENT_HASH_MAP.put(MAP_KEY, accessToken);
  }

  @Override
  public void updateAccessToken(WeChatAccessToken accessToken) {
    updateAccessToken(accessToken.getAccessToken(), accessToken.getExpiresIn());
  }

  @Override
  public void updateAccessToken(String accessToken, int expiresInSeconds) {
    CONCURRENT_HASH_MAP.put(MAP_KEY, accessToken);
    this.expiresTime = System.currentTimeMillis() + (expiresInSeconds - 200) * 1000L;
  }


}
