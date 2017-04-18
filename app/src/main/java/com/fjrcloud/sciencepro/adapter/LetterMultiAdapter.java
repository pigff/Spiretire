package com.fjrcloud.sciencepro.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.LetterMulti;

import java.util.List;

/**
 * Created by greedy on 17/3/30.
 */

public class LetterMultiAdapter extends BaseMultiItemQuickAdapter<LetterMulti, BaseViewHolder> {

    public static final int HEADER = 0;
    public static final int BODY = 1;
    public static final int IMG = 2;
    public static final int FILE = 3;
    public static final int DIVIDING = 4;

    public LetterMultiAdapter(List<LetterMulti> data) {
        super(data);
        addItemType(HEADER, R.layout.recyler_multi_letter_header_item);
        addItemType(BODY, R.layout.recyler_multi_letter_body_item);
        addItemType(IMG, R.layout.recycler_multi_img_item);
        addItemType(FILE, R.layout.recycler_multi_file_item);
        addItemType(DIVIDING, R.layout.recycler_multi_dividing_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, LetterMulti item) {
        switch (item.getItemType()) {
            case IMG:
                helper.setVisible(R.id.view_placeholder, item.isShow());
                break;

        }
    }
}
