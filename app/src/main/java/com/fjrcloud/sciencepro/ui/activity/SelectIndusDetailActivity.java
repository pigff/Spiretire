package com.fjrcloud.sciencepro.ui.activity;

import android.view.View;
import android.widget.EditText;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

/**
 * 选择行业具体
 */
public class SelectIndusDetailActivity extends BaseToolbarActivity {

    private EditText mAbridgeEdit;
    private EditText mFullNameEdit;
    private HorizontalBtnLayout mLayout;

    @Override
    protected int provideContentView() {
        return R.layout.activity_select_indus_detail;
    }

    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setTitle("行业");
        mAbridgeEdit = (EditText) findViewById(R.id.edit_indus_abridge);
        mFullNameEdit = (EditText) findViewById(R.id.edit_indus_full_name);
        mAbridgeEdit.setEnabled(false);
        mFullNameEdit.setEnabled(false);
        mAbridgeEdit.setText("嘻嘻嘻");
        mFullNameEdit.setText("呵呵呵");
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_select_indus_detail);
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
