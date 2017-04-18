package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.CompanyResponse;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

import org.greenrobot.eventbus.Subscribe;

import java.util.ArrayList;
import java.util.List;


public class CompanyListFragment extends BaseRecyclerFragment<CompanyResponse.Company> {

    private List<CompanyResponse.Company> mCompanies;

    public CompanyListFragment() {
        // Required empty public constructor
    }

    public static CompanyListFragment newInstance() {
        CompanyListFragment fragment = new CompanyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
//                ScienceDynamicResponse.ScienceDynamic scienceDynamic = ((ScienceDynamicResponse.ScienceDynamic) adapter.getItem(position));
//                Toast.makeText(getActivity(), scienceDynamic.getTitle(), Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    protected BaseQuickAdapter<CompanyResponse.Company, BaseViewHolder> getRecyclerAdapter() {
        return new CompanyListAdapter(R.layout.recycler_company_list_item, mCompanies);
    }

    @Override
    protected void getData() {
        mCompanies = new ArrayList<>();
        mCompanies.add(new CompanyResponse.Company("福耀玻璃工业集团股份有限公司", "http://img.mp.itc.cn/upload/20160412/ca237dbb519d4211bc4d5a835f9c04b9.jpg", "玻璃", "高新"));
        mAdapter.setNewData(mCompanies);
    }


    @Override
    protected boolean registEventBus() {
        return true;
    }

    @Subscribe          //订阅事件FirstEvent
    public  void onEventMainThread(String[] filter){
        for (String msg : filter) {
            Log.d(TAG, msg);
        }
    }

    private class CompanyListAdapter extends BaseQuickAdapter<CompanyResponse.Company, BaseViewHolder> {

        CompanyListAdapter(int layoutResId, List<CompanyResponse.Company> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, CompanyResponse.Company item) {
            ImageView imageView = helper.getView(R.id.iv_avatar_company_list);
            helper.setText(R.id.tv_name_company_list, item.getCompanyName())
                    .setText(R.id.tv_industry_company_list, item.getIndustry())
                    .setText(R.id.tv_nature_company_list, item.getNature())
                    .addOnClickListener(R.id.company_list_group);
            ImgLoadUtils.loadUrl(mContext, item.getCompanyAvatar(), imageView);
        }
    }

}
