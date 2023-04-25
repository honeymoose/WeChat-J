package com.ossez.wechat.common.model.req;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.ossez.wechat.common.model.res.DataCubeArticle;

import java.io.Serializable;
import java.util.List;

/**
 * CustomMessage
 *
 * @author YuCheng
 */
public class MenuRequest implements Serializable {
    private static final long serialVersionUID = -9196732086954365246L;

    @JsonProperty(value = "button", required = true)
    private List<Button> buttonList;

    public static class Button {
        @JsonProperty(value = "sub_button")
        private List<Button> subButtonList;
        @JsonProperty("type")
        private String type;
        @JsonProperty("name")
        private String name;
        @JsonProperty("key")
        private String key;

        @JsonProperty("url")
        private String url;

        public List<Button> getSubButtonList() {
            return subButtonList;
        }

        public void setSubButtonList(List<Button> subButtonList) {
            this.subButtonList = subButtonList;
        }

        public String getType() {
            return type;
        }

        public void setType(String type) {
            this.type = type;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }

    public List<Button> getButtonList() {
        return buttonList;
    }

    public void setButtonList(List<Button> buttonList) {
        this.buttonList = buttonList;
    }
}
