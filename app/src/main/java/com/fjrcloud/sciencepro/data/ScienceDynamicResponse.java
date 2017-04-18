package com.fjrcloud.sciencepro.data;

import java.io.Serializable;
import java.util.List;

/**
 * Created by lin on 2017/2/27.
 */

public class ScienceDynamicResponse extends BaseBean<ScienceDynamicResponse.ScienceDynamic>{

    public static class  ScienceDynamic implements Serializable{
        private String title;
        private String date;
        private List<String> imgUrls;
        private String content;

        public ScienceDynamic(String title, String date, List<String> imgUrls, String content) {
            this.title = title;
            this.date = date;
            this.imgUrls = imgUrls;
            this.content = content;
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

        public List<String> getImgUrls() {
            return imgUrls;
        }

        public void setImgUrls(List<String> imgUrls) {
            this.imgUrls = imgUrls;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }
    }
}
