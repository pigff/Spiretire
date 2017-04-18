package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.text.TextUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.ScienceDynamicResponse;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

/**
 * 具体文章
 */
public class ArticleDetailedActivity extends BaseToolbarActivity {

    private ScienceDynamicResponse.ScienceDynamic mScienceDynamic;

    @Override
    protected int provideContentView() {
        return R.layout.activity_article_detailed;
    }

    @Override
    public void initListener() {

    }

    @Override
    public void initView() {
        if (TextUtils.equals("关于认定福州市创业创新示范中心的通知", mScienceDynamic.getTitle())) {
            setTitle("政务公开");
        } else {
            setTitle("科技动态");
        }
        TextView titleTv = (TextView) findViewById(R.id.tv_title_article_detailed);
        TextView countTv = (TextView) findViewById(R.id.tv_count_article_detailed);
        TextView contentTv = (TextView) findViewById(R.id.tv_content_article_detailed);
        ImageView imageView = (ImageView) findViewById(R.id.iv_01);
        titleTv.setText(mScienceDynamic.getTitle());
        countTv.setText(mScienceDynamic.getDate());
        contentTv.setText(mScienceDynamic.getContent());
        ImgLoadUtils.loadUrl(this, mScienceDynamic.getImgUrls().get(0), imageView);
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        Bundle bundle = getBundleData();
        mScienceDynamic = (ScienceDynamicResponse.ScienceDynamic) bundle.getSerializable(Constants.DATA);
    }
}
