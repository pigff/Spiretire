package com.fjrcloud.sciencepro.ui.activity;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.LeaderResponse;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * 介绍领导
 */
public class IntroLeaderActivity extends BaseRecyclerActivity<LeaderResponse.LeaderEntity.Leader> {

    private List<LeaderResponse.LeaderEntity.Leader> mLeaderItems;

    @Override
    protected void getData() {


        mAdapter.setNewData(mLeaderItems);
    }


    @Override
    protected String getToolbarTitle() {
        return "领导简介";
    }

    @Override
    protected BaseQuickAdapter<LeaderResponse.LeaderEntity.Leader, BaseViewHolder> getAdapter() {
        return new LeaderAdapter(R.layout.recycler_leader_card_item);
    }
    @Override
    public void initData() {
        mLeaderItems = new ArrayList<>();
        mLeaderItems.add(new LeaderResponse.LeaderEntity.Leader("张在铁", "http://img1.imgtn.bdimg.com/it/u=2583459808,3484382041&fm=23&gp=0.jpg"
                , "局长", "主持科技局全面工作", "0591-85266796", "fqkjj@sina.com"));
    }

    private class LeaderAdapter extends BaseQuickAdapter<LeaderResponse.LeaderEntity.Leader, BaseViewHolder> {

        LeaderAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, LeaderResponse.LeaderEntity.Leader item) {
            helper.setText(R.id.tv_name_leader_card, item.getName() + "    职务:  " + item.getDuty())
                    .setText(R.id.tv_duty_leader_card, "职能:  " + item.getDutyExplain())
                    .setText(R.id.tv_phone_leader_card, "电话:  " + item.getPhone())
                    .setText(R.id.tv_email_leader_card, "E-mail:" + item.getEmail());
            ImageView imageView = helper.getView(R.id.portrait_leader_card);
            ImgLoadUtils.loadUrl(mContext, item.getPhoto(), R.drawable.img_error, imageView);
        }
    }


}
