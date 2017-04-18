package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;
import com.fjrcloud.sciencepro.widget.EditTextWithDel;

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
