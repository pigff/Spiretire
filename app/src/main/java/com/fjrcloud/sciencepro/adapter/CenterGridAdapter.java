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
public class CenterGridAdapter extends BaseAdapter {

    private Context mContext;

    private List<Integer> mImageList;

    private List<String> mCategoryList;

    public CenterGridAdapter(Context context, List<Integer> imageList, List<String> categoryList) {
        mContext = context;
        mImageList = imageList;
        mCategoryList = categoryList;
    }

    @Override
    public int getCount() {
        return mImageList.size();
    }

    @Override
    public Object getItem(int position) {
        return position;
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
        if (position < mImageList.size()) {
            holder.mImageView.setImageResource(mImageList.get(position));
            holder.mTextView.setText(mCategoryList.get(position));
        }
        return convertView;
    }

    class ViewHolder {
        ImageView mImageView;

        TextView mTextView;
    }
}
