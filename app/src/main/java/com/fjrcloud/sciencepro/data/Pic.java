package com.fjrcloud.sciencepro.data;

/**
 * Created by greedy on 17/3/30.
 */

public class Pic {
    private String imgUrl;
    private boolean add;
    private boolean cancel;

    public Pic() {
        this.add = true;
        this.cancel = false;
    }

    public Pic(String imgUrl) {
        this.imgUrl = imgUrl;
        this.add = false;
        this.cancel = true;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public boolean isAdd() {
        return add;
    }

    public void setAdd(boolean add) {
        this.add = add;
    }

    public boolean isCancel() {
        return cancel;
    }

    public void setCancel(boolean cancel) {
        this.cancel = cancel;
    }

    public int getSpanSize() {
        return 1;
    }


}
