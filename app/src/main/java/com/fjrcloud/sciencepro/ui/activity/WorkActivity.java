package com.fjrcloud.sciencepro.ui.activity;

import android.content.Intent;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.data.WorkZipItem;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;

import java.util.ArrayList;
import java.util.List;

/**
 * 办事指南
 */
public class WorkActivity extends BaseToolbarActivity{

    private RecyclerView mRecyclerView;
    private WorkAdapter mAdapter;
    private List<WorkZipItem> mWorkZipItems;

    @Override
    protected int provideContentView() {
        return R.layout.activity_work;
    }

    @Override
    public void initData() {
        mWorkZipItems = new ArrayList<>();
        List<SciencePolicyResponse.SciencePolicy> policies1 = new ArrayList<>();
        policies1.add(new SciencePolicyResponse.SciencePolicy("假冒专利", "2013-09-09"));
        List<SciencePolicyResponse.SciencePolicy> policies2 = new ArrayList<>();
        policies2.add(new SciencePolicyResponse.SciencePolicy("假冒专利", "2013-09-09"));
        mWorkZipItems.add(new WorkZipItem("项目申报", policies1));
        mWorkZipItems.add(new WorkZipItem("项目验收", policies2));
    }

    @Override
    public void initAdapter() {
        mAdapter = new WorkAdapter(R.layout.recycler_work_card_item, mWorkZipItems);
    }

    @Override
    public void initView() {
        setTitle("办事指南");
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_work);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(WorkActivity.this, LinearLayoutManager.VERTICAL, false));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                WorkZipItem item = (WorkZipItem) adapter.getItem(position);
                Intent intent2Detailed = null;
                switch (view.getId()) {
                    case R.id.tv_more_work_card_item:
                        Intent intent2WorkList = WorkListActivity.newInstance(WorkActivity.this, item.getCategoryName());
                        startActivity(intent2WorkList);
                        break;
                    case R.id.first_work_card_group:
//                        Toast.makeText(getActivity(), item.getSciencePolicies().get(0).getTitle(), Toast.LENGTH_SHORT).show();
                        intent2Detailed = new Intent(WorkActivity.this, WorkDetailedActivity.class);
                        startActivity(intent2Detailed);
                        break;
                    case R.id.second_work_card_group:
//                        Toast.makeText(getActivity(), item.getSciencePolicies().get(1).getTitle(), Toast.LENGTH_SHORT).show();
                        intent2Detailed = new Intent(WorkActivity.this, WorkDetailedActivity.class);
                        startActivity(intent2Detailed);
                        break;
                    case R.id.third_work_card_group:
//                        Toast.makeText(getActivity(), item.getSciencePolicies().get(2).getTitle(), Toast.LENGTH_SHORT).show();
                        intent2Detailed = new Intent(WorkActivity.this, WorkDetailedActivity.class);
                        startActivity(intent2Detailed);
                        break;
                    case R.id.fourth_work_card_group:
//                        Toast.makeText(getActivity(), item.getSciencePolicies().get(3).getTitle(), Toast.LENGTH_SHORT).show();
                        intent2Detailed = new Intent(WorkActivity.this, WorkDetailedActivity.class);
                        startActivity(intent2Detailed);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private class WorkAdapter extends BaseQuickAdapter<WorkZipItem, BaseViewHolder> {

        WorkAdapter(int layoutResId, List<WorkZipItem> data) {
            super(layoutResId, data);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkZipItem item) {
            helper.setText(R.id.tv_name_work_card_item, item.getCategoryName())
                    .addOnClickListener(R.id.tv_more_work_card_item);
            int size = item.getSciencePolicies().size();
            switch (size) {
                case 0:
                    helper.setVisible(R.id.line_first_work_card, false);
                    break;
                case 1:
                    helper.setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.line_second_work_card, false)
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getTitle())
                            .setText(R.id.tv_first_date_work_card_item, item.getSciencePolicies().get(0).getDate())
                            .addOnClickListener(R.id.first_work_card_group);
                    break;
                case 2:
                    helper.setVisible(R.id.second_work_card_group, true)
                            .setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.line_third_work_card, false)
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getTitle())
                            .setText(R.id.tv_second_date_work_card_item, item.getSciencePolicies().get(1).getDate())
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getTitle())
                            .setText(R.id.tv_first_date_work_card_item, item.getSciencePolicies().get(0).getDate())
                            .addOnClickListener(R.id.first_work_card_group)
                            .addOnClickListener(R.id.second_work_card_group);
                    break;
                case 3:
                    helper.setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.second_work_card_group, true)
                            .setVisible(R.id.third_work_card_group, true)
                            .setVisible(R.id.line_fourth_work_card, false)
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getTitle())
                            .setText(R.id.tv_first_date_work_card_item, item.getSciencePolicies().get(0).getDate())
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getTitle())
                            .setText(R.id.tv_second_date_work_card_item, item.getSciencePolicies().get(1).getDate())
                            .setText(R.id.tv_third_title_work_card_item, item.getSciencePolicies().get(2).getTitle())
                            .setText(R.id.tv_third_date_work_card_item, item.getSciencePolicies().get(2).getDate())
                            .addOnClickListener(R.id.first_work_card_group)
                            .addOnClickListener(R.id.second_work_card_group)
                            .addOnClickListener(R.id.third_work_card_group);
                    break;
                case 4:
                    helper.setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.second_work_card_group, true)
                            .setVisible(R.id.third_work_card_group, true)
                            .setVisible(R.id.fourth_work_card_group, true)
                            .setVisible(R.id.line_fifth_work_card, false)
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getTitle())
                            .setText(R.id.tv_first_date_work_card_item, item.getSciencePolicies().get(0).getDate())
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getTitle())
                            .setText(R.id.tv_second_date_work_card_item, item.getSciencePolicies().get(1).getDate())
                            .setText(R.id.tv_third_title_work_card_item, item.getSciencePolicies().get(2).getTitle())
                            .setText(R.id.tv_third_date_work_card_item, item.getSciencePolicies().get(2).getDate())
                            .setText(R.id.tv_fourth_title_work_card_item, item.getSciencePolicies().get(3).getTitle())
                            .setText(R.id.tv_fourth_date_work_card_item, item.getSciencePolicies().get(3).getDate())
                            .addOnClickListener(R.id.first_work_card_group)
                            .addOnClickListener(R.id.second_work_card_group)
                            .addOnClickListener(R.id.third_work_card_group)
                            .addOnClickListener(R.id.fourth_work_card_group);
                    break;
                default:
                    break;
            }
        }
    }
}
