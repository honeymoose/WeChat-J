package com.ossez.wechat.open.test;

import com.ossez.wechat.open.api.impl.WxOpenInMemoryConfigStorage;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@XStreamAlias("xml")
public class TestConfigStorage extends WxOpenInMemoryConfigStorage {

  private String testMpAppId;

  private String testMaAppId;
}
