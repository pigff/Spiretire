package com.fjrcloud.sciencepro.ui.base;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.LogUtil;
import com.fjrcloud.sciencepro.widget.EmptyLayout;

import org.greenrobot.eventbus.EventBus;

import rx.Subscription;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by lin on 2017/2/23.
 */

public abstract class BaseActivity extends AppCompatActivity implements BaseFunIml, EmptyLayout.OnBaseLayoutClickListener {

    protected static final String TAG = "BaseActivity";

    private CompositeSubscription mCompositeSubscription;

    protected EmptyLayout mEmptyLayout;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        LogUtil.d(TAG, getClass().getSimpleName());
        setContentView(provideContentView());
        if (hasBaseLayout()) {
            mEmptyLayout = (EmptyLayout) findViewById(R.id.emptylayout);
            mEmptyLayout.setOnBaseLayoutClickListener(this);
        }
        if (registEventBus()) {
            EventBus.getDefault().register(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mCompositeSubscription != null) {
            mCompositeSubscription.unsubscribe();
        }
        if (registEventBus()) {
            EventBus.getDefault().unregister(this);
        }
    }

    protected void showEmpty() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_DATA);
        }
    }

    protected void showError() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_NO_NET);
        }
    }

    protected void showProgress() {
        if (mEmptyLayout != null) {
            mEmptyLayout.setEmptyStatus(EmptyLayout.STATUS_LOADING);
        }
    }

    protected void hideAllView() {
        if (mEmptyLayout != null) {
            mEmptyLayout.hide();
        }
    }


    protected CompositeSubscription getCompositeSubscription() {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        return mCompositeSubscription;
    }

    protected void addSubscription(Subscription subscription) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(subscription);
    }


    protected void openActivity(Class<?> clazz) {
        Intent intent = new Intent(this, clazz);
        startActivity(intent);
    }

    protected void openActivityForResult(Class<?> clazz, int requestCode) {
        Intent intent = new Intent(this, clazz);
        startActivityForResult(intent, requestCode);
    }

    protected void openActivityForResult(Class<?> clazz) {
        openActivityForResult(clazz, Constants.DEFAULT_REQUEST_CODE);
    }

    protected void openActivity(Class<?> clazz, Bundle bundle) {
        Intent intent = new Intent(this, clazz);
        intent.putExtra(Constants.PARAM, bundle);
        startActivity(intent);
    }

    protected Bundle getBundleData() {
        return getIntent().getBundleExtra(Constants.PARAM);
    }

    protected void showShortToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
    }

    protected void showLongToast(String msg) {
        Toast.makeText(this, msg, Toast.LENGTH_LONG).show();
    }

    /**
     * 初始化适配器
     */
    protected void initAdapter() {

    }

    /**
     * 初始化事件点击监听
     */
    protected void initListener() {

    }

    @Override
    public void onClickRetry() {

    }

    @Override
    public void onClickEmpty() {

    }

    protected boolean registEventBus() {
        return false;
    }

    protected abstract int provideContentView();

    protected boolean hasBaseLayout() {
        return false;
    }
}
