package com.fjrcloud.sciencepro.ui.activity.filepush;

import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;

public class FileContainerActivity extends BaseToolbarActivity implements View.OnClickListener {

    private LinearLayout mFakeFileSearchGroup;
    private FrameLayout mLayoutFileContainer;
    private FragmentTransaction mTransaction;
    private int mCategory;

    @Override
    protected int provideContentView() {
        return R.layout.activity_file_container;
    }

    @Override
    public void initData() {
        FragmentManager manager = getSupportFragmentManager();
        mTransaction = manager.beginTransaction();

        mCategory = getBundleData().getInt(Constants.DATA);
    }

    @Override
    public void initView() {
        setTitle("收件箱");
        mFakeFileSearchGroup = (LinearLayout) findViewById(R.id.fake_file_search_group);
        mFakeFileSearchGroup.setOnClickListener(this);
        mLayoutFileContainer = (FrameLayout) findViewById(R.id.layout_file_container);
        switch (mCategory) {
            case 0:
                break;
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fake_file_search_group:
                break;
        }
    }
}
