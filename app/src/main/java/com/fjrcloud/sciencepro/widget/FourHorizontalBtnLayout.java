package com.fjrcloud.sciencepro.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fjrcloud.sciencepro.R;


/**
 * Created by greedy on 17/3/11.
 */

public class FourHorizontalBtnLayout extends FrameLayout implements View.OnClickListener {

    private Button mAllBtn;
    private Button mWaitBtn;
    private Button mFinishBtn;
    private Button mFinishedBtn;
    private int mPosition;
    private String mFirstBtnName;
    private String mMiddleBtnName;
    private String mLastBtnName;
    private String mMiddleBtnTwoName;
    private View mView;

    public FourHorizontalBtnLayout(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public FourHorizontalBtnLayout(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.FourHorizontalBtnLayout, defStyleAttr, 0);
        int total = array.getIndexCount();
        for (int i = 0; i < total; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.FourHorizontalBtnLayout_firstBtnTextName_four:
                    mFirstBtnName = array.getString(R.styleable.FourHorizontalBtnLayout_firstBtnTextName_four);
                    break;
                case R.styleable.FourHorizontalBtnLayout_middleBtnTextName_four:
                    mMiddleBtnName = array.getString(R.styleable.FourHorizontalBtnLayout_middleBtnTextName_four);
                    break;
                case R.styleable.FourHorizontalBtnLayout_middleTwoBtnTextName_four:
                    mMiddleBtnTwoName = array.getString(R.styleable.FourHorizontalBtnLayout_middleTwoBtnTextName_four);
                    break;
                case R.styleable.FourHorizontalBtnLayout_lastBtnTextName_four:
                    mLastBtnName = array.getString(R.styleable.FourHorizontalBtnLayout_lastBtnTextName_four);
                    break;
                default:
                    break;
            }
        }
        array.recycle();

        mPosition = 0;
        inflate(context, R.layout.horizontal_four_btn_custom_item, this);
        mAllBtn = (Button) findViewById(R.id.four_btn_horizontal_all);
        mWaitBtn = (Button) findViewById(R.id.four_btn_horizontal_wait);
        mFinishBtn = (Button) findViewById(R.id.four_btn_horizontal_finish);
        mFinishedBtn = (Button) findViewById(R.id.four_btn_horizontal_finished);
        mView = findViewById(R.id.last_line);

        mAllBtn.setText(mFirstBtnName);
        mWaitBtn.setText(mMiddleBtnName);
        mFinishBtn.setText(mMiddleBtnTwoName);
        mAllBtn.setTextColor(getColor(R.color.orange_248_color_code));
        if (!TextUtils.isEmpty(mLastBtnName)) {
            mFinishedBtn.setText(mLastBtnName);
            mFinishedBtn.setVisibility(VISIBLE);
            mView.setVisibility(VISIBLE);
        }


        mAllBtn.setOnClickListener(this);
        mWaitBtn.setOnClickListener(this);
        mFinishBtn.setOnClickListener(this);
        mFinishedBtn.setOnClickListener(this);
    }

    private int getColor(int src) {
        return ContextCompat.getColor(getContext(), src);
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.four_btn_horizontal_all:
                if (mPosition != 0) {
                    mWaitBtn.setTextColor(getColor(R.color.black));
                    mFinishBtn.setTextColor(getColor(R.color.black));
                    mFinishedBtn.setTextColor(getColor(R.color.black));
                    mAllBtn.setTextColor(getColor(R.color.orange_248_color_code));
                }
                mPosition = 0;
                break;
            case R.id.four_btn_horizontal_wait:
                if (mPosition != 1) {
                    mFinishBtn.setTextColor(getColor(R.color.black));
                    mFinishedBtn.setTextColor(getColor(R.color.black));
                    mAllBtn.setTextColor(getColor(R.color.black));
                    mWaitBtn.setTextColor(getColor(R.color.orange_248_color_code));
                }
                mPosition = 1;
                break;
            case R.id.four_btn_horizontal_finish:
                if (mPosition != 2) {
                    mWaitBtn.setTextColor(getColor(R.color.black));
                    mAllBtn.setTextColor(getColor(R.color.black));
                    mFinishedBtn.setTextColor(getColor(R.color.black));
                    mFinishBtn.setTextColor(getColor(R.color.orange_248_color_code));
                }
                mPosition = 2;
                break;
            case R.id.four_btn_horizontal_finished:
                if (mPosition != 3) {
                    mWaitBtn.setTextColor(getColor(R.color.black));
                    mAllBtn.setTextColor(getColor(R.color.black));
                    mFinishBtn.setTextColor(getColor(R.color.black));
                    mFinishedBtn.setTextColor(getColor(R.color.orange_248_color_code));
                }
                mPosition = 3;
                break;
            default:
                break;
        }
        if (mHoriBtnItemClick != null) {
            mHoriBtnItemClick.itemClick(v, mPosition);
        }
    }

    public interface HoriBtnItemClick {
        void itemClick(View view, int position);
    }

    public void setItemClick(HoriBtnItemClick itemClick) {
        mHoriBtnItemClick = itemClick;
    }

    private HoriBtnItemClick mHoriBtnItemClick;
}

