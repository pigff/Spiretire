package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.activity.ScienceDynamicActivity;
import com.fjrcloud.sciencepro.ui.activity.SciencePolicyActivity;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

public class MsgNoticeFragment extends BaseRecyclerFragment<MsgNoticeFragment.MsgNoticeItem> {

    private List<MsgNoticeItem> mItems;


    public MsgNoticeFragment() {
        // Required empty public constructor
    }

    public static MsgNoticeFragment newInstance() {
        MsgNoticeFragment fragment = new MsgNoticeFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                MsgNoticeItem item = (MsgNoticeItem) adapter.getItem(position);
//                Toast.makeText(getActivity(), item.getName(), Toast.LENGTH_SHORT).show();
                switch (position) {
                    case 0:
//                        openActivity(GovernmentActivity.class);
                        break;
                    case 1:
                        openActivity(ScienceDynamicActivity.class);
                        break;
                    case 2:
                        openActivity(SciencePolicyActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }


    @Override
    protected BaseQuickAdapter<MsgNoticeItem, BaseViewHolder> getRecyclerAdapter() {
        return new MsgNoticeAdapter(R.layout.recycler_msg_notice_item);
    }

    @Override
    protected void getData() {
        mItems = new ArrayList<>();
        mItems.add(new MsgNoticeItem("政务公开", R.mipmap.icon_gover_msg, true, 0));
        mItems.add(new MsgNoticeItem("科技动态", R.mipmap.icon_dynamic_msg, true, 0));
        mItems.add(new MsgNoticeItem("科技政策", R.mipmap.icon_policy_msg, true, 0));
        mAdapter.setNewData(mItems);
    }

    private class MsgNoticeAdapter extends BaseQuickAdapter<MsgNoticeItem, BaseViewHolder> {

        MsgNoticeAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, MsgNoticeItem item) {
            helper.setImageResource(R.id.iv_msg_notice_item, item.getImgSrc())
                    .setText(R.id.name_msg_notice, item.getName())
                    .setText(R.id.tv_count_msg_notice, item.getMsgCount() + "条")
                    .addOnClickListener(R.id.group_msg_notice_item);
            if (item.isHasDynamic()) {
                helper.setTextColor(R.id.tv_dynamic_msg_notice, ContextCompat.getColor(mContext, R.color.orange_253_color_code));
            }
        }
    }

    class MsgNoticeItem {
        private String name;
        private int imgSrc;
        private boolean hasDynamic;
        private int msgCount;

        MsgNoticeItem(String name, int imgSrc, boolean hasDynamic, int msgCount) {
            this.name = name;
            this.imgSrc = imgSrc;
            this.hasDynamic = hasDynamic;
            this.msgCount = msgCount;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(int imgSrc) {
            this.imgSrc = imgSrc;
        }

        public boolean isHasDynamic() {
            return hasDynamic;
        }

        public void setHasDynamic(boolean hasDynamic) {
            this.hasDynamic = hasDynamic;
        }

        public int getMsgCount() {
            return msgCount;
        }

        public void setMsgCount(int msgCount) {
            this.msgCount = msgCount;
        }
    }
}
