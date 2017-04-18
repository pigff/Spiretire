package com.fjrcloud.sciencepro.ui.fragment;

import android.os.Bundle;
import android.view.View;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.UserMailboxAdapter;
import com.fjrcloud.sciencepro.data.net.LetterResponse;
import com.fjrcloud.sciencepro.ui.activity.LetterDetailedActivity;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greedy on 17/3/29.
 */

public class UserMailboxFragment extends BaseRecyclerFragment<LetterResponse.DataEntity.LetterEntity> {

    private static final String STATUS = "status";

    private int status;

    public UserMailboxFragment() {
        // Required empty public constructor
    }

    public static UserMailboxFragment newInstance(int status) {
        UserMailboxFragment fragment = new UserMailboxFragment();
        Bundle args = new Bundle();
        args.putInt(STATUS, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            status = getArguments().getInt(STATUS);
        }
    }

    @Override
    protected BaseQuickAdapter<LetterResponse.DataEntity.LetterEntity, BaseViewHolder> getRecyclerAdapter() {
        return new UserMailboxAdapter(R.layout.recycler_user_mailbox_item);
    }

    @Override
    protected void getData() {
        List<LetterResponse.DataEntity.LetterEntity> entities = new ArrayList<>();
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        mAdapter.setNewData(entities);
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                openActivity(LetterDetailedActivity.class);
            }
        });
    }
}
