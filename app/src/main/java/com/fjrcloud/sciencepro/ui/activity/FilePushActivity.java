package com.fjrcloud.sciencepro.ui.activity;

import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.activity.filepush.FileContainerActivity;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.ArrayList;
import java.util.List;

public class FilePushActivity extends BaseToolbarActivity implements View.OnClickListener {

    public static final int INBOX = 0;
    private LinearLayout mFakeFileSearchGroup;

    private TextView mFilePushLabel01;
    private LinearLayout mGroupInbox;

    private TextView mFilePushLabel02;
    private LinearLayout mFileDraftBox;

    private TextView mFilePushLabel03;
    private LinearLayout mGroupFileSended;

    private TextView mFilePushLabel04;
    private LinearLayout mGroupFileDeled;

    private TextView mFilePushLabel05;
    private LinearLayout mGroupFeedback;

    private TextView mFilePushLabel06;
    private LinearLayout mFileWarning;

    private List<TextView> mViews;


    @Override
    protected int provideContentView() {
        return R.layout.activity_file_push;
    }

    @Override
    public void initData() {
        mViews = new ArrayList<>();
    }

    @Override
    public void initView() {
        setTitle("文件推送");
        setRightTv("写信", this);
        mFakeFileSearchGroup = (LinearLayout) findViewById(R.id.fake_file_search_group);
        mFakeFileSearchGroup.setOnClickListener(this);
        mFilePushLabel01 = (TextView) findViewById(R.id.file_push_label_01);
        mGroupInbox = (LinearLayout) findViewById(R.id.group_inbox);
        mGroupInbox.setOnClickListener(this);
        mFilePushLabel02 = (TextView) findViewById(R.id.file_push_label_02);
        mFileDraftBox = (LinearLayout) findViewById(R.id.file_draft_box);
        mFileDraftBox.setOnClickListener(this);
        mFilePushLabel03 = (TextView) findViewById(R.id.file_push_label_03);
        mGroupFileSended = (LinearLayout) findViewById(R.id.group_file_sended);
        mGroupFileSended.setOnClickListener(this);
        mFilePushLabel04 = (TextView) findViewById(R.id.file_push_label_04);
        mGroupFileDeled = (LinearLayout) findViewById(R.id.group_file_deled);
        mGroupFileDeled.setOnClickListener(this);
        mFilePushLabel05 = (TextView) findViewById(R.id.file_push_label_05);
        mGroupFeedback = (LinearLayout) findViewById(R.id.group_feedback);
        mGroupFeedback.setOnClickListener(this);
        mFilePushLabel06 = (TextView) findViewById(R.id.file_push_label_06);
        mFileWarning = (LinearLayout) findViewById(R.id.file_warning);
        mFileWarning.setOnClickListener(this);

        mViews.add(mFilePushLabel01);
        mViews.add(mFilePushLabel02);
        mViews.add(mFilePushLabel03);
        mViews.add(mFilePushLabel04);
        mViews.add(mFilePushLabel05);
        mViews.add(mFilePushLabel06);

        setAllNumber(new ArrayList<Integer>());
        setSpecifyNumber(3, 10);
        setSpecifyNumber(2, 7878);
    }

    @Override
    public void onClick(View v) {
        int category = -1;
        switch (v.getId()) {
            case R.id.fake_file_search_group:
                break;
            case R.id.group_inbox:
                category = INBOX;
                break;
            case R.id.file_draft_box:
                category = 1;
                break;
            case R.id.group_file_sended:
                category = 2;
                break;
            case R.id.group_file_deled:
                category = 3;
                break;
            case R.id.group_feedback:
                category = 4;
                break;
            case R.id.file_warning:
                category = 5;
                break;
            case R.id.right_tv_toolbar:
                break;
            default:
                break;
        }
        if (category != -1) {
            Bundle bundle = new Bundle();
            bundle.putInt(Constants.DATA, category);
            openActivity(FileContainerActivity.class, bundle);
        }
    }

    private void setSpecifyNumber(int index, int number) {
        if (index > mViews.size() || index < 0) {
            return;
        }
        if (!mViews.get(index).isShown()) {
            mViews.get(index).setVisibility(View.VISIBLE);
        }
        if (number > 99) {
            mViews.get(index).setText("99+");
        } else {
            mViews.get(index).setText(String.valueOf(number));
        }
    }

    private void setAllNumber(List<Integer> numbers) {
        if (numbers.size() == 0) {
            for (int i = 0; i < mViews.size(); i++) {
                mViews.get(i).setVisibility(View.INVISIBLE);
            }
            return;
        }
        if (numbers.size() != mViews.size()) {
            return;
        }
        for (int i = 0; i < numbers.size(); i++) {
            int number = numbers.get(i);
            if (!mViews.get(i).isShown()) {
                mViews.get(i).setVisibility(View.VISIBLE);
            }
            if (number > 99) {
                mViews.get(i).setText("99+");
            } else {
                mViews.get(i).setText(String.valueOf(number));
            }
        }
    }
}
