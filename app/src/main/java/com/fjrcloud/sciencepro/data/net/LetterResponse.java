package com.fjrcloud.sciencepro.data.net;

import com.fjrcloud.sciencepro.data.BaseBean;

/**
 * Created by greedy on 17/3/29.
 */

public class LetterResponse extends BaseBean<LetterResponse.DataEntity> {

    public static class DataEntity {

        public static class LetterEntity {
            private String title;
            private String content;
            private String date;
            private int status;
            private String name;

            public LetterEntity() {
                this.title = "2017测试标题";
                this.content = "2017测试内容";
                this.date = "2017-11-1";
                this.status = 0;
                this.name = "福耀玻璃";
            }

            public LetterEntity(int a) {
                this.title = "2017测试标题";
                this.content = "2017测试内容";
                this.date = "2017-11-1";
                this.status = a;
                this.name = "福耀玻璃";
            }

            public String getName() {
                return name;
            }

            public void setName(String name) {
                this.name = name;
            }

            public String getTitle() {
                return title;
            }

            public void setTitle(String title) {
                this.title = title;
            }

            public String getContent() {
                return content;
            }

            public void setContent(String content) {
                this.content = content;
            }

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public int getStatus() {
                return status;
            }

            public void setStatus(int status) {
                this.status = status;
            }
        }
    }
}
