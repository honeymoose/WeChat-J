package com.ossez.wechat.wecom.bean.kf;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.contact.ExternalContact;
import com.ossez.wechat.wecom.util.json.WxCpGsonBuilder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import com.ossez.wechat.wecom.bean.WxCpBaseResp;

import java.util.List;

/**
 * The type Wx cp kf customer batch get resp.
 *
 * @author leiin  created on  2022/1/26 7:56 下午
 */
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@Data
public class WxCpKfCustomerBatchGetResp extends WxCpBaseResp {

  private static final long serialVersionUID = -3697709507605389887L;

  @SerializedName("customer_list")
  private List<ExternalContact> customerList;

  @SerializedName("invalid_external_userid")
  private List<String> invalidExternalUserId;

  /**
   * From json wx cp kf customer batch get resp.
   *
   * @param json the json
   * @return the wx cp kf customer batch get resp
   */
  public static WxCpKfCustomerBatchGetResp fromJson(String json) {
    return WxCpGsonBuilder.create().fromJson(json, WxCpKfCustomerBatchGetResp.class);
  }
}
