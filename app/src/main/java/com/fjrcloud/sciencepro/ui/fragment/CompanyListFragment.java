package com.fjrcloud.sciencepro.ui.fragment;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.EnterpriseEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

import org.greenrobot.eventbus.Subscribe;


public class CompanyListFragment extends BaseRecyclerFragment<EnterpriseEntity> {


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
    protected BaseQuickAdapter<EnterpriseEntity, BaseViewHolder> getRecyclerAdapter() {
        return new CompanyListAdapter(R.layout.recycler_company_list_item);
    }

    @Override
    protected void getData() {
        ScieneManager.enterpriseFindAll(new SimpleSubscriber<>(getSimpleListener()), mPageNum, mPageSize);
    }


    @Override
    protected boolean registEventBus() {
        return false;
    }

    @Subscribe          //订阅事件FirstEvent
    public  void onEventMainThread(String[] filter){
        for (String msg : filter) {
            Log.d(TAG, msg);
        }
    }

    private class CompanyListAdapter extends BaseQuickAdapter<EnterpriseEntity, BaseViewHolder> {

        CompanyListAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, EnterpriseEntity item) {
            ImageView imageView = helper.getView(R.id.iv_avatar_company_list);
            helper.setText(R.id.tv_name_company_list, item.getName())
//                    .setText(R.id.tv_industry_company_list, item.getEnterpriseCategorySet())
//                    .setText(R.id.tv_nature_company_list, item.getNature())
                    .addOnClickListener(R.id.company_list_group);
            ImgLoadUtils.loadUrl(mContext, Constants.BASE_IMG_URL + item.getImagePath(), imageView);
        }
    }

}
