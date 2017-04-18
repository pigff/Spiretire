package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link WorkPagerFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class WorkPagerFragment extends BaseFragment {

    private static final String CONTENT = "content";

    private String mParam1;


    public WorkPagerFragment() {
        // Required empty public constructor
    }

    public static WorkPagerFragment newInstance(String param1) {
        WorkPagerFragment fragment = new WorkPagerFragment();
        Bundle args = new Bundle();
        args.putString(CONTENT, param1);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(CONTENT);
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
    public void initAdapter() {

    }

    @Override
    public void initView() {
        TextView textView = (TextView) findViewById(R.id.tv_content_work_pager);
        textView.setText(mParam1);
    }

    @Override
    public void initListener() {

    }
}
