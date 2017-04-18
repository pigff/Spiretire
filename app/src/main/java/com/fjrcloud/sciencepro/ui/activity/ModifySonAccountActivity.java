package com.fjrcloud.sciencepro.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

/**
 * 修改子账号
 */
public class ModifySonAccountActivity extends BaseToolbarActivity {

    /**
     * 企业子账号: 肯德基疯狂的减肥
     */
    private TextView mAccountNameTv;
    private EditText mModifyPswEdit;
    private EditText mConfirmPswEdit;
    private HorizontalBtnLayout mLayout;

    @Override
    protected int provideContentView() {
        return R.layout.activity_modify_son_account;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setTitle("子账号修改");
        mAccountNameTv = (TextView) findViewById(R.id.tv_modify_son_account);
        mModifyPswEdit = (EditText) findViewById(R.id.edit_modify_son_psw);
        mConfirmPswEdit = (EditText) findViewById(R.id.edit_modify_son_confirm_psw);
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_son_modify_account);
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
