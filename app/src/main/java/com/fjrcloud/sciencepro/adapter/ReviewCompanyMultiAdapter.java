package com.fjrcloud.sciencepro.adapter;

import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.CommonMulti;

import java.util.List;

/**
 * Created by greedy on 17/4/1.
 */

public class ReviewCompanyMultiAdapter extends BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder> {
    public static final int TITLE = 0;
    public static final int IMAGE = 1;
    public static final int LOGIN_TEXT = 2;
    public static final int FILE = 3;
    public static final int ALL_DELETE = 4;
    public static final int DIVIDING = 5;
    public static final int STATUS = 6;

    public ReviewCompanyMultiAdapter(List<MultiItemEntity> data) {
        super(data);
        addItemType(TITLE, R.layout.recycler_multi_login_title_item);
        addItemType(LOGIN_TEXT, R.layout.recycler_multi_review_company_item);
        addItemType(IMAGE, R.layout.recycler_multi_review_company_image_item);
        addItemType(FILE, R.layout.recycler_multi_review_company_file_item);
        addItemType(ALL_DELETE, R.layout.recycler_multi_review_company_delete_item);
        addItemType(DIVIDING, R.layout.recycler_multi_drawer_dividing_item);
        addItemType(STATUS, R.layout.recycler_multi_review_company_status_item);
    }

    @Override
    protected void convert(BaseViewHolder helper, final MultiItemEntity item) {
        switch (helper.getItemViewType()) {
            case TITLE:
                if (item instanceof CommonMulti) {
                    helper.setText(R.id.tv_multi_login_title, ((CommonMulti) item).getOther());
                }
                break;
            case LOGIN_TEXT:
                break;
            case IMAGE:
                break;
            case FILE:
                break;
            case ALL_DELETE:
                break;
            default:
                break;
        }
    }

}
