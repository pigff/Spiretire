package com.fjrcloud.sciencepro.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;


/**
 * Created by lin on 2016/8/10.
 */
public class EditWhitePopWindow extends PopupWindow{

    private View contentView;
    public Button mButton;
    public EditText mEditText;
    private EditPopClick mClick;
    public ImageButton mImageButton;

    public EditWhitePopWindow(final Activity context) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.inter_popup_edit_window, null);
        Point x = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(x);
        int h = x.y;
        int w = x.x;
        setContentView(contentView);
        setHeight(ViewGroup.LayoutParams.WRAP_CONTENT);
        setWidth(ViewGroup.LayoutParams.MATCH_PARENT);
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        ColorDrawable dw = new ColorDrawable(00000000);
        setBackgroundDrawable(dw);
        setAnimationStyle(R.style.editpopwindow_anim_style);
        mEditText = (EditText) contentView.findViewById(R.id.pop_white_edit);
        mButton = (Button) contentView.findViewById(R.id.pop_white_btn);
//        TextView showNotice = (TextView) contentView.findViewById(R.id.white_edit_notice);
//        showNotice.setText("@功能使用: @+用户名称+空格+内容");
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String content = String.valueOf(mEditText.getText());
                if (mClick != null) {
                    mClick.popClick(content);
                }
            }
        });
        TextView textView = (TextView) contentView.findViewById(R.id.cancel_popwindow);
        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
    }

    public void showPoppupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.BOTTOM, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public void setPopClick(EditPopClick click) {
        this.mClick = click;
    }

    public interface EditPopClick {
        void popClick(String content);

    }

    @Override
    public void dismiss() {
        super.dismiss();
    }
}
