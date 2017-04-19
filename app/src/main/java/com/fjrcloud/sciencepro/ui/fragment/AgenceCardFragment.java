package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.DepartmentEntity;
import com.fjrcloud.sciencepro.data.net.LeaderEntity;
import com.fjrcloud.sciencepro.data.net.StaffEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;
import com.fjrcloud.sciencepro.utils.HtmlUtil;

public class AgenceCardFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "agence";

    private DepartmentEntity mAgence;
    private WebView dutyDesTv;
    private TextView nameTv;


    public AgenceCardFragment() {

    }
    public static AgenceCardFragment newInstance(DepartmentEntity agenceResponse) {
        AgenceCardFragment fragment = new AgenceCardFragment();
        Bundle args = new Bundle();
        args.putSerializable(ARG_PARAM1, agenceResponse);
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_agence_card, container, false);
    }

    @Override
    public void initData() {
        if (getArguments() != null) {
            mAgence = (DepartmentEntity) getArguments().getSerializable(ARG_PARAM1);
        }
    }


    @Override
    public void initView() {
        TextView officeNameTv = (TextView) findViewById(R.id.tv_office_name_agence_card);
        TextView phoneTv = (TextView) findViewById(R.id.tv_phone_name_agence_card);
        nameTv = (TextView) findViewById(R.id.tv_name_agence_card);
//        TextView emailTv = (TextView) findViewById(R.id.tv_email_agence_card);
        dutyDesTv = (WebView) findViewById(R.id.web_duty_des_agence_card);
        dutyDesTv.getSettings().setDomStorageEnabled(true);
        dutyDesTv.getSettings().setJavaScriptEnabled(true);
        dutyDesTv.getSettings().setBlockNetworkImage(false);
        dutyDesTv.setWebViewClient(new WebViewClient() {
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, String url) {
                view.loadUrl(url);
                return true;
            }
        });

        if (mAgence != null) {
            officeNameTv.setText("科室名称:  " + mAgence.getName());
            phoneTv.setText("电话:  " + mAgence.getPhone());
        }
    }

    @Override
    protected void onLazyLoad() {
        if (mAgence != null) {
            SimpleSubListener<StaffEntity> listener = new SimpleSubListener<StaffEntity>() {
                @Override
                public void onNext(StaffEntity staffEntity) {
                    if (staffEntity.getStaff().size() > 0) {
                        nameTv.setText("负责人: " + staffEntity.getStaff().get(0).getName());
                    }
                    dutyDesTv.loadData(staffEntity.getDepartment().getResponsibilities(), HtmlUtil.MIME_TYPE, HtmlUtil.ENCODING);
                }

                @Override
                public void onError(Throwable throwable) {

                }
            };
            addSubscription(ScieneManager.deparmentFind(new SimpleSubscriber<>(listener), mAgence.getId()));
        }
    }
}
