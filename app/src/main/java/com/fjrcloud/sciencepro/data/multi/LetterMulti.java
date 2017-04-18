package com.fjrcloud.sciencepro.data.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.adapter.LetterMultiAdapter;

/**
 * Created by greedy on 17/3/30.
 */

public class LetterMulti implements MultiItemEntity {

    private int itemType;

    private boolean show;

    public LetterMulti(int itemType) {
        this(itemType, false);
    }

    public LetterMulti(int itemType, boolean show) {
        this.itemType = itemType;
        this.show = show;
    }

    public int getSpanSize() {
        return (itemType == LetterMultiAdapter.IMG ? 1 : 3);
    }

    public boolean isShow() {
        return show;
    }

    @Override
    public int getItemType() {
        return itemType;
    }
}
