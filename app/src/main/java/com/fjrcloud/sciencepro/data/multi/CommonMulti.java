package com.fjrcloud.sciencepro.data.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by greedy on 17/4/1.
 */

public class CommonMulti implements MultiItemEntity {

    private int itemType;

    private String other;

    public CommonMulti(int itemType) {
        this.itemType = itemType;
    }

    public CommonMulti(int itemType, String other) {
        this.itemType = itemType;
        this.other = other;
    }

    public String getOther() {
        return other;
    }

    public void setOther(String other) {
        this.other = other;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
