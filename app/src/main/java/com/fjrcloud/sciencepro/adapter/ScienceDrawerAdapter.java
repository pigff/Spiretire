package com.fjrcloud.sciencepro.adapter;

import android.support.annotation.DrawableRes;
import android.support.annotation.StringRes;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;

import java.util.List;

/**
 * Created by 林圣坚 on 2016/1/29.
 */
public class ScienceDrawerAdapter extends BaseQuickAdapter<ScienceDrawerAdapter.Item, BaseViewHolder> {

    public ScienceDrawerAdapter(int layoutResId, List<Item> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, Item item) {
        helper.setImageResource(R.id.iv_navigation_item, item.img)
                .setText(R.id.title_navigation_item, item.title)
                .addOnClickListener(R.id.group_rv_navigation_item);
    }

    public enum Item {
        COURIER(R.mipmap.icon_avatar, R.string.set_avatar_navigation),
        VISITORS(R.mipmap.icon_modify_psw, R.string.modify_psw_navigation),
        MONITORING(R.mipmap.icon_msg_notice, R.string.msg_notice_navigation),
        VideoSquare(R.mipmap.icon_company_msg, R.string.company_msg_navigation),
        SmartLife(R.mipmap.icon_company_list, R.string.company_directory_navigation);

        public
        @DrawableRes
        int img;
        public
        @StringRes
        int title;

        Item(int img, int title) {
            this.img = img;
            this.title = title;
        }
    }
}
