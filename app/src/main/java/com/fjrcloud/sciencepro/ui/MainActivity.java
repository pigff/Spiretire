package com.fjrcloud.sciencepro.ui;

import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.content.ContextCompat;
import android.support.v4.view.ViewPager;
import android.util.Log;
import android.view.View;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.CommonPagerAdapter;
import com.fjrcloud.sciencepro.data.multi.DrawerMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.CompanyListFragment;
import com.fjrcloud.sciencepro.ui.fragment.MainFragment;
import com.fjrcloud.sciencepro.ui.fragment.MsgNoticeFragment;
import com.fjrcloud.sciencepro.ui.fragment.PersonalFragment;
import com.fjrcloud.sciencepro.widget.DrawerPopWindow;

import org.greenrobot.eventbus.EventBus;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseToolbarActivity {

    private ViewPager mViewPager;
    private TabLayout mTabLayout;
    private List<TabItem> mTabItems;
    private List<Fragment> mFragments;
    private List<DrawerMulti> mMultis;
    private DrawerPopWindow mPopWindow;
    private long mLastLogoutTime;
    private int mLastIndustryIndex;
    private int mLastNatureIndex;

    @Override
    protected int provideContentView() {
        return R.layout.activity_main;
    }

    @Override
    public void initListener() {

        mTabLayout.addOnTabSelectedListener(new TabLayout.OnTabSelectedListener() {
            @Override
            public void onTabSelected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                setTab(tab, true, position);
                mViewPager.setCurrentItem(position, false);
                setTitle(mTabItems.get(position).getTitle());
                if (position == 1) {
                    mRightTv.setVisibility(View.VISIBLE);
                }
            }

            @Override
            public void onTabUnselected(TabLayout.Tab tab) {
                int position = tab.getPosition();
                setTab(tab, false, position);
                mRightTv.setVisibility(View.GONE);
            }

            @Override
            public void onTabReselected(TabLayout.Tab tab) {

            }
        });
    }

    private void setTab(TabLayout.Tab tab, boolean isSelected, int position) {
        View view = tab.getCustomView();
        if (view != null) {
            ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
            TextView textView = (TextView) view.findViewById(R.id.tv_tab);
            if (isSelected) {
                imageView.setImageResource(mTabItems.get(position).getImgSrcOn());
                textView.setTextColor(ContextCompat.getColor(this, R.color.blue_68_color_code));
            } else {
                imageView.setImageResource(mTabItems.get(position).getImgSrcOff());
                textView.setTextColor(ContextCompat.getColor(this, R.color.grey_150_color_code));
            }
        }
    }

    @Override
    public void initView() {
        mViewPager = (ViewPager) findViewById(R.id.vp_main);
        mTabLayout = (TabLayout) findViewById(R.id.tablayout_main);
        initPopWindow();
        mRightTv.setVisibility(View.GONE);

        for (int i = 0; i < mTabItems.size(); i++) {
            mTabLayout.addTab(mTabLayout.newTab().setCustomView(getTabView(i)));
        }
        mViewPager.setAdapter(new CommonPagerAdapter(getSupportFragmentManager(), mFragments));
        mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(mTabLayout));

        setTitle(mTabItems.get(0).getTitle());
    }

    private void initPopWindow() {
        setRightTv("筛选", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                WindowManager.LayoutParams lp2 = getWindow().getAttributes();
                lp2.alpha = 0.5f;
                getWindow().setAttributes(lp2);
                mPopWindow = new DrawerPopWindow(MainActivity.this, mMultis, mLastIndustryIndex, mLastNatureIndex);
                mPopWindow.showPoppupWindow(mViewPager);
                mPopWindow.setIndustryClick(new DrawerPopWindow.IndustryInterface() {
                    @Override
                    public void click(DrawerMulti.Category category, int position) {
                        mLastIndustryIndex = position;
                        Log.d(TAG, category == null ? "null" + "xixi" + position : category.toString() + "xixi" + position);
                    }
                });
                mPopWindow.setNatureClick(new DrawerPopWindow.NatureInterface() {
                    @Override
                    public void click(DrawerMulti.Category category, int position) {
                        mLastNatureIndex = position;
                        Log.d(TAG, category == null ? "null" + "xixi" + position : category.toString() + "xixi" + position);
                    }
                });
                mPopWindow.setConfirmClick(new DrawerPopWindow.ConfirmClick() {
                    @Override
                    public void click(View view) {
                        if (mLastIndustryIndex == -1 && mLastNatureIndex == -1) {
                            return;
                        }
                        String[] filter = {(mLastIndustryIndex == -1 ? "" : mMultis.get(mLastIndustryIndex).getCategory().getName())
                                , mLastNatureIndex == -1 ? "" : mMultis.get(mLastNatureIndex).getCategory().getName()};
                        EventBus.getDefault().post(filter);
                        mPopWindow.dismiss();
                    }
                });
                mPopWindow.setOnDismissListener(new PopupWindow.OnDismissListener() {
                    @Override
                    public void onDismiss() {
                        WindowManager.LayoutParams lp = getWindow().getAttributes();
                        lp.alpha = 1.0f;
                        getWindow().setAttributes(lp);
                    }
                });
            }
        });
    }

    @Override
    public void initData() {
        mLastLogoutTime = 0;

        initPopData();

        mTabItems = new ArrayList<>();
        mTabItems.add(new TabItem("首页", R.mipmap.icon_main_on, R.mipmap.icon_main_off));
        mTabItems.add(new TabItem("企业名录", R.mipmap.icon_gover_on, R.mipmap.icon_gover_off));
        mTabItems.add(new TabItem("消息提醒", R.mipmap.icon_notice_on, R.mipmap.icon_notice_off));
        mTabItems.add(new TabItem("个人中心", R.mipmap.icon_personal_on, R.mipmap.icon_personal_off));

        mFragments = new ArrayList<>();
        mFragments.add(MainFragment.newInstance());
        mFragments.add(CompanyListFragment.newInstance());
        mFragments.add(MsgNoticeFragment.newInstance());
        mFragments.add(PersonalFragment.newInstance());
    }

    private void initPopData() {
        mLastIndustryIndex = -1;
        mLastNatureIndex = -1;

        mMultis = new ArrayList<>();
        mMultis.add(new DrawerMulti(DrawerMulti.NORMAL_SIZE, DrawerMulti.CATEGORY, "行业"));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃2", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃3", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃4", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃5", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃6", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.INDUSTRY, new DrawerMulti.Category("玻璃7", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.NORMAL_SIZE, DrawerMulti.DIVIDING));
        mMultis.add(new DrawerMulti(DrawerMulti.NORMAL_SIZE, DrawerMulti.CATEGORY, "性质"));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新2", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新3", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新4", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新5", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新6", false)));
        mMultis.add(new DrawerMulti(DrawerMulti.CATEGORY_SIZE, DrawerMulti.NATURE, new DrawerMulti.Category("高新7", false)));
    }

    @Override
    public void initAdapter() {

    }

    public View getTabView(int position) {
        View view = getLayoutInflater().inflate(R.layout.design_tab_layout, null, false);
        ImageView imageView = (ImageView) view.findViewById(R.id.iv_tab);
        TextView textView = (TextView) view.findViewById(R.id.tv_tab);
        textView.setText(mTabItems.get(position).getTitle());
        if (position == 0) {
            textView.setTextColor(ContextCompat.getColor(this, R.color.blue_68_color_code));
            imageView.setImageResource(mTabItems.get(position).getImgSrcOn());
        } else {
            textView.setTextColor(ContextCompat.getColor(this, R.color.grey_150_color_code));
            imageView.setImageResource(mTabItems.get(position).getImgSrcOff());
        }
        return view;
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }

    @Override
    protected boolean canGoBack() {
        return false;
    }

    @Override
    public void onBackPressed() {
        if (mLastLogoutTime == 0) {
            Toast.makeText(this, R.string.exit_notice, Toast.LENGTH_SHORT).show();
            mLastLogoutTime = System.currentTimeMillis();
            return;
        }
        if (System.currentTimeMillis() - mLastLogoutTime <= 5000) {
            super.onBackPressed();
        } else {
            Toast.makeText(this, R.string.exit_notice, Toast.LENGTH_SHORT).show();
            mLastLogoutTime = System.currentTimeMillis();
        }
    }

    private class TabItem {
        private String title;
        private Integer imgSrcOn;
        private Integer imgSrcOff;

        TabItem(String title, Integer imgSrcOn, Integer imgSrcOff) {
            this.title = title;
            this.imgSrcOn = imgSrcOn;
            this.imgSrcOff = imgSrcOff;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public Integer getImgSrcOn() {
            return imgSrcOn;
        }

        public void setImgSrcOn(Integer imgSrcOn) {
            this.imgSrcOn = imgSrcOn;
        }

        public Integer getImgSrcOff() {
            return imgSrcOff;
        }

        public void setImgSrcOff(Integer imgSrcOff) {
            this.imgSrcOff = imgSrcOff;
        }
    }
}
