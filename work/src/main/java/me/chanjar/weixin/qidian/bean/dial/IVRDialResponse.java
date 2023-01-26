package me.chanjar.weixin.qidian.bean.dial;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import lombok.Data;
import me.chanjar.weixin.qidian.bean.common.QidianResponse;
import me.chanjar.weixin.qidian.util.json.WxQidianGsonBuilder;

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
