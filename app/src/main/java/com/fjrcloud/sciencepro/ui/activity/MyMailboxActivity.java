package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.View;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.CommonPagerAdapter;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.MyMailboxFragment;
import com.fjrcloud.sciencepro.widget.FourHorizontalBtnLayout;

import java.util.ArrayList;
import java.util.List;

/**
 * 我的信箱
 */
public class MyMailboxActivity extends BaseToolbarActivity {
    private List<Fragment> mFragments;
    private CommonPagerAdapter mAdapter;
    private ViewPager mViewPager;
    private FourHorizontalBtnLayout mLayout;
    private FloatingActionButton mFab;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_mailbox_user;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(MyMailboxFragment.newInstance(1));
        mFragments.add(MyMailboxFragment.newInstance(1));
        mFragments.add(MyMailboxFragment.newInstance(1));
    }

    @Override
    public void initAdapter() {
        mAdapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);

    }

    @Override
    public void initView() {
        setTitle("我的信箱");
        mViewPager = (ViewPager) findViewById(R.id.vp_user_mailbox);
        mLayout = (FourHorizontalBtnLayout) findViewById(R.id.layout_user_mailbox);
        mFab = (FloatingActionButton) findViewById(R.id.fab_user_mailbox);
        mViewPager.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mLayout.setItemClick(new FourHorizontalBtnLayout.HoriBtnItemClick() {
            @Override
            public void itemClick(View view, int position) {
                int currentPosition = mViewPager.getCurrentItem();
                if (position == currentPosition) {
                    return;
                }
                mViewPager.setCurrentItem(position, false);
            }
        });
    }
}
