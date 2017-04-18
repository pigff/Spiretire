package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.view.View;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.CommonPagerAdapter;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.ui.fragment.UserMailboxFragment;
import com.fjrcloud.sciencepro.widget.FourHorizontalBtnLayout;
import com.fjrcloud.sciencepro.widget.NoScrollViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * 用户公众信箱
 */
public class MailboxUserActivity extends BaseToolbarActivity {

    private List<Fragment> mFragments;
    private CommonPagerAdapter mAdapter;
    private NoScrollViewPager mViewPager;
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
        mFragments.add(UserMailboxFragment.newInstance(1));
        mFragments.add(UserMailboxFragment.newInstance(1));
        mFragments.add(UserMailboxFragment.newInstance(1));
    }

    @Override
    public void initAdapter() {
        mAdapter = new CommonPagerAdapter(getSupportFragmentManager(), mFragments);

    }

    @Override
    public void initView() {
        setTitle("公众信箱");
        setRightTv("我的信息", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(MyMailboxActivity.class);
            }
        });
        mViewPager = (NoScrollViewPager) findViewById(R.id.vp_user_mailbox);
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

        mFab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(WriteLetterActivity.class);
//                openActivity(UserReplyActivity.class);
            }
        });
    }

}
