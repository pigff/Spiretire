package com.fjrcloud.sciencepro.adapter;

import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.ScienceDynamicResponse;

import java.util.List;

/**
 * Created by lin on 2016/12/6.
 */
public class ScienceDynamicAdapter extends BaseQuickAdapter<ScienceDynamicResponse.ScienceDynamic, BaseViewHolder> {

    public ScienceDynamicAdapter(int layoutResId, List<ScienceDynamicResponse.ScienceDynamic> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, ScienceDynamicResponse.ScienceDynamic scienceDynamic) {
        baseViewHolder.setText(R.id.tv_name_company_list, scienceDynamic.getTitle())
                .setText(R.id.comment_date, scienceDynamic.getDate())
                .addOnClickListener(R.id.comment_group);
        List<String> uploadFiles = scienceDynamic.getImgUrls();
        int imgCount = uploadFiles.size();
        // item复用  但是数据并不会乱
        switch (imgCount) {
            case 0:
                //没有图片的布局
                baseViewHolder.setVisible(R.id.comment_one_img, false)
                        .setVisible(R.id.comment_item_img_group, false);
                break;
            case 1:
                //一张图片的布局， 以下类推。
                baseViewHolder.setVisible(R.id.comment_one_img, true)
                        .setVisible(R.id.comment_item_img_group, false);
                Glide.with(mContext).load(uploadFiles.get(0)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_one_img));
                break;
            case 2:
                baseViewHolder.setVisible(R.id.comment_item_img_group, true)
                        .setVisible(R.id.comment_one_img, false);
                baseViewHolder.getView(R.id.comment_img_group_three).setVisibility(View.INVISIBLE);
                Glide.with(mContext).load(uploadFiles.get(0)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_img_group_one));
                Glide.with(mContext).load(uploadFiles.get(1)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_img_group_two));

                break;
            case 3:
                baseViewHolder.setVisible(R.id.comment_item_img_group, true)
                        .setVisible(R.id.comment_img_group_three, true)
                        .setVisible(R.id.comment_one_img, false);
                Glide.with(mContext).load(uploadFiles.get(0)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_img_group_one));
                Glide.with(mContext).load(uploadFiles.get(1)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_img_group_two));
                Glide.with(mContext).load(uploadFiles.get(2)).error(R.drawable.img_error).
                        into((ImageView) baseViewHolder.getView(R.id.comment_img_group_three));
                break;
            default:
                break;
        }
    }
}
