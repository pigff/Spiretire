package com.fjrcloud.sciencepro.ui.activity;

import android.content.Intent;
import android.view.View;
import android.widget.LinearLayout;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

/**
 * 科技局
 */
public class ScienceActivity extends BaseToolbarActivity implements View.OnClickListener{

    private LinearLayout mDutyGroup;
    private LinearLayout mInnerGroup;
    private LinearLayout mLeaderGroup;

    @Override
    protected int provideContentView() {
        return R.layout.activity_science;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.linear_duty_group:
                Intent intent2Duty = new Intent(ScienceActivity.this, IntroDutyActivity.class);
                startActivity(intent2Duty);
                break;
            case R.id.linear_leader_group:
                Intent intent2Leader = new Intent(ScienceActivity.this, IntroLeaderActivity.class);
                startActivity(intent2Leader);
                break;
            case R.id.linear_inner_group:
                Intent intent2Inner = new Intent(ScienceActivity.this, IntroAgenceActivity.class);
                startActivity(intent2Inner);
                break;
            default:
                break;
        }
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        setTitle("科技局");
        mDutyGroup = (LinearLayout) findViewById(R.id.linear_duty_group);
        mLeaderGroup = (LinearLayout) findViewById(R.id.linear_leader_group);
        mInnerGroup = (LinearLayout) findViewById(R.id.linear_inner_group);
    }

    @Override
    public void initListener() {
        mDutyGroup.setOnClickListener(this);
        mLeaderGroup.setOnClickListener(this);
        mInnerGroup.setOnClickListener(this);
    }
}
