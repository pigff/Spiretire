package com.fjrcloud.sciencepro.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 办事指南每个 类别的列表
 */
public class WorkListActivity extends BaseRecyclerActivity<SciencePolicyResponse.SciencePolicy> {

    private List<SciencePolicyResponse.SciencePolicy> mSciencePolicies;

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SciencePolicyResponse.SciencePolicy policy = (SciencePolicyResponse.SciencePolicy) adapter.getItem(position);
                Intent intent2Detailed = new Intent(WorkListActivity.this, WorkDetailedActivity.class);
                startActivity(intent2Detailed);
            }
        });
    }

    @Override
    protected String getToolbarTitle() {
        Intent intent = getIntent();
        return intent.getStringExtra(Constants.PARAM);
    }

    @Override
    protected BaseQuickAdapter<SciencePolicyResponse.SciencePolicy, BaseViewHolder> getAdapter() {
        return new WorkListAdapter(R.layout.recycler_science_policy_item, mSciencePolicies);
    }

    @Override
    protected void getData() {
        mSciencePolicies = new ArrayList<>();
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("假冒专利", "2013-09-09"));
        mAdapter.setNewData(mSciencePolicies);
    }

    public static Intent newInstance(Context context, String title) {
        Intent intent = new Intent(context, WorkListActivity.class);
        intent.putExtra(Constants.PARAM, title);
        return intent;
    }

    private class WorkListAdapter extends BaseQuickAdapter<SciencePolicyResponse.SciencePolicy, BaseViewHolder> {
        WorkListAdapter(int layoutResId, List<SciencePolicyResponse.SciencePolicy> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, SciencePolicyResponse.SciencePolicy item) {
            helper.setText(R.id.tv_title_science_policy, item.getTitle())
                    .setText(R.id.tv_date_science_policy, item.getDate())
                    .addOnClickListener(R.id.science_policy_group);
        }
    }
}
