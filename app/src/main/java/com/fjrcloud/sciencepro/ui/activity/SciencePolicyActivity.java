package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.TypeEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.SciencePolicyListFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 科技政策
 */
public class SciencePolicyActivity extends BaseToolbarActivity {

    private List<Fragment> mFragments;
    private SciencePolicyAdapter mAdapter;
    private List<TypeEntity> entities;

    @Override
    protected int provideContentView() {
        return R.layout.activity_science_policy;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {
        SimpleSubListener<List<TypeEntity>> listener = new SimpleSubListener<List<TypeEntity>>() {
            @Override
            public void onNext(List<TypeEntity> typeEntities) {
                for (TypeEntity entity : typeEntities) {
                    entities.add(entity);
                }
                for (int i = 0; i < entities.size(); i++) {
                    mFragments.add(SciencePolicyListFragment.newInstance(entities.get(i).getId()));
                }
                mAdapter.notifyDataSetChanged();
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };
        addSubscription(ScieneManager.type2FindAll(new SimpleSubscriber<>(listener)));
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        entities = new ArrayList<>();
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
            return entities.get(position).getName();
        }
    }
}
