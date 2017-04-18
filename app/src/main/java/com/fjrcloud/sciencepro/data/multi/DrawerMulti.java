package com.fjrcloud.sciencepro.data.multi;

import com.chad.library.adapter.base.entity.MultiItemEntity;

/**
 * Created by lin on 2017/2/28.
 */

public class DrawerMulti implements MultiItemEntity {

    public static final int CATEGORY = 0;
    public static final int INDUSTRY = 1;
    public static final int NATURE = 2;
    public static final int DIVIDING = 3;

    public static final int NORMAL_SIZE = 3;
    public static final int CATEGORY_SIZE = 1;

    private int spanSize;
    private int itemType;

    private String categoryName;
    private Category category;

    public DrawerMulti(int spanSize, int itemType, String categoryName) {
        this.spanSize = spanSize;
        this.itemType = itemType;
        this.categoryName = categoryName;
    }

    public DrawerMulti(int spanSize, int itemType, Category category) {
        this.spanSize = spanSize;
        this.itemType = itemType;
        this.category = category;
    }

    public DrawerMulti(int spanSize, int itemType) {
        this.spanSize = spanSize;
        this.itemType = itemType;
    }

    public int getSpanSize() {
        return spanSize;
    }

    public void setSpanSize(int spanSize) {
        this.spanSize = spanSize;
    }

    public void setItemType(int itemType) {
        this.itemType = itemType;
    }

    public String getCategoryName() {
        return categoryName;
    }

    public void setCategoryName(String categoryName) {
        this.categoryName = categoryName;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    @Override
    public int getItemType() {
        return itemType;
    }

    public static class Category {
        private String name;
        private boolean isSelected;

        public Category(String name, boolean isSelected) {
            this.name = name;
            this.isSelected = isSelected;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public boolean isSelected() {
            return isSelected;
        }

        public void setSelected(boolean selected) {
            isSelected = selected;
        }

        @Override
        public String toString() {
            return "Category{" +
                    "name='" + name + '\'' +
                    ", isSelected=" + isSelected +
                    '}';
        }
    }
}
