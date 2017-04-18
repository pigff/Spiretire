package com.fjrcloud.sciencepro.adapter;

import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.ViewFlipper;

import com.bumptech.glide.Glide;
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.multi.MainMulti;
import com.fjrcloud.sciencepro.data.net.AdEntity;
import com.fjrcloud.sciencepro.utils.Constants;
import com.fjrcloud.sciencepro.utils.DateUtil;
import com.fjrcloud.sciencepro.utils.ImgLoadUtils;
import com.fjrcloud.sciencepro.widget.HomeGridView;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import cn.bingoogolapple.bgabanner.BGABanner;

/**
 * Created by lin on 2016/11/24.
 */
public class MainMultiAdapter extends BaseMultiItemQuickAdapter<MainMulti, BaseViewHolder> {

    private IndexClickInterface mClickInterface;

    public MainMultiAdapter(List<MainMulti> data) {
        super(data);
        addItemType(MainMulti.BANNER, R.layout.recycler_multi_banner_item);
        addItemType(MainMulti.CATEGORY, R.layout.recycler_multi_category_item);
        addItemType(MainMulti.NEWS_RIGHT, R.layout.recycler_multi_news_item);
        addItemType(MainMulti.DIVIDING, R.layout.recycler_multi_dividing_item);
        addItemType(MainMulti.TEXT_MORE, R.layout.recycler_multi_more_item);
        addItemType(MainMulti.GRID, R.layout.recycler_multi_grid_item);
        addItemType(MainMulti.FILP, R.layout.recycler_multi_filpper_item);
    }

    @Override
    protected void convert(BaseViewHolder baseViewHolder, MainMulti multi) {
        switch (baseViewHolder.getItemViewType()) {
            case MainMulti.BANNER:
                List<String> names = new ArrayList<>();
                BGABanner bgaBanner = baseViewHolder.getView(R.id.common_banner);
                if (multi.getAds().size() > 0 &&
                        !TextUtils.equals("广告位招租", multi.getAds().get(0).getTitle())) {
                    bgaBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            Glide.with(mContext).load(Constants.BASE_IMG_URL + ((AdEntity) model).getImgPath()).
                                    error(R.drawable.img_error).into((ImageView) view);
                        }
                    });
                } else {
                    bgaBanner.setAdapter(new BGABanner.Adapter() {
                        @Override
                        public void fillBannerItem(BGABanner banner, View view, Object model, int position) {
                            Glide.with(mContext).load(((AdEntity) model).getImgPath()).
                                    error(R.drawable.img_error).into((ImageView) view);
                        }
                    });
                }

                for (int i = 0; i < multi.getAds().size(); i++) {
                    names.add(multi.getAds().get(i).getTitle());
                }
                bgaBanner.setData(multi.getAds(), names);
                bgaBanner.setOnItemClickListener(new BGABanner.OnItemClickListener() {
                    @Override
                    public void onBannerItemClick(BGABanner banner, View view, Object model, int position) {
//                        Toast.makeText(mContext, ((CategoryBean.Category) model).getName(), Toast.LENGTH_SHORT).show();
                    }
                });
                break;
            case MainMulti.CATEGORY:
                baseViewHolder.setImageResource(R.id.iv_multi_category, multi.getCategory().getImgSrc())
                        .setText(R.id.tv_multi_category, multi.getCategory().getContent())
                        .addOnClickListener(R.id.multi_category_group);
                break;
            case MainMulti.NEWS_RIGHT:
                baseViewHolder.setText(R.id.title_multi_news, multi.getNews().getTitle())
                        .setText(R.id.time_multi_news, DateUtil.getDateToString(multi.getNews().getCreateTime()))
                        .addOnClickListener(R.id.multi_news_group);

                ImageView imageView = baseViewHolder.getView(R.id.iv_multi_news);
                ImgLoadUtils.loadUrl(mContext, Constants.BASE_Web_IMG_URL + multi.getNews().getImgPath(), R.drawable.img_error, imageView);
                break;
            case MainMulti.TEXT_MORE:
                baseViewHolder.setText(R.id.name_multi_more, multi.getContent())
                        .addOnClickListener(R.id.btn_multi_more);
                break;
            case MainMulti.GRID:
                List<String> categoryNames = new ArrayList<>();
                List<Integer> categorySrc = new ArrayList<>();
                for (Map.Entry<String, Integer> map : multi.getMap().entrySet()) {
                    categoryNames.add(map.getKey());
                    categorySrc.add(map.getValue());
                }
                HomeGridView gridView = baseViewHolder.getView(R.id.grid_item);
                baseViewHolder.setOnItemClickListener(R.id.grid_item, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        if (mClickInterface != null) {
                            mClickInterface.click(position);
                        }
                    }
                });
                HomeGridAdapter adapter = new HomeGridAdapter(categorySrc, categoryNames, mContext);
                gridView.setAdapter(adapter);
                break;
            case MainMulti.FILP:
                ViewFlipper flipper = baseViewHolder.getView(R.id.flipper_multi_item);

                for (int i = 0; i < 3; i++) {
                    View view = LayoutInflater.from(mContext).inflate(R.layout.vf_bulletin_item, null, false);
                    TextView textView = (TextView) view.findViewById(R.id.tv_bulletin_first_title_item);
                    textView.setText("嘻嘻" + i);
                    textView.setText("哈哈" + i);
                    flipper.addView(view);
                }
                break;
            default:
                break;
        }
    }

    public void setClickInterface(IndexClickInterface clickInterface) {
        mClickInterface = clickInterface;
    }

    public interface IndexClickInterface {
        void click(int positon);
    }
}
