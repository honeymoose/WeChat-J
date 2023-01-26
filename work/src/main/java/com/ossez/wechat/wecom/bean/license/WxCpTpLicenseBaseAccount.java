package com.ossez.wechat.wecom.bean.license;

import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.io.Serializable;

/**
 * 许可证账号基础类
 *
 * @author Totoro  created on  2022/6/27 14:39
 */
@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class WxCpTpLicenseBaseAccount implements Serializable {
  private static final long serialVersionUID = 7075253491688740047L;


  /**
   * 用户ID
   */
  private String userid;

  /**
   * 类型
   */
  private Integer type;


  /**
   * To json string.
   *
   * @return the string
   */
  public String toJson() {
    return WxCpGsonBuilder.create().toJson(this);
  }
}
