package com.fjrcloud.sciencepro.ui.fragment;

import android.os.Bundle;
import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.LetterResponse;
import com.fjrcloud.sciencepro.ui.base.BaseRecyclerFragment;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by greedy on 17/3/29.
 */

public class ManagerMailboxFragment extends BaseRecyclerFragment<LetterResponse.DataEntity.LetterEntity> {

    private static final String STATUS = "status";

    private int status;

    public ManagerMailboxFragment() {
        // Required empty public constructor
    }

    public static ManagerMailboxFragment newInstance(int status) {
        ManagerMailboxFragment fragment = new ManagerMailboxFragment();
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
        return new ManagerMailboxAdapter(R.layout.recycler_manager_mailbox_item);
    }

    @Override
    protected void getData() {
        List<LetterResponse.DataEntity.LetterEntity> entities = new ArrayList<>();
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        entities.add(new LetterResponse.DataEntity.LetterEntity(1));
        entities.add(new LetterResponse.DataEntity.LetterEntity(2));
        entities.add(new LetterResponse.DataEntity.LetterEntity(0));
        entities.add(new LetterResponse.DataEntity.LetterEntity(2));
        entities.add(new LetterResponse.DataEntity.LetterEntity(1));
        entities.add(new LetterResponse.DataEntity.LetterEntity(2));
        entities.add(new LetterResponse.DataEntity.LetterEntity());
        mAdapter.setNewData(entities);
    }

    private class ManagerMailboxAdapter extends BaseQuickAdapter<LetterResponse.DataEntity.LetterEntity, BaseViewHolder> {

        public ManagerMailboxAdapter(int layoutResId) {
            super(layoutResId);
        }

        @Override
        protected void convert(BaseViewHolder helper, LetterResponse.DataEntity.LetterEntity item) {
            helper.setText(R.id.tv_title_mg_mailbox, item.getTitle())
                    .setText(R.id.tv_name_mg_mailbox, item.getName())
                    .setText(R.id.tv_date_mg_mailbox, item.getDate())
                    .addOnClickListener(R.id.mg_mailbox_group);
            switch (item.getStatus()) {
                case Constants.WAIT:
                    setText(helper, true);
                    break;
                case Constants.NOT_PASS:
                    setText(helper, false);
                    break;
                case Constants.PASS:
                    setText(helper, true);
                    break;
            }
        }

        private void setText(BaseViewHolder helper, boolean ok) {
            if (ok) {
                helper.setText(R.id.tv_status_mg_mailbox, "通过")
                        .setTextColor(R.id.tv_status_mg_mailbox, ContextCompat.getColor(mContext, R.color.blue_59_color_code))
                        .setBackgroundRes(R.id.tv_status_mg_mailbox, R.drawable.shape_hollow_blue59_3dp)
                        .setText(R.id.tv_deep_status_mg_mailbox, "正在处理");
            } else {
                helper.setText(R.id.tv_status_mg_mailbox, "不通过")
                        .setTextColor(R.id.tv_status_mg_mailbox, ContextCompat.getColor(mContext, R.color.black_160_color_code))
                        .setBackgroundRes(R.id.tv_status_mg_mailbox, R.drawable.shape_hollow_black160_3dp)
                        .setVisible(R.id.tv_deep_status_mg_mailbox, false);
            }
        }
    }
}
