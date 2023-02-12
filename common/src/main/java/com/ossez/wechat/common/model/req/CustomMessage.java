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
public class CustomMessage implements Serializable {
    private static final long serialVersionUID = -9196732086954365246L;

    @JsonProperty("touser")
    private String toUser;

    @JsonProperty("msgtype")
    private String msgType;

    @JsonProperty("text")
    private KfText text;

    @JsonProperty("image")
    private KfImage image;

    @JsonProperty("link")
    private KfLink link;

    @JsonProperty("miniprogrampage")
    private KfMaPage maPage;

    @Data
    @AllArgsConstructor
    public static class KfText implements Serializable {
        private static final long serialVersionUID = 151122958720941270L;

        private String content;
    }

    @Data
    @AllArgsConstructor
    public static class KfImage implements Serializable {
        private static final long serialVersionUID = -5409342945117300782L;

        @SerializedName("media_id")
        private String mediaId;
    }

    @Data
    @Builder
    public static class KfLink implements Serializable {
        private static final long serialVersionUID = -6728776817556127413L;

        private String title;
        private String description;
        private String url;

        @SerializedName("thumb_url")
        private String thumbUrl;
    }

    @Data
    @Builder
    public static class KfMaPage implements Serializable {
        private static final long serialVersionUID = -5633492281871634466L;

        private String title;

        @SerializedName("pagepath")
        private String pagePath;

        @SerializedName("thumb_media_id")
        private String thumbMediaId;
    }

    public String getToUser() {
        return toUser;
    }

    public void setToUser(String toUser) {
        this.toUser = toUser;
    }

    public String getMsgType() {
        return msgType;
    }

    public void setMsgType(String msgType) {
        this.msgType = msgType;
    }

    public KfText getText() {
        return text;
    }

    public void setText(KfText text) {
        this.text = text;
    }

    public KfImage getImage() {
        return image;
    }

    public void setImage(KfImage image) {
        this.image = image;
    }

    public KfLink getLink() {
        return link;
    }

    public void setLink(KfLink link) {
        this.link = link;
    }

    public KfMaPage getMaPage() {
        return maPage;
    }

    public void setMaPage(KfMaPage maPage) {
        this.maPage = maPage;
    }
}
