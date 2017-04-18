package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.ScienceDynamicAdapter;
import com.fjrcloud.sciencepro.data.ScienceDynamicResponse;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * 科技动态
 */
public class ScienceDynamicActivity extends BaseRecyclerActivity<ScienceDynamicResponse.ScienceDynamic> {

    private List<ScienceDynamicResponse.ScienceDynamic> mScienceDynamics;

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ScienceDynamicResponse.ScienceDynamic item = (ScienceDynamicResponse.ScienceDynamic) adapter.getItem(position);
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.DATA, item);
                openActivity(ArticleDetailedActivity.class, bundle);
            }
        });
    }

    @Override
    protected String getToolbarTitle() {
        return getString(R.string.science_dynamic);
    }

    @Override
    protected BaseQuickAdapter<ScienceDynamicResponse.ScienceDynamic, BaseViewHolder> getAdapter() {
        return new ScienceDynamicAdapter(R.layout.recycler_science_imgs_item, mScienceDynamics);
    }

    @Override
    protected void getData() {
        mScienceDynamics = new ArrayList<>();
        List<String> oneUrl = new ArrayList<>();
        oneUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1812573004,1419340744&fm=23&gp=0.jpg");
        List<String> twoUrl = new ArrayList<>();
        twoUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=437499229,210401039&fm=11&gp=0.jpg");
        twoUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2847398204,1011998520&fm=11&gp=0.jpg");
        List<String> threeUrl = new ArrayList<>();
        threeUrl.add("https://ss0.bdstatic.com/70cFvHSh_Q1YnxGkpoWK1HF6hhy/it/u=437499229,210401039&fm=11&gp=0.jpg");
        threeUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=2847398204,1011998520&fm=11&gp=0.jpg");
        threeUrl.add("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=3155400910,3660277733&fm=11&gp=0.jpg");
        List<String> noneUrl = new ArrayList<>();

        mScienceDynamics.add(new ScienceDynamicResponse.ScienceDynamic("福州市科学技术局2016年政府信息公开工作年度报告", "2017-01-09", oneUrl, getString(R.string.first)));
        mAdapter.setNewData(mScienceDynamics);
    }

}
