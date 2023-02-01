package com.ossez.wechat.common.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ossez.wechat.common.api.WxMessageInMemoryDuplicateCheckerSingleton;
import org.apache.commons.lang3.StringUtils;

/**
 * WeChatAccessToken Response Object
 *
 * @author YuCheng Hu
 */
public class NetworkCheckResponse {
    @JsonProperty("dns")
    private String dns;
    @JsonProperty("ping")
    private Ping ping;
    
    private static class dns {
        @JsonProperty("ip")
        private String ip;
        @JsonProperty("real_operator")
        private String realOperator;
    }

    private static class Ping {
        @JsonProperty("ip")
        private String ip;
        @JsonProperty("from_operator")
        private String fromOperator;
        @JsonProperty("package_loss")
        private String packageLoss;
        @JsonProperty("time")
        private String time;
    }
}
