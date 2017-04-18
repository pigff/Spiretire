package com.fjrcloud.sciencepro.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 文件下载
 */
public class DownloadFileActivity extends BaseToolbarActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private LinearLayout mSearchLayout;
    private List<SciencePolicyResponse.SciencePolicy> mSciencePolicies;
    private DownloadFIleListAdapter mAdapter;


    @Override
    protected int provideContentView() {
        return R.layout.activity_download_file;
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                SciencePolicyResponse.SciencePolicy policy = (SciencePolicyResponse.SciencePolicy) adapter.getItem(position);
                Toast.makeText(DownloadFileActivity.this, policy.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initView() {
        setTitle("文件下载");

        mRecyclerView = (RecyclerView) findViewById(R.id.rv_download_file);
        mSearchLayout = (LinearLayout) findViewById(R.id.fake_search_group);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DownloadFileActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mSearchLayout.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {
        mAdapter = new DownloadFIleListAdapter(R.layout.recycler_science_policy_item, mSciencePolicies);
    }

    @Override
    public void initData() {
        mSciencePolicies = new ArrayList<>();
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("科学发展观活动征求意见表", "2017-2-19"));
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("福清市企业知识产权情况调查表", "2017-2-19"));
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("福清市科技计划项目合同书", "2017-2-19"));
        mSciencePolicies.add(new SciencePolicyResponse.SciencePolicy("福清市科技计划项目申报系统", "2017-2-19"));
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fake_search_group:
                Intent intent2Search = new Intent(DownloadFileActivity.this, SearchActivity.class);
                startActivity(intent2Search);
                break;
            default:
                break;
        }
    }

    private class DownloadFIleListAdapter extends BaseQuickAdapter<SciencePolicyResponse.SciencePolicy, BaseViewHolder> {
        DownloadFIleListAdapter(int layoutResId, List<SciencePolicyResponse.SciencePolicy> data) {
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
