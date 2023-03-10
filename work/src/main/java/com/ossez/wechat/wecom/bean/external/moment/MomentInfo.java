package com.ossez.wechat.wecom.bean.external.moment;

import com.google.gson.annotations.SerializedName;
import com.ossez.wechat.wecom.bean.external.msg.*;
import lombok.Data;

import java.util.List;

/**
 * The type Moment info.
 *
 * @author Borisg
 */
@Data
public class MomentInfo {
    @SerializedName("moment_id")
    private String momentId;
    @SerializedName("creator")
    private String creator;
    @SerializedName("create_time")
    private String createTime;
    @SerializedName("create_type")
    private Integer createType;
    @SerializedName("visible_type")
    private Integer visibleType;
    private Text text;
    private List<Image> image;
    private Video video;
    private Link link;
    private Location location;
}
