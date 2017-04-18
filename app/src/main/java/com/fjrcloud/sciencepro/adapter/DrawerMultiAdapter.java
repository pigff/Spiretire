package com.fjrcloud.sciencepro.adapter;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.DrawerMulti;

import java.util.List;

/**
 * Created by lin on 2017/2/28.
 */

public class DrawerMultiAdapter extends BaseMultiItemQuickAdapter<DrawerMulti, BaseViewHolder> {

    public DrawerMultiAdapter(List<DrawerMulti> data) {
        super(data);
        addItemType(DrawerMulti.CATEGORY, R.layout.recycler_multi_drawer_name_item);
        addItemType(DrawerMulti.INDUSTRY, R.layout.recycler_multi_drawer_category_item);
        addItemType(DrawerMulti.DIVIDING, R.layout.recycler_multi_drawer_dividing_item);
        addItemType(DrawerMulti.NATURE, R.layout.recycler_multi_drawer_category_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, DrawerMulti item) {
        switch (helper.getItemViewType()) {
            case DrawerMulti.CATEGORY:
                helper.setText(R.id.tv_name_multi_drawer, item.getCategoryName());
                break;
            case DrawerMulti.NATURE:
            case DrawerMulti.INDUSTRY:
                DrawerMulti.Category category = item.getCategory();
                helper.setText(R.id.tv_category_name_multi_drawer, category.getName())
                        .addOnClickListener(R.id.tv_category_name_multi_drawer);
                if (category.isSelected()) {
                    helper.setBackgroundRes(R.id.tv_category_name_multi_drawer, R.drawable.pop_flesh_hole_group_bg)
                            .setTextColor(R.id.tv_category_name_multi_drawer, ContextCompat.getColor(mContext, R.color.white));
                } else {
                    helper.setBackgroundRes(R.id.tv_category_name_multi_drawer, R.drawable.pop_grey_hole_group_bg)
                            .setTextColor(R.id.tv_category_name_multi_drawer, ContextCompat.getColor(mContext, R.color.black_102_color_code));
                }
            default:
                break;
        }
    }
}
