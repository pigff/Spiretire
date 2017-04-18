package com.fjrcloud.sciencepro.ui.activity;

import android.support.v4.content.ContextCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.DrawerMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 选择行业
 */
public class SelectIndusActivity extends BaseToolbarActivity {


    private IndusGridAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private List<DrawerMulti.Category> mCategories;
    private HorizontalBtnLayout mLayout;

    @Override
    protected int provideContentView() {
        return R.layout.activity_select_indus;
    }

    @Override
    public void initData() {
        mCategories = new ArrayList<>();
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
        mCategories.add(new DrawerMulti.Category("互联网", false));
    }

    @Override
    protected void initAdapter() {
        mAdapter = new IndusGridAdapter(R.layout.recycler_multi_drawer_category_item);
        mAdapter.setNewData(mCategories);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {

            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                Object item = adapter.getItem(position);
                if (item instanceof DrawerMulti.Category) {
                    if (((DrawerMulti.Category) item).isSelected()) {
                        return;
                    }
                    for (DrawerMulti.Category category : mCategories) {
                        category.setSelected(false);
                    }
                    ((DrawerMulti.Category) item).setSelected(true);
                    mAdapter.notifyDataSetChanged();
                }
            }
        });

        mLayout.setClick(new HorizontalBtnLayout.BtnLayoutClick() {
            @Override
            public void leftClick(View view) {
                openActivity(SelectIndusDetailActivity.class);
            }

            @Override
            public void rightClick(View view) {
                finish();
            }
        });
    }

    @Override
    public void initView() {
        setTitle("行业");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_select_indus);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);

        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_select_indus);

    }

    private class IndusGridAdapter extends BaseQuickAdapter<DrawerMulti.Category, BaseViewHolder> {

        IndusGridAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, DrawerMulti.Category item) {
            helper.setText(R.id.tv_category_name_multi_drawer, item.getName())
                    .addOnClickListener(R.id.tv_category_name_multi_drawer);
            if (item.isSelected()) {
                helper.setBackgroundRes(R.id.tv_category_name_multi_drawer, R.drawable.pop_flesh_hole_group_bg)
                        .setTextColor(R.id.tv_category_name_multi_drawer, ContextCompat.getColor(mContext, R.color.white));
            } else {
                helper.setBackgroundRes(R.id.tv_category_name_multi_drawer, R.drawable.pop_grey_hole_group_bg)
                        .setTextColor(R.id.tv_category_name_multi_drawer, ContextCompat.getColor(mContext, R.color.black_102_color_code));
            }
        }
    }
}
