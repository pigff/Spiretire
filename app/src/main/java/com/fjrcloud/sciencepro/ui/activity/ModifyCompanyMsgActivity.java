package com.fjrcloud.sciencepro.ui.activity;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.View;
import android.widget.Toast;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.RegisterMultiAdapter;
import com.fjrcloud.sciencepro.data.multi.CommonMulti;
import com.fjrcloud.sciencepro.data.multi.LoginItemMulti;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.widget.HorizontalBtnLayout;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 修改企业信息
 */
public class ModifyCompanyMsgActivity extends BaseToolbarActivity {

    private HorizontalBtnLayout mLayout;
    private List<MultiItemEntity> mMultiItemEntities;
    private Map<String, String> mMap;
    private RegisterMultiAdapter mAdapter;
    private RecyclerView mRecyclerView;


    @Override
    protected int provideContentView() {
        return R.layout.activity_modify_company_msg;
    }

    @Override
    protected void initAdapter() {
        mAdapter = new RegisterMultiAdapter(mMultiItemEntities);
    }

    @Override
    public void initData() {
        mMultiItemEntities = new ArrayList<>();
        mMap = new HashMap<>();
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.TITLE, "必填"));
        mMultiItemEntities.add(LoginItemMulti.testData("hangye", RegisterMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.DIVIDING));
        mMultiItemEntities.add(LoginItemMulti.testData("name", RegisterMultiAdapter.LOGIN_EDIT_TEXT));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.IMAGE));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.FILE));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.DIVIDING));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.TITLE, "选填"));
        mMultiItemEntities.add(LoginItemMulti.testData("long", RegisterMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.DIVIDING));
        mMultiItemEntities.add(LoginItemMulti.testData("short", RegisterMultiAdapter.LOGIN_TEXT));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.ALL_DELETE));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.IMAGE));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.FILE));
        mMultiItemEntities.add(new CommonMulti(RegisterMultiAdapter.DIVIDING));
    }

    @Override
    public void initView() {
        setTitle("企业信息");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_modify_company_msg);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
        mLayout = (HorizontalBtnLayout) findViewById(R.id.layout_modify_company);
    }

    @Override
    protected void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                switch (adapter.getItemViewType(position)) {
                    case RegisterMultiAdapter.LOGIN_TEXT:
                        if (adapter.getItem(position) instanceof LoginItemMulti) {
                            String text = ((LoginItemMulti) adapter.getItem(position)).getTag();
                            if (TextUtils.equals("hangye", text)) {
                                openActivity(SelectIndusActivity.class);
                            }
                        }
                        break;
                    case RegisterMultiAdapter.LOGIN_EDIT_TEXT:
                        Toast.makeText(ModifyCompanyMsgActivity.this, "附件", Toast.LENGTH_SHORT).show();
                        break;
                    case RegisterMultiAdapter.ALL_DELETE:
                        Toast.makeText(ModifyCompanyMsgActivity.this, "删除", Toast.LENGTH_SHORT).show();
                        break;
                    case RegisterMultiAdapter.IMAGE:
                        Toast.makeText(ModifyCompanyMsgActivity.this, "删除", Toast.LENGTH_SHORT).show();
                        break;
                    case RegisterMultiAdapter.FILE:
                        Toast.makeText(ModifyCompanyMsgActivity.this, "删除", Toast.LENGTH_SHORT).show();
                    default:
                        break;
                }
            }
        });


        mLayout.setClick(new HorizontalBtnLayout.BtnLayoutClick() {
            @Override
            public void leftClick(View view) {

            }

            @Override
            public void rightClick(View view) {
                finish();
            }
        });

        mAdapter.setEditChangeListener(new RegisterMultiAdapter.EditChangeListener() {
            @Override
            public void common(String tag, String content) {
                mMap.put(tag, content);
            }
        });
    }
}
