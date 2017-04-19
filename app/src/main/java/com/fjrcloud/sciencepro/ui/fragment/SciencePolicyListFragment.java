package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.data.net.WorkEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.activity.PolicyDetailedActivity;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;
import com.fjrcloud.sciencepro.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SciencePolicyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SciencePolicyListFragment extends BaseRecyclerFragment<WorkEntity> {

    private static final String ARG_PARAM1 = "param1";
    private int tabId;

    public SciencePolicyListFragment() {
    }


    public static SciencePolicyListFragment newInstance(int param1) {
        SciencePolicyListFragment fragment = new SciencePolicyListFragment();
        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabId = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(getActivity(), ((SciencePolicyResponse.SciencePolicy) adapter.getItem(position)).getTitle(), Toast.LENGTH_SHORT).show();
                openActivity(PolicyDetailedActivity.class);
            }
        });
    }

    @Override
    protected BaseQuickAdapter<WorkEntity, BaseViewHolder> getRecyclerAdapter() {
        return new SciencePolicyListAdapter(R.layout.recycler_science_policy_item);
    }

    @Override
    protected void getData() {

    }

    @Override
    protected void onLazyLoad() {
        addSubscription(ScieneManager.guideFindByDepartment(new SimpleSubscriber<>(getSimpleListener()), tabId, mPageNum, mPageSize));
    }

    private class SciencePolicyListAdapter extends BaseQuickAdapter<WorkEntity, BaseViewHolder> {

        SciencePolicyListAdapter(int layoutResId) {
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
