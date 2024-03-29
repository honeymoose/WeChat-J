package com.ossez.wechat.oa.api.test;

import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.ossez.wechat.common.config.DefaultConfigStorage;
import org.apache.commons.lang3.builder.ToStringBuilder;

import java.util.concurrent.locks.Lock;

public class TestConfigStorage extends DefaultConfigStorage {

  private String openid;
  private String kfAccount;
  private String qrconnectRedirectUrl;
  private String templateId;
  private String keyPath;

  public String getKeyPath() {
    return keyPath;
  }

  public void setKeyPath(String keyPath) {
    this.keyPath = keyPath;
  }

  public String getOpenid() {
    return this.openid;
  }

  public void setOpenid(String openid) {
    this.openid = openid;
  }

  @Override
  public String toString() {
    return ToStringBuilder.reflectionToString(this);
  }

  public String getKfAccount() {
    return this.kfAccount;
  }

  public void setKfAccount(String kfAccount) {
    this.kfAccount = kfAccount;
  }

  public String getQrconnectRedirectUrl() {
    return this.qrconnectRedirectUrl;
  }

  public void setQrconnectRedirectUrl(String qrconnectRedirectUrl) {
    this.qrconnectRedirectUrl = qrconnectRedirectUrl;
  }

  @Override
  public String getTemplateId() {
    return this.templateId;
  }

  @Override
  public void setTemplateId(String templateId) {
    this.templateId = templateId;
  }

  public void setAccessTokenLock(Lock lock) {
    super.accessTokenLock = lock;
  }

}
