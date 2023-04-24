package com.ossez.wechat.common.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * UserSummaryResponse Object
 *
 * @author YuCheng Hu
 */
public class DataCubeArticle {
    @JsonProperty("list")
    private List<ArticleData> articleDataList;

    public static class ArticleData {
        @JsonProperty("ref_date")
        private String refDate;

        @JsonProperty("msgid")
        private String msgId;
        @JsonProperty("title")
        private String title;
        @JsonProperty("int_page_read_user")
        private Long intPageReadUser;
        @JsonProperty("ori_page_read_user")
        private Long oriPageReadUser;
        @JsonProperty("ori_page_read_count")
        private Long oriPageReadCount;
        @JsonProperty("share_user")
        private Long shareUser;

        @JsonProperty("shareCount")
        private Long shareCount;

        @JsonProperty("add_to_fav_user")
        private Long addToFavUser;

        @JsonProperty("add_to_fav_count")
        private Long addToFavCount;

        public String getRefDate() {
            return refDate;
        }

        public void setRefDate(String refDate) {
            this.refDate = refDate;
        }

        public String getMsgId() {
            return msgId;
        }

        public void setMsgId(String msgId) {
            this.msgId = msgId;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Long getIntPageReadUser() {
            return intPageReadUser;
        }

        public void setIntPageReadUser(Long intPageReadUser) {
            this.intPageReadUser = intPageReadUser;
        }

        public Long getOriPageReadUser() {
            return oriPageReadUser;
        }

        public void setOriPageReadUser(Long oriPageReadUser) {
            this.oriPageReadUser = oriPageReadUser;
        }

        public Long getOriPageReadCount() {
            return oriPageReadCount;
        }

        public void setOriPageReadCount(Long oriPageReadCount) {
            this.oriPageReadCount = oriPageReadCount;
        }

        public Long getShareUser() {
            return shareUser;
        }

        public void setShareUser(Long shareUser) {
            this.shareUser = shareUser;
        }

        public Long getShareCount() {
            return shareCount;
        }

        public void setShareCount(Long shareCount) {
            this.shareCount = shareCount;
        }

        public Long getAddToFavUser() {
            return addToFavUser;
        }

        public void setAddToFavUser(Long addToFavUser) {
            this.addToFavUser = addToFavUser;
        }

        public Long getAddToFavCount() {
            return addToFavCount;
        }

        public void setAddToFavCount(Long addToFavCount) {
            this.addToFavCount = addToFavCount;
        }
    }

    public List<ArticleData> getArticleDataList() {
        return articleDataList;
    }

    public void setArticleDataList(List<ArticleData> articleDataList) {
        this.articleDataList = articleDataList;
    }
}
