package com.fjrcloud.sciencepro.ui.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.SciencePolicyResponse;
import com.fjrcloud.sciencepro.data.WorkZipItem;
import com.fjrcloud.sciencepro.data.net.TypeEntity;
import com.fjrcloud.sciencepro.data.net.WorkEntity;
import com.fjrcloud.sciencepro.network.ScieneManager;
import com.fjrcloud.sciencepro.subscribers.CompleteSubListener;
import com.fjrcloud.sciencepro.subscribers.CompleteSubscriber;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;

import java.util.ArrayList;
import java.util.List;

import rx.Observable;
import rx.Subscriber;

/**
 * 办事指南
 */
public class WorkActivity extends BaseToolbarActivity {

    private RecyclerView mRecyclerView;
    private WorkAdapter mAdapter;
    private List<WorkZipItem> mWorkZipItems;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getData();
    }

    private void getData() {
        final CompleteSubListener<List<WorkEntity>> comListener = new CompleteSubListener<List<WorkEntity>>() {
            @Override
            public void onNext(List<WorkEntity> workEntities) {
                mWorkZipItems.add(new WorkZipItem(workEntities.get(0).getDepartment(), workEntities));
                Log.d(TAG, "onNext: " + workEntities);
            }

            @Override
            public void onError(Throwable throwable) {

            }

            @Override
            public void onCompleted() {
                mAdapter.addData(mWorkZipItems);
//                if (current < total) {
//                    addSubscription(ScieneManager.guideFindByDepartment(new CompleteSubscriber<>(this),
//                            mWorkZipItems.get(current).getTypeEntity().getId(), 0, 4));
//                } else {
//                    mAdapter.addData(mWorkZipItems);
//                }
            }
        };
        SimpleSubListener<List<TypeEntity>> listener = new SimpleSubListener<List<TypeEntity>>() {
            @Override
            public void onNext(List<TypeEntity> typeEntities) {
//                for (TypeEntity entity : typeEntities) {
//                    mWorkZipItems.add(new WorkZipItem(entity));
//                }
//                addSubscription(ScieneManager.guideFindByDepartment(new CompleteSubscriber<>(comListener),
//                        mWorkZipItems.get(current).getTypeEntity().getId(), 0, 4));
                List<Integer> integers = new ArrayList<>();
                for (TypeEntity entity : typeEntities) {
                    integers.add(entity.getId());
                }
                ScieneManager.fakeNetWork(new CompleteSubscriber<>(comListener), integers, 0, 4);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };


        addSubscription(ScieneManager.type1FindAll(new SimpleSubscriber<>(listener)));
    }


    @Override
    protected int provideContentView() {
        return R.layout.activity_work;
    }

    @Override
    public void initData() {
        mWorkZipItems = new ArrayList<>();
    }

    @Override
    public void initAdapter() {
        mAdapter = new WorkAdapter(R.layout.recycler_work_card_item);
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
                Bundle bundle = new Bundle();
                switch (view.getId()) {
                    case R.id.tv_more_work_card_item:
                        Intent intent2WorkList = WorkListActivity.newInstance(WorkActivity.this, item.getTypeEntity());
                        startActivity(intent2WorkList);
                        break;
                    case R.id.first_work_card_group:
                        bundle.putSerializable(Constants.DATA, item.getSciencePolicies().get(0));
                        openActivity(WorkDetailedActivity.class, bundle);
                        break;
                    case R.id.second_work_card_group:
                        bundle.putSerializable(Constants.DATA, item.getSciencePolicies().get(1));
                        openActivity(WorkDetailedActivity.class, bundle);
                        break;
                    case R.id.third_work_card_group:
                        bundle.putSerializable(Constants.DATA, item.getSciencePolicies().get(2));
                        openActivity(WorkDetailedActivity.class, bundle);
                        break;
                    case R.id.fourth_work_card_group:
                        bundle.putSerializable(Constants.DATA, item.getSciencePolicies().get(3));
                        openActivity(WorkDetailedActivity.class, bundle);
                        break;
                    default:
                        break;
                }

            }
        });
    }

    private class WorkAdapter extends BaseQuickAdapter<WorkZipItem, BaseViewHolder> {

        WorkAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, WorkZipItem item) {
            helper.setText(R.id.tv_name_work_card_item, item.getTypeEntity().getName())
                    .addOnClickListener(R.id.tv_more_work_card_item);
            int size = item.getSciencePolicies().size();
            switch (size) {
                case 0:
                    helper.setVisible(R.id.line_first_work_card, false);
                    break;
                case 1:
                    helper.setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.line_second_work_card, false)
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getName())
                            .setText(R.id.tv_first_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(0).getCreateTime()))
                            .addOnClickListener(R.id.first_work_card_group);
                    break;
                case 2:
                    helper.setVisible(R.id.second_work_card_group, true)
                            .setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.line_third_work_card, false)
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getName())
                            .setText(R.id.tv_second_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(1).getCreateTime()))
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getName())
                            .setText(R.id.tv_first_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(0).getCreateTime()))
                            .addOnClickListener(R.id.first_work_card_group)
                            .addOnClickListener(R.id.second_work_card_group);
                    break;
                case 3:
                    helper.setVisible(R.id.first_work_card_group, true)
                            .setVisible(R.id.second_work_card_group, true)
                            .setVisible(R.id.third_work_card_group, true)
                            .setVisible(R.id.line_fourth_work_card, false)
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getName())
                            .setText(R.id.tv_first_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(0).getCreateTime()))
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getName())
                            .setText(R.id.tv_second_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(1).getCreateTime()))
                            .setText(R.id.tv_third_title_work_card_item, item.getSciencePolicies().get(2).getName())
                            .setText(R.id.tv_third_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(2).getCreateTime()))
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
                            .setText(R.id.tv_first_title_work_card_item, item.getSciencePolicies().get(0).getName())
                            .setText(R.id.tv_first_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(0).getCreateTime()))
                            .setText(R.id.tv_second_title_work_card_item, item.getSciencePolicies().get(1).getName())
                            .setText(R.id.tv_second_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(1).getCreateTime()))
                            .setText(R.id.tv_third_title_work_card_item, item.getSciencePolicies().get(2).getName())
                            .setText(R.id.tv_third_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(2).getCreateTime()))
                            .setText(R.id.tv_fourth_title_work_card_item, item.getSciencePolicies().get(3).getName())
                            .setText(R.id.tv_fourth_date_work_card_item, DateUtil.getDateToString(item.getSciencePolicies().get(3).getCreateTime()))
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
