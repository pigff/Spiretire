package com.fjrcloud.sciencepro.data;

/**
 * Created by lin on 2017/2/27.
 */

public class SciencePolicyResponse extends BaseBean<SciencePolicyResponse.SciencePolicy> {

    public static class SciencePolicy {
        private String title;
        private String date;

        public SciencePolicy(String title, String date) {
            this.title = title;
            this.date = date;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
