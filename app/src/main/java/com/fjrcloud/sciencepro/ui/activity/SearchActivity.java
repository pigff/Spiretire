package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

/**
 * 搜索
 */
public class SearchActivity extends BaseToolbarActivity {

    @Override
    protected int provideContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        setTitle("搜索");
        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.rv_search);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
    }

    @Override
    public void initListener() {

    }
}
