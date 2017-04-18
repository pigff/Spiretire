package com.fjrcloud.sciencepro.ui.activity;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.WorkPagerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * 科技政策   具体
 */
public class PolicyDetailedActivity extends BaseToolbarActivity {

    private String[] mTitles = {"办理程序", "办理条件", "其他事项"};

    private RecyclerView mRecyclerView;
    private TabLayout mTabLayout;
    private TextView mPhoneTv;
    private TextView mAddrTv;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<DownloadInfo> mDownloadInfos;
    private WorkDetailedPagerAdapter mPagerAdapter;
    private WorkRvAdapter mRvAdapter;

    @Override
    protected int provideContentView() {
        return R.layout.activity_work_detailed;
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                Toast.makeText(WorkDetailedActivity.this, "xixi", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void initView() {
        setTitle("科技政策");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_work_detailed);
        mTabLayout = (TabLayout) findViewById(R.id.tab_work_detailed);
        mPhoneTv = (TextView) findViewById(R.id.tv_phone_work_detailed);
        mAddrTv = (TextView) findViewById(R.id.tv_addr_work_detailed);
        mViewPager = (ViewPager) findViewById(R.id.vp_work_detailed);

        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 2));
        mRecyclerView.setAdapter(mRvAdapter);
        mViewPager.setAdapter(mPagerAdapter);
        mTabLayout.setupWithViewPager(mViewPager);

        mAddrTv.setText("福清市科技局");
        mPhoneTv.setText("18065215452");
    }

    @Override
    public void initAdapter() {
        mPagerAdapter = new WorkDetailedPagerAdapter(getSupportFragmentManager());
        mRvAdapter = new WorkRvAdapter(R.layout.recycler_file_item, mDownloadInfos);
    }

    @Override
    public void initData() {
        mFragments = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            mFragments.add(WorkPagerFragment.newInstance(mTitles[i] + "暂时没有内容"));
        }

        mDownloadInfos = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            mDownloadInfos.add(new DownloadInfo("办理文档程序.doc"));
        }
    }

    private class WorkDetailedPagerAdapter extends FragmentPagerAdapter {

        WorkDetailedPagerAdapter(FragmentManager fm) {
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
            return mTitles[position];
        }
    }

    private class WorkRvAdapter extends BaseQuickAdapter<DownloadInfo, BaseViewHolder> {

        WorkRvAdapter(int layoutResId, List<DownloadInfo> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DownloadInfo item) {
            helper.setImageResource(R.id.iv_file_item, R.drawable.img_word)
                    .setText(R.id.tv_name_file_item, item.getName())
                    .addOnClickListener(R.id.file_item_group);
        }
    }

    class DownloadInfo {
        private String name;

        public DownloadInfo(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
