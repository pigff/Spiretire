package com.fjrcloud.sciencepro.ui.activity;

import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

/**
 * 资质 长度 - -
 */
public class NatureLengthActivity extends BaseToolbarActivity implements View.OnClickListener {

    private EditText mNatureAbridgeEdit;
    private EditText mNatureFullNameEdit;
    private EditText mNutureStartDateEdit;
    private ImageView mStartDateIv;
    private EditText mNatureEndDateEdit;
    private ImageView mEndDateIv;
    private HorizontalBtnLayout mLayout;


    @Override
    public void initData() {

    }

    @Override
    public void initView() {
        setTitle("资质");
        mNatureAbridgeEdit = (EditText) findViewById(R.id.edit_nature_abridge);
        mNatureFullNameEdit = (EditText) findViewById(R.id.edit_nature_full_name);
        mNutureStartDateEdit = (EditText) findViewById(R.id.edit_nuture_start_date);
        mStartDateIv = (ImageView) findViewById(R.id.iv_nature_start_date);
        mNatureEndDateEdit = (EditText) findViewById(R.id.edit_nature_end_date);
        mEndDateIv = (ImageView) findViewById(R.id.iv_nature_end_date);
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_nature_length);
    }

    @Override
    protected void initListener() {
        mStartDateIv.setOnClickListener(this);
        mEndDateIv.setOnClickListener(this);
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_nature_length;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_nature_start_date:
                openActivity(ChooseTimeActivity.class);
                break;
            case R.id.iv_nature_end_date:
                openActivity(ChooseTimeActivity.class);
                break;
            default:
                break;
        }
    }
}
