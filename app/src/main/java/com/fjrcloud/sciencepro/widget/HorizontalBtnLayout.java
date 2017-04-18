package com.fjrcloud.sciencepro.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.support.annotation.AttrRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.widget.Button;
import android.widget.FrameLayout;

import com.fjrcloud.sciencepro.R;

/**
 * Created by greedy on 17/4/1.
 */

public class HorizontalBtnLayout extends FrameLayout implements View.OnClickListener {

    private String mLeftText;
    private String mRightText;
    private BtnLayoutClick mClick;

    public HorizontalBtnLayout(@NonNull Context context) {
        this(context, null);
    }

    public HorizontalBtnLayout(@NonNull Context context, @Nullable AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public HorizontalBtnLayout(@NonNull Context context, @Nullable AttributeSet attrs, @AttrRes int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        initialize(context, attrs, defStyleAttr);
    }

    private void initialize(Context context, AttributeSet attrs, int defStyleAttr) {
        TypedArray array = context.obtainStyledAttributes(attrs, R.styleable.HorizontalBtnLayout, defStyleAttr, 0);
        int total = array.getIndexCount();
        for (int i = 0; i < total; i++) {
            int attr = array.getIndex(i);
            switch (attr) {
                case R.styleable.HorizontalBtnLayout_leftBtnText:
                    mLeftText = array.getString(attr);
                    break;
                case R.styleable.HorizontalBtnLayout_rightBtnText:
                    mRightText = array.getString(attr);
                    break;
                default:
                    break;
            }
        }
        array.recycle();

        inflate(context, R.layout.horizontal_custom_btn_layout, this);
        Button leftBtn = (Button) findViewById(R.id.custom_left_btn);
        Button rightBtn = (Button) findViewById(R.id.custom_right_btn);

        leftBtn.setText(mLeftText);
        rightBtn.setText(mRightText);
        leftBtn.setOnClickListener(this);
        rightBtn.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.custom_left_btn:
                if (mClick != null) {
                    mClick.leftClick(v);
                }
                break;
            case R.id.custom_right_btn:
                if (mClick != null) {
                    mClick.rightClick(v);
                }
                break;
            default:
                break;
        }
    }

    public void setClick(BtnLayoutClick click) {
        mClick = click;
    }

    public interface BtnLayoutClick {
        void leftClick(View view);

        void rightClick(View view);
    }
}
