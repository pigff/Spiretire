package com.fjrcloud.sciencepro.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.SciencePolicyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 科技政策
 */
public class SciencePolicyActivity extends BaseToolbarActivity {

    private String[] mName = {"国家政策", "省市政策", "福清政策"};
    private List<Fragment> mFragments;
    private SciencePolicyAdapter mAdapter;

    @Override
    protected int provideContentView() {
        return R.layout.activity_science_policy;
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < mName.length; i++) {
            mFragments.add(SciencePolicyListFragment.newInstance(mName[i]));
        }

    }

    @Override
    public void initAdapter() {
        mAdapter = new SciencePolicyAdapter(getSupportFragmentManager());
    }

    @Override
    public void initView() {
        setTitle("科技政策");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_science_policy);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_science_policy);
        viewPager.setAdapter(mAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

    private class SciencePolicyAdapter extends FragmentPagerAdapter {

        SciencePolicyAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mFragments.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mName[position];
        }
    }
}
