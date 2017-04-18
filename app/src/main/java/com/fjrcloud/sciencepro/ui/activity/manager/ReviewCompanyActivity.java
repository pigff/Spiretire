package com.fjrcloud.sciencepro.ui.activity.manager;

import android.content.DialogInterface;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.ReviewCompanyMultiAdapter;
import com.fjrcloud.sciencepro.data.multi.CommonMulti;
import com.fjrcloud.sciencepro.data.multi.LoginItemMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.DialDialog;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理审核公司具体
 */
public class ReviewCompanyActivity extends BaseToolbarActivity {


    private HorizontalBtnLayout mLayout;
    private RecyclerView mRecyclerView;
    private List<MultiItemEntity> mMultiItemEntities;
    private ReviewCompanyMultiAdapter mAdapter;

    @Override
    protected int provideContentView() {
        return R.layout.activity_review_company;
    }

    @Override
    public void initData() {
        mMultiItemEntities = new ArrayList<>();
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
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_review_company);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_review_company);

    }

    @Override
    protected void initListener() {
        mLayout.setClick(new HorizontalBtnLayout.BtnLayoutClick() {
            @Override
            public void leftClick(View view) {
                new DialDialog.Builder(ReviewCompanyActivity.this)
                        .setMessage("该企业信息是否准确无误?")
                        .setNegativeButtonColor(R.color.grey_191_color_code)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButtonColor(R.color.black_80_color_code)
                        .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }

            @Override
            public void rightClick(View view) {
                openActivity(ReviewNoPassActivity.class);
            }
        });
    }
}
