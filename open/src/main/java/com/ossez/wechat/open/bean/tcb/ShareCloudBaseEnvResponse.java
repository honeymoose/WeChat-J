package com.ossez.wechat.open.bean.tcb;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.open.bean.result.WxOpenResult;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
@Builder
public class ShareCloudBaseEnvResponse extends WxOpenResult implements Serializable {
  private static final long serialVersionUID = 316444074975118997L;

  @SerializedName("err_list")
  private List<ErrListDTO> errList;

  @Data
  public static class ErrListDTO implements Serializable {
    @SerializedName("env")
    private String env;
    @SerializedName("appid")
    private String appid;
    @SerializedName("errmsg")
    private String errmsg;
  }
}
