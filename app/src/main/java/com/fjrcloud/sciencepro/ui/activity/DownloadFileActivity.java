package com.fjrcloud.sciencepro.ui.activity;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.App;
import com.fjrcloud.sciencepro.DownloadService;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;
import com.fjrcloud.sciencepro.utils.FileUtil;
import com.fjrcloud.sciencepro.utils.IntentUtil;
import com.fjrcloud.sciencepro.widget.DialDialog;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * 文件下载
 */
public class DownloadFileActivity extends BaseToolbarActivity implements View.OnClickListener {

    private RecyclerView mRecyclerView;
    private LinearLayout mSearchLayout;
    private DownloadFileListAdapter mAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getData();
    }

    private void getData() {
        SimpleSubListener<List<FileEntity>> listener = new SimpleSubListener<List<FileEntity>>() {
            @Override
            public void onNext(List<FileEntity> fileEntities) {
                if (fileEntities.size() == 0) {
                    showEmpty();
                } else {
                    mAdapter.addData(fileEntities);
                }
            }

            @Override
            public void onError(Throwable throwable) {
                showError();
            }
        };

        ScieneManager.downloadFindAll(new SimpleSubscriber<>(listener), 0, 100000);
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_download_file;
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                final FileEntity item = (FileEntity) adapter.getItem(position);
                File file = new File(FileUtil.getDiskCacheDir(App.getInstance(), item.getName()));

                if (file.exists()) {
                    Intent intent = IntentUtil.openFile(FileUtil.getDiskCacheDir(App.getInstance(), item.getName()));
                    if (intent != null) {
                        startActivity(intent);
                    } else {
                        Toast.makeText(DownloadFileActivity.this, "不支持打开该类型的文件", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    download(item);
                }
            }
        });
    }

    private void download(final FileEntity item) {
        new DialDialog.Builder(DownloadFileActivity.this)
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
                            Toast.makeText(DownloadFileActivity.this, "正在下载中，请稍后", Toast.LENGTH_SHORT).show();
                        } else {
                            item.setDownload(true);
                            Intent intent2Download = new Intent(DownloadFileActivity.this, DownloadService.class);
                            intent2Download.putExtra(Constants.DATA, item);
                            startService(intent2Download);
                        }
                        dialog.dismiss();
                    }
                })
                .create()
                .show();
    }

    @Override
    public void initView() {
        setTitle("文件下载");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_download_file);
        mSearchLayout = (LinearLayout) findViewById(R.id.fake_search_group);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(DownloadFileActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
        mSearchLayout.setOnClickListener(this);
    }

    @Override
    public void initAdapter() {
        mAdapter = new DownloadFileListAdapter(R.layout.recycler_science_policy_item);
    }

    @Override
    public void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.fake_search_group:
                ArrayList<FileEntity> fileEntities = (ArrayList<FileEntity>) mAdapter.getData();
                Bundle bundle = new Bundle();
                bundle.putParcelableArrayList(Constants.DATA, fileEntities);
                openActivity(SearchActivity.class, bundle);
                break;
            default:
                break;
        }
    }

    @Override
    protected boolean hasBaseLayout() {
        return true;
    }

    private class DownloadFileListAdapter extends BaseQuickAdapter<FileEntity, BaseViewHolder> {


        public DownloadFileListAdapter(int layoutResId) {
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
