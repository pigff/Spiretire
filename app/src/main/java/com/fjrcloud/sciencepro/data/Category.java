package com.fjrcloud.sciencepro.data;

/**
 * Created by lin on 2017/2/24.
 */

public class Category {

    private String content;
    private int imgSrc;

    public Category(String content, int imgSrc) {
        this.content = content;
        this.imgSrc = imgSrc;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getImgSrc() {
        return imgSrc;
    }

    public void setImgSrc(int imgSrc) {
        this.imgSrc = imgSrc;
    }
}
