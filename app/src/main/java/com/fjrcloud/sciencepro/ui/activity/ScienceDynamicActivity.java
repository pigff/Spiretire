package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.ScienceDynamicAdapter;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;
import com.fjrcloud.sciencepro.utils.Constants;

/**
 * 科技动态
 */
public class ScienceDynamicActivity extends BaseRecyclerActivity<ScienceDyEntity> {


    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                ScienceDyEntity item = (ScienceDyEntity) adapter.getItem(position);
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
    protected BaseQuickAdapter<ScienceDyEntity, BaseViewHolder> getAdapter() {
        return new ScienceDynamicAdapter(R.layout.recycler_science_imgs_item);
    }

    @Override
    protected void getData() {
        ScieneManager.affairFindAll(new SimpleSubscriber<>(getSimpleListener()), mPageNum, mPageSize);
    }

    @Override
    protected boolean hasBaseLayout() {
        return true;
    }
}
