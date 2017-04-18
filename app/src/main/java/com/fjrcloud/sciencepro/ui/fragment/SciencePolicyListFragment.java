package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.ui.activity.PolicyDetailedActivity;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link SciencePolicyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class SciencePolicyListFragment extends BaseRecyclerFragment<SciencePolicyResponse.SciencePolicy> {

    private static final String ARG_PARAM1 = "param1";


    private String tabName;
    private List<SciencePolicyResponse.SciencePolicy> mSciencePolicies;


    public SciencePolicyListFragment() {
    }


    public static SciencePolicyListFragment newInstance(String param1) {
        SciencePolicyListFragment fragment = new SciencePolicyListFragment();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            tabName = getArguments().getString(ARG_PARAM1);
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
    protected BaseQuickAdapter<SciencePolicyResponse.SciencePolicy, BaseViewHolder> getRecyclerAdapter() {
        return new SciencePolicyListAdapter(R.layout.recycler_science_policy_item, mSciencePolicies);
    }

    @Override
    protected void getData() {
        mSciencePolicies = new ArrayList<>();
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("关于征集2017年全市性创新创业活动项目的通知", "2017-03-01"));
        mAdapter.setNewData(mSciencePolicies);
    }

    private class SciencePolicyListAdapter extends BaseQuickAdapter<SciencePolicyResponse.SciencePolicy, BaseViewHolder> {

        SciencePolicyListAdapter(int layoutResId, List<SciencePolicyResponse.SciencePolicy> data) {
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
