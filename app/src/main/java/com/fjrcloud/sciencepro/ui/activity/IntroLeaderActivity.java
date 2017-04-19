package com.fjrcloud.sciencepro.ui.activity;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.LeaderResponse;
import com.fjrcloud.sciencepro.data.net.LeaderEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 介绍领导
 */
public class IntroLeaderActivity extends BaseRecyclerActivity<LeaderEntity> {


    @Override
    protected void getData() {
        addSubscription(ScieneManager.findLeaders(new SimpleSubscriber<>(getSimpleListener())));
    }

    @Override
    protected String getToolbarTitle() {
        return "领导简介";
    }

    @Override
    protected BaseQuickAdapter<LeaderEntity, BaseViewHolder> getAdapter() {
        return new LeaderAdapter(R.layout.recycler_leader_card_item);
    }

    private class LeaderAdapter extends BaseQuickAdapter<LeaderEntity, BaseViewHolder> {

        LeaderAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, LeaderEntity item) {
            helper.setText(R.id.tv_name_leader_card, item.getName() + "    职务:  " + item.getDutyName())
                    .setText(R.id.tv_duty_leader_card, "职能:  " + item.getDutyDescripte())
                    .setText(R.id.tv_phone_leader_card, "电话:  " + item.getPhoneNum());
//                    .setText(R.id.tv_email_leader_card, "E-mail:" + item.getEmail());
            ImageView imageView = helper.getView(R.id.portrait_leader_card);
            ImgLoadUtils.loadUrl(mContext, Constants.BASE_IMG_URL + item.getPhotoPath(), R.drawable.img_error, imageView);
        }
    }


}
