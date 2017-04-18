package com.fjrcloud.sciencepro.data;

import java.io.Serializable;

/**
 * Created by lin on 2017/2/24.
 */

public class AdResponse extends BaseBean {

    public static class Ad implements Serializable {
        private String content;
        private String imgUrl;

        public Ad(String content, String imgUrl) {
            this.content = content;
            this.imgUrl = imgUrl;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getImgUrl() {
            return imgUrl;
        }

        public void setImgUrl(String imgUrl) {
            this.imgUrl = imgUrl;
        }
    }
}
