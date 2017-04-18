package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.ManagementEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.HtmlUtil;

/**
 * 介绍职务
 */
public class IntroDutyActivity extends BaseToolbarActivity {

    private WebView mWebView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {
        SimpleSubListener<ManagementEntity> listener = new SimpleSubListener<ManagementEntity>() {
            @Override
            public void onNext(ManagementEntity managementEntity) {
                mWebView.loadData(managementEntity.getContent(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };
        ScieneManager.getManagement(new SimpleSubscriber<>(listener));
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_duty;
    }

    @Override
    public void initData() {

    }


    @Override
    public void initView() {
        setTitle("机构职责");
        mWebView = (WebView) findViewById(R.id.web_duty);
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
    }

}
