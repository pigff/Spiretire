package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.FrameLayout;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;
import com.fjrcloud.sciencepro.utils.HtmlUtil;

/**
 * 具体文章
 */
public class ArticleDetailedActivity extends BaseToolbarActivity {

    private ScienceDyEntity mScienceDynamic;
    private WebView mWebView;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {
        SimpleSubListener<ScienceDyEntity> listener = new SimpleSubListener<ScienceDyEntity>() {
            @Override
            public void onNext(ScienceDyEntity scienceDyEntity) {
                mWebView.loadData(scienceDyEntity.getContent(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };
        ScieneManager.affairFindById(new SimpleSubscriber<>(listener), mScienceDynamic.getId());
    }

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
        FrameLayout layout = (FrameLayout) findViewById(R.id.layout_web_container);

        mWebView = new WebView(getApplicationContext());
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT);
        mWebView.setLayoutParams(params);
        layout.addView(mWebView);

        mWebView.getSettings().setDomStorageEnabled(true);
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.getSettings().setBlockNetworkImage(false);
        mWebView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });


        titleTv.setText(mScienceDynamic.getTitle());
        countTv.setText(DateUtil.getDateToString(mScienceDynamic.getCreateTime()));
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initData() {
        Bundle bundle = getBundleData();
        mScienceDynamic = (ScienceDyEntity) bundle.getSerializable(Constants.DATA);
    }
}
