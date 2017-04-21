package com.fjrcloud.sciencepro.ui.fragment;


import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.text.Spannable;
import android.text.SpannableString;
import android.text.style.ImageSpan;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.CenterGridAdapter;
import com.fjrcloud.sciencepro.ui.activity.LoginActivity;
import com.fjrcloud.sciencepro.ui.activity.ModifyPswActivity;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;
import com.makeramen.roundedimageview.RoundedImageView;

import java.util.ArrayList;
import java.util.List;


public class PersonalFragment extends BaseFragment implements View.OnClickListener {

    private List<Integer> mImageList;

    private List<String> mCategoryList;
    private CenterGridAdapter mAdapter;
    private GridView mGridView;
    private View mView;
    private RoundedImageView mRoundedImageView;
    private TextView mCompanyName;
    private TextView mIntroTv;
    private Button mLogoutBtn;
    private TextView mPhoneTv;
    private TextView mRegistTv;
    private TextView mLoginTv;

    public PersonalFragment() {
        // Required empty public constructor
    }

    public static PersonalFragment newInstance() {
        PersonalFragment fragment = new PersonalFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }


    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_personal, container, false);
    }

    @Override
    public void initView() {
        mRoundedImageView = (RoundedImageView) findViewById(R.id.ec_head_image);
        mCompanyName = (TextView) findViewById(R.id.center_company_name);
        mIntroTv = (TextView) findViewById(R.id.intro_login);
        mLogoutBtn = (Button) findViewById(R.id.login_out);
        mPhoneTv = (TextView) findViewById(R.id.cmc_contact_tel);
        mGridView = (GridView) findViewById(R.id.center_grid);
        mRegistTv = (TextView) findViewById(R.id.tv_regist);
        mLoginTv = (TextView) findViewById(R.id.tv_login);
        mGridView.setAdapter(mAdapter);

        SpannableString string = new SpannableString("  科技局电话: 400-667788-5");
        Drawable d = ContextCompat.getDrawable(getActivity(), R.mipmap.icon_phone);
        d.setBounds(0, 0, 50, 50);
        ImageSpan span = new ImageSpan(d, ImageSpan.ALIGN_BASELINE);
        string.setSpan(span, 0, 1, Spannable.SPAN_EXCLUSIVE_EXCLUSIVE);
        mPhoneTv.setText(string);
    }

    @Override
    public void initListener() {
        mRegistTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showShortToast("该功能开发中");
//                openActivity(RegisterActivity.class);
            }
        });
        mLoginTv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(LoginActivity.class);
            }
        });

        mGridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                switch (position) {
                    case 0:
                        break;
                    case 1:
//                        openActivity(ReviewFailUserActivity.class);
                        showShortToast("该功能开发中");
//                        openActivity(ReviewUserActivity.class);
                        break;
                    case 2:
                        openActivity(ModifyPswActivity.class);
                        break;
                    case 3:
                        showShortToast("该功能开发中");
//                        openActivity(SonAccountListActivity.class);
                        break;
                    case 4:
                        showShortToast("该功能开发中");
//                        openActivity(ManagerReviewListActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initAdapter() {
        mAdapter = new CenterGridAdapter(getActivity(), mImageList, mCategoryList);
    }

    @Override
    public void initData() {
        mImageList = new ArrayList<>();
        mCategoryList = new ArrayList<>();

        mImageList.add(R.drawable.img_header);
        mImageList.add(R.drawable.img_company_msg);
        mImageList.add(R.drawable.img_modify_psw);
        mImageList.add(R.drawable.img_share);
        mImageList.add(R.drawable.img_share);

        mCategoryList.add("头像设置");
        mCategoryList.add("企业信息");
        mCategoryList.add("修改密码");
        mCategoryList.add("子账号注册");
        mCategoryList.add("应用分享");
    }


    @Override
    public void onClick(View v) {

    }
}
