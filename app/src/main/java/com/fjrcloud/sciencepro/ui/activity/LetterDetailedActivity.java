package com.fjrcloud.sciencepro.ui.activity;

import android.content.DialogInterface;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.LetterMultiAdapter;
import com.fjrcloud.sciencepro.data.multi.LetterMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.DialDialog;

import java.util.ArrayList;
import java.util.List;

/**
 * 信件具体
 */
public class LetterDetailedActivity extends BaseToolbarActivity {


    private List<LetterMulti> mMultis;
    private LetterMultiAdapter mAdapter;
    private RecyclerView mRecyclerView;
    private Button mButton;

    @Override
    protected int provideContentView() {
        return R.layout.activity_letter_detailed;
    }

    @Override
    public void initData() {
        mMultis = new ArrayList<>();
        mMultis.add(new LetterMulti(LetterMultiAdapter.HEADER));
        mMultis.add(new LetterMulti(LetterMultiAdapter.IMG));
        mMultis.add(new LetterMulti(LetterMultiAdapter.IMG));
        mMultis.add(new LetterMulti(LetterMultiAdapter.IMG, true));
        mMultis.add(new LetterMulti(LetterMultiAdapter.FILE));
        mMultis.add(new LetterMulti(LetterMultiAdapter.FILE));
        mMultis.add(new LetterMulti(LetterMultiAdapter.FILE));
        mMultis.add(new LetterMulti(LetterMultiAdapter.DIVIDING));
        mMultis.add(new LetterMulti(LetterMultiAdapter.BODY));
        mMultis.add(new LetterMulti(LetterMultiAdapter.DIVIDING));
        mMultis.add(new LetterMulti(LetterMultiAdapter.BODY));
        mMultis.add(new LetterMulti(LetterMultiAdapter.IMG));
        mMultis.add(new LetterMulti(LetterMultiAdapter.IMG));
    }

    @Override
    public void initAdapter() {
        mAdapter = new LetterMultiAdapter(mMultis);
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return mMultis.get(position).getSpanSize();
            }
        });
    }

    @Override
    public void initView() {
        setTitle("公众信箱");
        setRightTv("处理", new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                new DialDialog.Builder(LetterDetailedActivity.this)
                        .setMessage("该信件是否处理完毕?")
                        .setNegativeButtonColor(R.color.grey_191_color_code)
                        .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        })
                        .setPositiveButtonColor(R.color.black_80_color_code)
                        .setPositiveButton("完成", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.dismiss();
                            }
                        }).create().show();
            }
        });
        mButton = (Button) findViewById(R.id.btn_letter_detailed);
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_letter_detailed);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(this, 3));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openActivity(UserReplyActivity.class);
            }
        });
    }
}
