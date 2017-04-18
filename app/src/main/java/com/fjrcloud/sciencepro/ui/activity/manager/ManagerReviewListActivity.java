package com.fjrcloud.sciencepro.ui.activity.manager;

import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 管理审核
 */
public class ManagerReviewListActivity extends BaseRecyclerActivity<ManagerReviewListActivity.CompanyMsg> {


    @Override
    protected String getToolbarTitle() {
        return "企业审核";
    }

    @Override
    protected BaseQuickAdapter<CompanyMsg, BaseViewHolder> getAdapter() {
        return new ManagerReviewAdapter(R.layout.recycler_review_company_item);
    }

    @Override
    protected void getData() {
        List<CompanyMsg> companyMsgs = new ArrayList<>();
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        companyMsgs.add(new CompanyMsg());
        mAdapter.setNewData(companyMsgs);
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                openActivity(ReviewCompanyActivity.class);
            }
        });
    }

    private class ManagerReviewAdapter extends BaseQuickAdapter<CompanyMsg, BaseViewHolder> {

        public ManagerReviewAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, CompanyMsg item) {
            helper.setText(R.id.tv_review_company_name, item.getName())
                    .setText(R.id.tv_review_company_date, "提交时间：" + item.getDate())
                    .addOnClickListener(R.id.review_company_group);
        }
    }

    class CompanyMsg {
        private String name;
        private String date;

        public CompanyMsg(String name, String date) {
            this.name = name;
            this.date = date;
        }

        public CompanyMsg() {
            this.name = "福耀玻璃工业集团有限公司";
            this.date = "2017-1-1";
        }

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }
    }
}
