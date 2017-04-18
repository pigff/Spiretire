package com.fjrcloud.sciencepro.data.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.data.AdResponse;
import com.fjrcloud.sciencepro.data.Category;
import com.fjrcloud.sciencepro.data.ScienceDynamicResponse;

import java.io.Serializable;
import java.util.List;
import java.util.Map;

/**
 * Created by lin on 2016/11/22.
 */
public class MainMulti implements MultiItemEntity, Serializable {

    public static final int BANNER = 1;
    public static final int CATEGORY = 2;
    public static final int NEWS_RIGHT = 3;
    public static final int DIVIDING = 4;
    public static final int TEXT_MORE = 5;
    public static final int GRID = 6;
    public static final int FILP = 7;

    public static final int CATEGORY_SIZE = 1;
    public static final int NORMAL_SIZE = 4;

    private Category category;
    private List<AdResponse.Ad> ads;
    private Map<String, Integer> map;
    private ScienceDynamicResponse.ScienceDynamic news;
    private String content;

    private int itemType;
    private int spanSize;


    public MainMulti(int spanSize, String content, int itemType) {
        this.spanSize = spanSize;
        this.content = content;
        this.itemType = itemType;
    }

    public MainMulti(int spanSize, Category category, int itemType) {
        this.spanSize = spanSize;
        this.category = category;
        this.itemType = itemType;
    }

    public MainMulti(int spanSize, ScienceDynamicResponse.ScienceDynamic news, int itemType) {
        this.spanSize = spanSize;
        this.news = news;
        this.itemType = itemType;
    }

    public MainMulti(int spanSize, List<AdResponse.Ad> ads, int itemType) {
        this.spanSize = spanSize;
        this.ads = ads;
        this.itemType = itemType;
    }

    public MainMulti(int spanSize, int itemType, Map<String, Integer> map) {
        this.spanSize = spanSize;
        this.itemType = itemType;
        this.map = map;
    }

    public MainMulti(int spanSize, int itemType) {
        this.spanSize = spanSize;
        this.itemType = itemType;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<AdResponse.Ad> getAds() {
        return ads;
    }

    public void setAds(List<AdResponse.Ad> ads) {
        this.ads = ads;
    }

    public ScienceDynamicResponse.ScienceDynamic getNews() {
        return news;
    }

    public void setNews(ScienceDynamicResponse.ScienceDynamic news) {
        this.news = news;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public Map<String, Integer> getMap() {
        return map;
    }

    public void setMap(Map<String, Integer> map) {
        this.map = map;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

}
