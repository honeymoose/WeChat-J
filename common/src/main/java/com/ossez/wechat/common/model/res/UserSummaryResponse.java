package com.ossez.wechat.common.model.res;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * UserSummaryResponse Object
 *
 * @author YuCheng Hu
 */
public class UserSummaryResponse {
    @JsonProperty("list")
    private List<UserData> userDataList;

    private static class UserData {
        @JsonProperty("ref_date")
        private String refDate;
        @JsonProperty("user_source")
        private Long userSource;

        @JsonProperty("new_user")
        private Long newUser;
        @JsonProperty("cancel_user")
        private Long cancelUser;

        @JsonProperty("cumulate_user")
        private Long cumulateUser;

        public String getRefDate() {
            return refDate;
        }

        public void setRefDate(String refDate) {
            this.refDate = refDate;
        }

        public Long getUserSource() {
            return userSource;
        }

        public void setUserSource(Long userSource) {
            this.userSource = userSource;
        }

        public Long getNewUser() {
            return newUser;
        }

        public void setNewUser(Long newUser) {
            this.newUser = newUser;
        }

        public Long getCancelUser() {
            return cancelUser;
        }

        public void setCancelUser(Long cancelUser) {
            this.cancelUser = cancelUser;
        }

        public Long getCumulateUser() {
            return cumulateUser;
        }

        public void setCumulateUser(Long cumulateUser) {
            this.cumulateUser = cumulateUser;
        }
    }

    public List<UserData> getUserDataList() {
        return userDataList;
    }

    public void setUserDataList(List<UserData> userDataList) {
        this.userDataList = userDataList;
    }
}
