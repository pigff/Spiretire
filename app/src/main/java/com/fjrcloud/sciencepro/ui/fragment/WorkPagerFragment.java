package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.GuideItemsEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;
import com.fjrcloud.sciencepro.utils.HtmlUtil;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkPagerFragment extends BaseFragment {

    private static final String CONTENT = "content";

    private GuideItemsEntity mParam1;


    public WorkPagerFragment() {
        // Required empty public constructor
    }

    public static WorkPagerFragment newInstance(GuideItemsEntity param1) {
        WorkPagerFragment fragment = new WorkPagerFragment();
        Bundle args = new Bundle();
        args.putSerializable(CONTENT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = (GuideItemsEntity) getArguments().getSerializable(CONTENT);
        }
    }



    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_work_pager, container, false);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        WebView webView = (WebView) findViewById(R.id.web_content_work_pager);
        webView.getSettings().setDomStorageEnabled(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.getSettings().setBlockNetworkImage(false);
        webView.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });
        webView.loadData(mParam1.getValue(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
    }
}
