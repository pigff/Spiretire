package com.fjrcloud.sciencepro.ui.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;

import java.util.List;

/**
 * Created by greedy on 17/3/14.
 */

public abstract class BaseRecyclerFragment<T> extends BaseFragment implements BaseQuickAdapter.RequestLoadMoreListener {

    protected RecyclerView mRecyclerView;
    protected BaseQuickAdapter<T, BaseViewHolder> mAdapter;
    protected int mPageNum;
    protected int mPageSize;
    private boolean mIsLoad;
    private SimpleSubListener<List<T>> mSimpleSubListener;

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_common_rv_list, container, false);
    }

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_common_fragment);
        if (mRecyclerView == null) {
            throw new IllegalStateException(
                    "The subclass of ToolbarActivity must contain a recyclerview.");
        }
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initAdapter() {
        mAdapter = getRecyclerAdapter();
        if (canLoadMore()) {
            mAdapter.setOnLoadMoreListener(this);
        }
        if (canLoadMore() && openLoadAnim()) {
            mAdapter.openLoadAnimation();
        }
    }

    @Override
    public void initData() {
        mPageNum = 0;
        mPageSize = 10;
        mIsLoad = false;
    }




    @Override
    protected boolean hasBaseLayout() {
        return true;
    }

    protected SimpleSubListener<List<T>> getSimpleListener() {
        if (mSimpleSubListener == null) {
            mSimpleSubListener = new SimpleSubListener<List<T>>() {
                @Override
                public void onNext(List<T> t) {
                    if (mPageNum == 0 && t.size() == 0 && hasBaseLayout()) {
                        showEmpty();
                        return;
                    }
                    mAdapter.addData(t);
                    if (canLoadMore()) {
                        if (t.size() <= mPageSize) {
                            if (mPageNum == 0) {
                                mAdapter.loadMoreEnd(true);
                            } else {
                                mAdapter.loadMoreEnd();
                            }
                        } else {
                            mAdapter.loadMoreComplete();
                            mPageNum++;
                        }
                    }
                    mIsLoad = false;
                }

                @Override
                public void onError(Throwable throwable) {
                    if (mPageNum == 0 && hasBaseLayout()) {
                        showError();
                    } else {
                        mAdapter.loadMoreFail();
                    }
                    mIsLoad = false;
                }
            };
        }
        return mSimpleSubListener;
    }


    protected boolean canLoadMore() {
        return false;
    }

    protected boolean openLoadAnim() {
        return false;
    }

    protected abstract BaseQuickAdapter<T, BaseViewHolder> getRecyclerAdapter();


    @Override
    public void onLoadMoreRequested() {
        getNetData();
    }

    private void getNetData() {
        if (!mIsLoad) {
            mIsLoad = true;
            onLazyLoad();
        }
    }
}
