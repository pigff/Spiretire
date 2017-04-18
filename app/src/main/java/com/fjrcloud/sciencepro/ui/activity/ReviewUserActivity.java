package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.ReviewCompanyMultiAdapter;
import com.fjrcloud.sciencepro.data.multi.CommonMulti;
import com.fjrcloud.sciencepro.data.multi.LoginItemMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户 企业审核
 */
public class ReviewUserActivity extends BaseToolbarActivity implements View.OnClickListener {

    private RecyclerView mRvReviewUser;
    private List<MultiItemEntity> mMultiItemEntities;
    /**
     * 修改
     */
    private Button mBtnReviewUser;
    private ReviewCompanyMultiAdapter mAdapter;


    @Override
    protected int provideContentView() {
        return R.layout.activity_review_user;
    }

    @Override
    public void initData() {
        mMultiItemEntities = new ArrayList<>();
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.STATUS));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.TITLE, "必填"));
        mMultiItemEntities.add(LoginItemMulti.testData("hangye", ReviewCompanyMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.DIVIDING));
        mMultiItemEntities.add(LoginItemMulti.testData("name", ReviewCompanyMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.IMAGE));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.FILE));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.DIVIDING));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.TITLE, "选填"));
        mMultiItemEntities.add(LoginItemMulti.testData("long", ReviewCompanyMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.DIVIDING));
        mMultiItemEntities.add(LoginItemMulti.testData("short", ReviewCompanyMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.ALL_DELETE));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.IMAGE));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.FILE));
        mMultiItemEntities.add(new CommonMulti(ReviewCompanyMultiAdapter.DIVIDING));
    }

    @Override
    protected void initAdapter() {
        mAdapter = new ReviewCompanyMultiAdapter(mMultiItemEntities);
    }

    @Override
    public void initView() {
        setTitle("企业信息");
        mRvReviewUser = (RecyclerView) findViewById(R.id.rv_review_user);
        mRvReviewUser.setHasFixedSize(true);
        mRvReviewUser.setLayoutManager(new LinearLayoutManager(this));
        mRvReviewUser.setAdapter(mAdapter);
        mBtnReviewUser = (Button) findViewById(R.id.btn_review_user);
        mBtnReviewUser.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_review_user:
                openActivity(ModifyCompanyMsgActivity.class);
                break;
        }
    }
}
