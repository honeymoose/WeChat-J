package com.ossez.wechat.qidian.bean.dial;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.qidian.bean.common.QidianResponse;
import com.ossez.wechat.qidian.util.json.WxQidianGsonBuilder;
import lombok.Data;

@Data
public class IVRDialResponse extends QidianResponse {
    private String callid;

    public static IVRDialResponse fromJson(String json) {
        return WxGsonBuilder.create().fromJson(json, IVRDialResponse.class);
    }

    @Override
    public String toString() {
        return WxQidianGsonBuilder.create().toJson(this);
    }
}
