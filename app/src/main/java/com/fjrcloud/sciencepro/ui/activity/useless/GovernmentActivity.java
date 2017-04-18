package com.fjrcloud.sciencepro.ui.activity.useless;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.ScienceDynamicAdapter;
import com.fjrcloud.sciencepro.data.ScienceDynamicResponse;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.ui.activity.ArticleDetailedActivity;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.List;

/**
 * 政务公开
 */
public class GovernmentActivity extends BaseRecyclerActivity<ScienceDyEntity> {

    private List<ScienceDynamicResponse.ScienceDynamic> mScienceDynamics;


    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ScienceDynamicResponse.ScienceDynamic scienceDynamic = ((ScienceDynamicResponse.ScienceDynamic) adapter.getItem(position));
                Bundle bundle = new Bundle();
                bundle.putSerializable(Constants.DATA, scienceDynamic);
                openActivity(ArticleDetailedActivity.class, bundle);
            }
        });
    }

    @Override
    protected String getToolbarTitle() {
        return "政务公开";
    }

    @Override
    protected BaseQuickAdapter<ScienceDyEntity, BaseViewHolder> getAdapter() {
        return new ScienceDynamicAdapter(R.layout.recycler_science_imgs_item);
    }

    @Override
    protected void getData() {

    }

}
