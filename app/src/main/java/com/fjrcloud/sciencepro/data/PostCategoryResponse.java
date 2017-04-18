package com.fjrcloud.sciencepro.data;

import java.io.Serializable;

/**
 * Created by lin on 2017/2/27.
 */

public class PostCategoryResponse extends BaseBean<PostCategoryResponse.PostCategory>{

    public static class PostCategory implements Serializable{
        private String name;

        public PostCategory(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
