package com.ossez.wechat.open.bean.ma;

import lombok.Data;

import java.io.Serializable;

/**
 * @author yqx
 * created on  2018/9/13
 */
@Data
public class WxMaOpenNetworkTimeout implements Serializable {

  private Integer request;

  private Integer connectSocket;

  private Integer uploadFile;

  private Integer downloadFile;
}
