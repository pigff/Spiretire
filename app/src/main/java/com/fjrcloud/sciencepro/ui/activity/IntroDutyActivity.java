package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

/**
 * 介绍职务
 */
public class IntroDutyActivity extends BaseToolbarActivity {

    private TextView mContentTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {

    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_duty;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        setTitle("机构职责");
        mContentTv = (TextView) findViewById(R.id.tv_content_duty);
        mContentTv.setText(R.string.first);
    }

    @Override
    public void initListener() {

    }
}
