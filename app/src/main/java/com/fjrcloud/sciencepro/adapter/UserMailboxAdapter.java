package com.fjrcloud.sciencepro.adapter;

import android.support.v4.content.ContextCompat;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.net.LetterResponse;
import com.fjrcloud.sciencepro.utils.Constants;

/**
 * Created by greedy on 17/3/29.
 */

public class UserMailboxAdapter extends BaseQuickAdapter<LetterResponse.DataEntity.LetterEntity, BaseViewHolder> {

    public UserMailboxAdapter(int layoutResId) {
        super(layoutResId);
    }

    @Override
    protected void convert(BaseViewHolder helper, LetterResponse.DataEntity.LetterEntity item) {
        helper.setText(R.id.tv_title_user_mailbox, item.getTitle())
                .setText(R.id.tv_name_user_mailbox, item.getName())
                .setText(R.id.tv_date_user_mailbox, item.getDate())
                .addOnClickListener(R.id.user_mailbox_group);

        switch (item.getStatus()) {
            case Constants.PROCESING:
                setText(helper, false);
                break;
            case Constants.FINISHED:
                setText(helper, true);
                break;
        }
    }

    private void setText(BaseViewHolder helper, boolean ok) {
        if (ok) {
            helper.setText(R.id.tv_status_user_mailbox, "处理完毕")
                    .setTextColor(R.id.tv_status_user_mailbox, ContextCompat.getColor(mContext, R.color.pink_red_255_color_code))
                    .setBackgroundRes(R.id.tv_status_user_mailbox, R.drawable.shape_hollow_pink_red255_3dp);
        } else {
            helper.setText(R.id.tv_status_user_mailbox, "正在处理")
                    .setTextColor(R.id.tv_status_user_mailbox, ContextCompat.getColor(mContext, R.color.blue_59_color_code))
                    .setBackgroundRes(R.id.tv_status_user_mailbox, R.drawable.shape_hollow_blue59_3dp);
        }
    }
}
