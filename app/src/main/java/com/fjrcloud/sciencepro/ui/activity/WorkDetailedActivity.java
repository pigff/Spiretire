package com.fjrcloud.sciencepro.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.App;
import com.fjrcloud.sciencepro.DownloadService;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.data.net.GuideItemsEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.WorkPagerFragment;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.FileUtil;
import com.fjrcloud.sciencepro.utils.IntentUtil;
import com.fjrcloud.sciencepro.widget.DialDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 办事指南具体
 */
public class WorkDetailedActivity extends BaseToolbarActivity {

    private List<GuideItemsEntity> guideItemsEntities;

    private RecyclerView mRecyclerView;
    private TabLayout mTabLayout;
    private TextView mPhoneTv;
    private TextView mAddrTv;
    private ViewPager mViewPager;
    private List<Fragment> mFragments;
    private List<DownloadInfo> mDownloadInfos;
    private WorkDetailedPagerAdapter mPagerAdapter;
    private WorkRvAdapter mRvAdapter;
    private WorkEntity entity;

    @Override
    protected int provideContentView() {
        return R.layout.activity_work_detailed;
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                DownloadInfo item = (DownloadInfo) baseQuickAdapter.getItem(position);
                File file = new File(FileUtil.getDiskCacheDir(App.getInstance(), item.getRealName()));

                if (file.exists()) {
                    Intent intent = IntentUtil.openFile(FileUtil.getDiskCacheDir(App.getInstance(), item.getRealName()));
                    if (intent != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(WorkDetailedActivity.this, "不支持打开该类型的文件", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    download(item);
                }
            }
        });
    }

    private void download(final DownloadInfo item) {
        new DialDialog.Builder(WorkDetailedActivity.this)
                .setMessage("是否要下载该文件")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (item.isDownload()) {
                            Toast.makeText(WorkDetailedActivity.this, "正在下载中，请稍后", Toast.LENGTH_SHORT).show();
                        } else {
                            item.setDownload(true);
                            FileEntity entity = new FileEntity(item.getRealName(), item.getName());
                            Intent intent2Download = new Intent(WorkDetailedActivity.this, DownloadService.class);
                            intent2Download.putExtra(Constants.DATA, entity);
                            startService(intent2Download);
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void initView() {
        setTitle("办事指南");
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
        mViewPager.setOffscreenPageLimit(5);

        mAddrTv.setText(entity.getAddr());
        mPhoneTv.setText(entity.getPhone());
    }

    @Override
    public void initAdapter() {
        mPagerAdapter = new WorkDetailedPagerAdapter(getSupportFragmentManager());
        mRvAdapter = new WorkRvAdapter(R.layout.recycler_file_item, mDownloadInfos);
    }

    @Override
    public void initData() {
        Bundle bundle = getBundleData();
        entity = (WorkEntity) bundle.getSerializable(Constants.DATA);
        guideItemsEntities = new ArrayList<>();
        for (GuideItemsEntity guideItemsEntity : entity.getGuideItems()) {
            guideItemsEntities.add(guideItemsEntity);
        }
        mFragments = new ArrayList<>();
        for (int i = 0; i < guideItemsEntities.size(); i++) {
            mFragments.add(WorkPagerFragment.newInstance(guideItemsEntities.get(i)));
        }

        String[] filePahts = entity.getFilePath().split(",");
        mDownloadInfos = new ArrayList<>();
        for (int i = 0; i < filePahts.length; i++) {
            if (!TextUtils.isEmpty(filePahts[i])) {
                mDownloadInfos.add(new DownloadInfo(filePahts[i]));
            }
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
            return guideItemsEntities.get(position).getName();
        }
    }

    private class WorkRvAdapter extends BaseQuickAdapter<DownloadInfo, BaseViewHolder> {

        WorkRvAdapter(int layoutResId, List<DownloadInfo> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, DownloadInfo item) {
            helper.setImageResource(R.id.iv_file_item, item.getImgSrc())
                    .setText(R.id.tv_name_file_item, item.getRealName())
                    .addOnClickListener(R.id.file_item_group);
        }
    }

    class DownloadInfo {
        private String name;
        private int imgSrc;
        private String realName;
        private boolean isDownload;

        DownloadInfo(String name) {
            this.name = name;
            this.imgSrc = IntentUtil.getFileImage(name);
            realName = getReal(name);
        }

        private String getReal(String name) {
            int index = name.lastIndexOf("/");
            return name.substring(index + 1, name.length());
        }

        public boolean isDownload() {
            return isDownload;
        }

        public void setDownload(boolean download) {
            isDownload = download;
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public int getImgSrc() {
            return imgSrc;
        }

        public void setImgSrc(int imgSrc) {
            this.imgSrc = imgSrc;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }
    }
}
