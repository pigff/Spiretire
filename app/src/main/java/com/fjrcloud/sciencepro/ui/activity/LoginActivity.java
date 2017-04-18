package com.fjrcloud.sciencepro.ui.activity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseActivity;
import com.fjrcloud.sciencepro.utils.Constants;

/**
 * Created by lin on 2016/8/15.
 * 登录界面
 */
public class LoginActivity extends BaseActivity implements View.OnClickListener {

    private static final String TAG = "LoginActivity";
    private EditText mAccountEdit;
    private EditText mPwdEdit;

    private String mUserName;
    private String mPassword;
    private Button mLoginBtn;
    private Button mRegistBtn;
    private ImageView mBackIv;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        init();
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_login;
    }

    private void init() {
        initView();

        initListener();
    }

    @Override
    public void initListener() {
        mLoginBtn.setOnClickListener(this);
        mRegistBtn.setOnClickListener(this);
        mBackIv.setOnClickListener(this);
    }

    @Override
    public void initData() {

    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        setTitle("登陆");

        mAccountEdit = (EditText) findViewById(R.id.edit_input_account);
        mPwdEdit = (EditText) findViewById(R.id.edit_input_pwd);
        mLoginBtn = (Button) findViewById(R.id.btn_login);
        mRegistBtn = (Button) findViewById(R.id.btn_regist);
        mBackIv = (ImageView) findViewById(R.id.iv_back_img_top);


    }

    /**
     * -------------------------------------------------------------------------------------------------------------------------------------------------
     */

    /**
     * 将用户名和密码记录进sharedpreferences中
     */
    private void initSp() {
        SharedPreferences sp = getSharedPreferences("user_data", Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sp.edit();
        editor.putString(Constants.ACCOUNT, mUserName);
        editor.putString(Constants.PASSWORD, mPassword);
        editor.apply();
    }


    /**
     * -------------------------------------------------------------------------------------------------------------------------------------------------
     */


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btn_login:
//                mUserName = mAccountEdit.getText().toString();
//                mPassword = MD5.md5(mPwdEdit.getText().toString());
//                if (TextUtils.equals(mUserName, "") || TextUtils.equals(mPassword, "")) {
//                    Toast.makeText(this, "用户名或密码不能为空", Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                if (!RegexUtils.isMobileExact(mUserName)) {
//                    Toast.makeText(this, R.string.phone_format_error, Toast.LENGTH_SHORT).show();
//                    return;
//                }
//                login(new UserDomain().new Login(mUserName, mPassword));
                break;
            case R.id.btn_regist:
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                break;
            case R.id.iv_back_img_top:
                finish();
                break;
            default:
                break;
        }
    }
}
