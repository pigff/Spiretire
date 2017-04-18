package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseActivity;

/**
 * 修改密码
 */
public class ModifyPswActivity extends BaseActivity implements View.OnClickListener {

    private EditText mUserEdit;
    private EditText mPswEdit;
    private EditText mConfirmEdit;
    private Button mConfirmBtn;
    private Button mCancelBtn;
    private ImageView mBackIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_modify_psw;
    }

    private void init() {
        initView();

        initListener();
    }

    @Override
    public void initListener() {
        mBackIv.setOnClickListener(this);
        mConfirmBtn.setOnClickListener(this);
        mCancelBtn.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        mUserEdit = (EditText) findViewById(R.id.edit_input_account);
        mPswEdit = (EditText) findViewById(R.id.edit_input_pwd);
        mConfirmEdit = (EditText) findViewById(R.id.edit_confirm_pwd);

        mConfirmBtn = (Button) findViewById(R.id.btn_horizon_confirm);
        mCancelBtn = (Button) findViewById(R.id.btn_horizon_cancel);
        mBackIv = (ImageView) findViewById(R.id.iv_back_img_top);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_horizon_cancel:
                finish();
                break;
            case R.id.btn_horizon_confirm:
                Toast.makeText(this, "确定", Toast.LENGTH_SHORT).show();
                break;
            case R.id.iv_back_img_top:
                finish();
                break;
            default:
                break;
        }
    }
}
