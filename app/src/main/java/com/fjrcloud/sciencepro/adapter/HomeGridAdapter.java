package com.fjrcloud.sciencepro.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.fjrcloud.sciencepro.R;

import java.util.List;

/**
 * Created by lin on 2016/8/1.
 */
public class HomeGridAdapter extends BaseAdapter {
    private List<Integer> mImages;

    private List<String> mCategory;

    private Context mContext;

    public HomeGridAdapter(List<Integer> images, List<String> category, Context context) {
        mImages = images;
        mCategory = category;
        mContext = context;
    }

    @Override
    public int getCount() {
        return 9;
    }

    @Override
    public Object getItem(int position) {
        if (position < 8) {
            return mImages.get(position);
        }
        return null;
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_center_home_item, parent, false);
            holder = new ViewHolder();
            holder.mImageView = (ImageView) convertView.findViewById(R.id.home_grid_image_item);
            holder.mTextView = (TextView) convertView.findViewById(R.id.home_grid_text_image);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder) convertView.getTag();
        }
        if (position < 8) {
            holder.mImageView.setBackgroundResource(mImages.get(position));
            holder.mTextView.setText(mCategory.get(position));
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;

        TextView mTextView;
    }
}
