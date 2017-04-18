package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.AgenceResponse;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;

public class AgenceCardFragment extends BaseFragment {

    private static final String ARG_PARAM1 = "agence";

    private AgenceResponse.AgenceEntity.Agence mAgence;


    public AgenceCardFragment() {

    }
    public static AgenceCardFragment newInstance(AgenceResponse.AgenceEntity.Agence agenceResponse) {
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
            mAgence = (AgenceResponse.AgenceEntity.Agence) getArguments().getSerializable(ARG_PARAM1);
        }
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        TextView officeNameTv = (TextView) findViewById(R.id.tv_office_name_agence_card);
        TextView phoneTv = (TextView) findViewById(R.id.tv_phone_name_agence_card);
        TextView nameTv = (TextView) findViewById(R.id.tv_name_agence_card);
        TextView emailTv = (TextView) findViewById(R.id.tv_email_agence_card);
        TextView dutyDesTv = (TextView) findViewById(R.id.tv_duty_des_agence_card);

        if (mAgence != null) {
            officeNameTv.setText("科室名称:  " + mAgence.getName());
            phoneTv.setText("电话:  " + mAgence.getPhone());
            nameTv.setText("负责人:  " + mAgence.getHead());
            emailTv.setText("E-mail:  " + mAgence.getEmail());
            dutyDesTv.setText(mAgence.getDuty());
        }
    }

    @Override
    public void initListener() {

    }
}
