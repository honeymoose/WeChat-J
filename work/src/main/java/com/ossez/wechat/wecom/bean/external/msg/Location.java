package com.ossez.wechat.wecom.bean.external.msg;

import lombok.Data;

/**
 * 地理位置
 *
 * @author leiin  created on  2021-10-29
 */
@Data
public class Location {
  private String latitude;
  private String longitude;
  private String name;
}
