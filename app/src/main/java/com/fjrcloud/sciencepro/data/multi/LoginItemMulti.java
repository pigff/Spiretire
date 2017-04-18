package com.fjrcloud.sciencepro.data.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by greedy on 17/4/1.
 */

public class LoginItemMulti implements MultiItemEntity {

    private int itemType;
    private String prompt;
    private String name;
    private boolean upload;
    private String tag;

    public LoginItemMulti(String tag, int itemType, String prompt, String name) {
        this(tag, itemType, prompt, name, false);
    }

    public LoginItemMulti(String tag, int itemType, String prompt, String name, boolean upload) {
        this.tag = tag;
        this.itemType = itemType;
        this.prompt = prompt;
        this.name = name;
        this.upload = upload;
    }

    public LoginItemMulti() {
    }

    public String getPrompt() {
        return prompt;
    }

    public void setPrompt(String prompt) {
        this.prompt = prompt;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public boolean isUpload() {
        return upload;
    }

    public void setUpload(boolean upload) {
        this.upload = upload;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public static LoginItemMulti testData(String tag, int itemType) {
        return testData(tag, itemType, false);
    }

    public static LoginItemMulti testData(int itemType) {
        return testData("", itemType, false);
    }

    public static LoginItemMulti testData(String tag, int itemType, boolean upload) {
        LoginItemMulti itemMulti = new LoginItemMulti();
        itemMulti.setName("测试:");
        itemMulti.setPrompt("测试");
        itemMulti.setItemType(itemType);
        itemMulti.setTag(tag);
        itemMulti.setUpload(upload);
        return itemMulti;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
