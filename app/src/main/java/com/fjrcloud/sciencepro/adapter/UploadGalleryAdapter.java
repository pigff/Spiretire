package com.fjrcloud.sciencepro.adapter;

import android.widget.ImageView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.Pic;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;

/**
 * Created by greedy on 17/3/30.
 */

public class UploadGalleryAdapter extends BaseQuickAdapter<Pic, BaseViewHolder> {

    public static final String ADD = "add";
    public static final String IMG = "img";

    public UploadGalleryAdapter(int layoutResId) {
        super(layoutResId);
        addData(new Pic());
    }

    @Override
    protected void convert(BaseViewHolder helper, Pic item) {
        boolean isAdd = item.isAdd();
        boolean isShow = item.isCancel();
        helper.setVisible(R.id.iv_gallery_cancel, isShow);
        if (isAdd) {
            helper.setImageResource(R.id.iv_gallery_show_pic, R.drawable.bg_add_pic)
                    .setTag(R.id.iv_gallery_show_pic, R.id.add_pic, ADD)
                    .addOnClickListener(R.id.iv_gallery_show_pic);
        } else {
            ImageView imageView = (ImageView) helper.getView(R.id.iv_gallery_show_pic);
            ImgLoadUtils.loadUrl(mContext, item.getImgUrl(), imageView);
            helper.addOnClickListener(R.id.iv_gallery_cancel)
                    .setTag(R.id.iv_gallery_show_pic, R.id.add_pic, IMG);
        }
    }
}
