package com.ossez.wechat.open.bean.ma;

import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Data
@EqualsAndHashCode(callSuper = true)
public class WxMaScheme extends WxOpenResult {
  private static final long serialVersionUID = 6099475183322341647L;

  private String openlink;
}
