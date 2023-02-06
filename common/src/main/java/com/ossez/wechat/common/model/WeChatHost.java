package com.ossez.wechat.common.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class WeChatHost implements Serializable {
    private static final long serialVersionUID = -7648920647310280817L;
    private String apiHost;
    private String openHost;
    private String mpHost;

}
