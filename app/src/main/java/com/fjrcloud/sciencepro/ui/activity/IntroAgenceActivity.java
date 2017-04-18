package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.AgenceResponse;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.AgenceCardFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 机构简介
 */
public class IntroAgenceActivity extends BaseToolbarActivity {

    private List<AgenceResponse.AgenceEntity.Agence> mAgences;
    private List<Fragment> mFragments;
    private AgencePagerAdapter mPagerAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {

    }


    @Override
    protected int provideContentView() {
        return R.layout.activity_intro_agence;
    }

    @Override
    public void initData() {
        mAgences = new ArrayList<>();
        mFragments = new ArrayList<>();
        mAgences.add(new AgenceResponse.AgenceEntity.Agence("办公室", "0591-8522259", "625456656@qq.com", "林泽松", "负责处理内勤"));
        mAgences.add(new AgenceResponse.AgenceEntity.Agence("工业科科技", "0591-8522259", "625456656@qq.com", "林泽松", "负责处理工业方案"));
        mAgences.add(new AgenceResponse.AgenceEntity.Agence("农业科科技", "0591-8522259", "625456656@qq.com", "林泽松", "负责处理农业方案"));
        for (int i = 0; i < mAgences.size(); i++) {
            mFragments.add(AgenceCardFragment.newInstance(mAgences.get(i)));
        }
    }

    @Override
    public void initView() {
        setTitle("内设机构");
        TabLayout tabLayout = (TabLayout) findViewById(R.id.tab_intro_agence);
        ViewPager viewPager = (ViewPager) findViewById(R.id.vp_intro_agence);

        viewPager.setAdapter(mPagerAdapter);
        tabLayout.setupWithViewPager(viewPager);
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initAdapter() {
        mPagerAdapter = new AgencePagerAdapter(getSupportFragmentManager());
    }

    private class AgencePagerAdapter extends FragmentPagerAdapter {

        AgencePagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragments.get(position);
        }

        @Override
        public int getCount() {
            return mAgences.size();
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mAgences.get(position).getName();
        }
    }
}
