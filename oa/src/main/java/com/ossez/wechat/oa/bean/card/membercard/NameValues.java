package com.ossez.wechat.oa.bean.card.membercard;

import lombok.Data;

import java.io.Serializable;

/**
 *
 * @author YuJian
 * created on  2017/7/11
 */
@Data
public class NameValues implements Serializable{
  private static final long serialVersionUID = -8529369702944594330L;

  private String name;

  private String value;

  private String[] valueList;

}
