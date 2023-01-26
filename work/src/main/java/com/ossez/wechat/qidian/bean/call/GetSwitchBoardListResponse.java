package com.ossez.wechat.qidian.bean.call;

import com.ossez.wechat.common.util.json.WxGsonBuilder;
import com.ossez.wechat.qidian.bean.common.QidianResponse;
import lombok.Data;

@Data
public class GetSwitchBoardListResponse extends QidianResponse {
    private SwitchBoardList data;

    public static GetSwitchBoardListResponse fromJson(String result) {
        return WxGsonBuilder.create().fromJson(result, GetSwitchBoardListResponse.class);
    }
}
