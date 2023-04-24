package com.ossez.wechat.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.gson.annotations.SerializedName;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.io.Serializable;

/**
 * CustomMessage
 *
 * @author YuCheng
 */
public class DataCubeRequest implements Serializable {
    private static final long serialVersionUID = -9196732086954365246L;

    @JsonProperty(value = "begin_date", required = true)
    private String beginDate;

    @JsonProperty(value = "end_date", required = true)
    private String endDate;

    public String getBeginDate() {
        return beginDate;
    }

    public void setBeginDate(String beginDate) {
        this.beginDate = beginDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }
}
