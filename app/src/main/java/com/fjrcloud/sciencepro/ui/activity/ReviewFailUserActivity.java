package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

/**
 * 审核失败
 */
public class ReviewFailUserActivity extends BaseToolbarActivity implements View.OnClickListener {

    private TextView mContentTv;
    private RecyclerView mRecyclerView;
    /**
     * 重新编辑
     */
    private Button mBtn;

    @Override
    protected int provideContentView() {
        return R.layout.activity_review_user_fail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setTitle("企业信息");
        mContentTv = (TextView) findViewById(R.id.tv_user_review_fail_content);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_user_review_fail);
        mBtn = (Button) findViewById(R.id.btn_user_review_fail);

    }

    @Override
    protected void initListener() {
        mBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_user_review_fail:
                openActivity(ModifyCompanyMsgActivity.class);
                break;
            default:
                break;
        }
    }
}
