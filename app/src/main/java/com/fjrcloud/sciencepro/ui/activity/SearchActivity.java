package com.fjrcloud.sciencepro.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.App;
import com.fjrcloud.sciencepro.DownloadService;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;
import com.fjrcloud.sciencepro.utils.FileUtil;
import com.fjrcloud.sciencepro.utils.IntentUtil;
import com.fjrcloud.sciencepro.widget.DialDialog;
import com.fjrcloud.sciencepro.widget.EditTextWithDel;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 搜索
 */
public class SearchActivity extends BaseToolbarActivity {

    private SearchAdapter mAdapter;
    private RecyclerView mRecyclerView;
    /**
     * 请输入关键字...
     */
    private EditTextWithDel mEditCommonSearch;
    private List<FileEntity> mFileEntities;
    private List<String> mNames;
    private List<Integer> mIndexs;


    private void getData(String keyWord) {
        for (int i = 0; i < mNames.size(); i++) {
            if (mNames.get(i).contains(keyWord) && !TextUtils.isEmpty(keyWord)) {
                mIndexs.add(i);
            }
        }
        if (mIndexs.size() >= 0) {
            List<FileEntity> fileEntities = new ArrayList<>();
            for (int index : mIndexs) {
                fileEntities.add(mFileEntities.get(index));
            }
            mAdapter.setNewData(fileEntities);
            mIndexs.clear();
        }
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_search;
    }

    @Override
    public void initData() {
        mFileEntities = getBundleData().getParcelableArrayList(Constants.DATA);
        mNames = new ArrayList<>();
        for (FileEntity entity : mFileEntities) {
            mNames.add(entity.getName());
        }
        mIndexs = new ArrayList<>();
    }

    @Override
    public void initAdapter() {
        mAdapter = new SearchAdapter(R.layout.recycler_science_policy_item);
    }

    @Override
    public void initView() {
        setTitle("搜索");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_search);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mEditCommonSearch = (EditTextWithDel) findViewById(R.id.edit_common_search);
    }

    @Override
    public void initListener() {
        mEditCommonSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                String keyWord = String.valueOf(s);
                getData(keyWord);
            }
        });

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter baseQuickAdapter, View view, int i) {
                FileEntity item = (FileEntity) baseQuickAdapter.getItem(i);
                File file = new File(FileUtil.getDiskCacheDir(App.getInstance(), item.getName()));

                if (file.exists()) {
                    Intent intent = IntentUtil.openFile(FileUtil.getDiskCacheDir(App.getInstance(), item.getName()));
                    if (intent != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(SearchActivity.this, "不支持打开该类型的文件", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    download(item);
                }
            }
        });
    }

    private void download(final FileEntity item) {
        new DialDialog.Builder(SearchActivity.this)
                .setMessage("是否要下载该文件")
                .setNegativeButton("取消", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.dismiss();
                    }
                })
                .setPositiveButton("确定", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        if (item.isDownload()) {
                            Toast.makeText(SearchActivity.this, "正在下载中，请稍后", Toast.LENGTH_SHORT).show();
                        } else {
                            item.setDownload(true);
                            Intent intent2Download = new Intent(SearchActivity.this, DownloadService.class);
                            intent2Download.putExtra(Constants.DATA, item);
                            startService(intent2Download);
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    private class SearchAdapter extends BaseQuickAdapter<FileEntity, BaseViewHolder> {

        SearchAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, FileEntity item) {
            helper.setText(R.id.tv_title_science_policy, item.getName())
                    .setText(R.id.tv_date_science_policy, DateUtil.getDateToString(item.getCreateTime()))
                    .addOnClickListener(R.id.science_policy_group);
        }
    }
}
