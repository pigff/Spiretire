package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 子账号列表
 */
public class SonAccountListActivity extends BaseToolbarActivity {


    private RecyclerView mRecyclerView;
    private List<Integer> mIntegers;
    private SonAccountAdapter mAdapter;
    private Button mButton;

    @Override
    protected int provideContentView() {
        return R.layout.activity_son_account_list;
    }

    @Override
    public void initData() {
        mIntegers = new ArrayList<>();
        mIntegers.add(1);
        mIntegers.add(1);
        mIntegers.add(1);
        mIntegers.add(1);
        mIntegers.add(1);
        mIntegers.add(1);
        mIntegers.add(1);
    }

    @Override
    protected void initAdapter() {
        mAdapter = new SonAccountAdapter(R.layout.recycler_son_account_item);
        mAdapter.setNewData(mIntegers);
    }

    @Override
    public void initView() {
        setTitle("企业子账号");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_account_list);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);

        mButton = (Button) findViewById(R.id.btn_son_account_list);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (view.getId()) {
                    case R.id.tv_son_account_del:
                        mAdapter.remove(position);
                        break;
                    case R.id.tv_son_account_modify:
                        openActivity(ModifySonAccountActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(SonRegistActivity.class);
            }
        });
    }

    private class SonAccountAdapter extends BaseQuickAdapter<Integer, BaseViewHolder> {

        SonAccountAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, Integer item) {
            helper.addOnClickListener(R.id.tv_son_account_modify)
                    .addOnClickListener(R.id.tv_son_account_del);
        }
    }
}
