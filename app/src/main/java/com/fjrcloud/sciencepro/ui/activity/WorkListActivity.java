package com.fjrcloud.sciencepro.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.data.net.TypeEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;

/**
 * 办事指南每个 类别的列表
 */
public class WorkListActivity extends BaseRecyclerActivity<WorkEntity> {

    private TypeEntity entity;

    @Override
    public void initData() {
        super.initData();
        Intent intent = getIntent();
        entity = (TypeEntity) intent.getSerializableExtra(Constants.PARAM);
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                WorkEntity workEntity = (WorkEntity) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.DATA, workEntity);
                openActivity(WorkDetailedActivity.class, bundle);
            }
        });
    }

    @Override
    protected String getToolbarTitle() {
        return entity.getName();
    }

    @Override
    protected BaseQuickAdapter<WorkEntity, BaseViewHolder> getAdapter() {
        return new WorkListAdapter(R.layout.recycler_science_policy_item);
    }

    @Override
    protected void getData() {
        addSubscription(ScieneManager.guideFindByDepartment(new SimpleSubscriber<>(getSimpleListener()), entity.getId(), mPageNum, mPageSize));
    }

    public static Intent newInstance(Context context, TypeEntity title) {
        Intent intent = new Intent(context, WorkListActivity.class);
        intent.putExtra(Constants.PARAM, title);
        return intent;
    }

    private class WorkListAdapter extends BaseQuickAdapter<WorkEntity, BaseViewHolder> {
        WorkListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkEntity item) {
            helper.setText(R.id.tv_title_science_policy, item.getName())
                    .setText(R.id.tv_date_science_policy, DateUtil.getDateToString(item.getCreateTime()))
                    .addOnClickListener(R.id.science_policy_group);
        }
    }
}
