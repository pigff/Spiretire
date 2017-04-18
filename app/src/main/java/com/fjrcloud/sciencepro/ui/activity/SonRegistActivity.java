package com.fjrcloud.sciencepro.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

/**
 * 子账号注册
 */
public class SonRegistActivity extends BaseToolbarActivity {

    private EditText mEditPswMyself;
    private EditText mEditAccount;
    private EditText mEditPsw;
    private EditText mEditConfirmPsw;
    private HorizontalBtnLayout mLayout;


    @Override
    protected int provideContentView() {
        return R.layout.activity_son_regist;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setTitle("子账号注册");
        mEditPswMyself = (EditText) findViewById(R.id.edit_psw_myself);
        mEditAccount = (EditText) findViewById(R.id.edit_son_account);
        mEditPsw = (EditText) findViewById(R.id.edit_son_psw);
        mEditConfirmPsw = (EditText) findViewById(R.id.edit_son_confirm_psw);
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_son_regist);
    }

    @Override
    protected void initListener() {
        mLayout.setClick(new HorizontalBtnLayout.BtnLayoutClick() {
            @Override
            public void leftClick(View view) {

            }

            @Override
            public void rightClick(View view) {
                finish();
            }
        });
    }
}
