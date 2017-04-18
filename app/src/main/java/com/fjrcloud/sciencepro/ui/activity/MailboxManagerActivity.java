package com.fjrcloud.sciencepro.ui.activity;

import android.support.v4.app.Fragment;
import android.view.View;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.CommonPagerAdapter;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.ManagerMailboxFragment;
import com.fjrcloud.sciencepro.widget.FourHorizontalBtnLayout;
import com.fjrcloud.sciencepro.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理员公众信箱
 */
public class  MailboxManagerActivity extends BaseToolbarActivity {

    private FourHorizontalBtnLayout mLayout;
    private List<Fragment> mFragments;
    private CommonPagerAdapter mAdapter;
    private NoScrollViewPager mViewPager;

    @Override
    protected int provideContentView() {
        return R.layout.activity_mailbox_manager;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        mFragments.add(ManagerMailboxFragment.newInstance(1));
        mFragments.add(ManagerMailboxFragment.newInstance(1));
        mFragments.add(ManagerMailboxFragment.newInstance(1));
        mFragments.add(ManagerMailboxFragment.newInstance(1));
    }

    @Override
    public void initAdapter() {
        mAdapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);
    }

    @Override
    public void initView() {
        setTitle("公众信箱");
        mLayout = (FourHorizontalBtnLayout) findViewById(R.id.layout_manager_mailbox);
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_manager_mailbox);
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
