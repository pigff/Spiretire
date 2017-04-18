package com.fjrcloud.sciencepro.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.content.ContextCompat;
import android.text.TextUtils;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.fjrcloud.sciencepro.App;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.data.PostCategoryResponse;

import java.util.List;


/**
 * Created by lin on 2016/8/10.
 * 弹出选择列表
 */
public class ChoicePopWindow extends PopupWindow{

    private ChoicePopLvClick mClick;

    public ChoicePopWindow(final Activity context, List<PostCategoryResponse.PostCategory> categoryList) {
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        View contentView = inflater.inflate(R.layout.dialog_pop_window_item, null);
        init(context, contentView);
        ListView listView = (ListView) contentView.findViewById(R.id.lv_pop_window);
        PopListViewAdapter adapter = new PopListViewAdapter(context, categoryList);
        listView.setAdapter(adapter);
        ImageButton imageButton = (ImageButton) contentView.findViewById(R.id.iv_xx_pop_window);
        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();

            }
        });
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                if (mClick != null) {
                    mClick.LvClick(position);
                }
            }
        });
    }

    private void init(Activity context, View contentView) {
        Point x = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(x);
        int h = x.y;
        int w = x.x;
        setContentView(contentView);
        setHeight(h / 3);
        setWidth(w / 2);
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        ColorDrawable dw = new ColorDrawable(0);
        setBackgroundDrawable(dw);
        setAnimationStyle(R.style.AnimationPreview);
    }


    public void showPoppupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.CENTER, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public void setChoiceClick(ChoicePopLvClick click) {
        this.mClick = click;
    }

    public interface ChoicePopLvClick {
        void LvClick(int position);
    }

    class PopListViewAdapter extends BaseAdapter {
        private List<PostCategoryResponse.PostCategory> mCategory;

        private Context mContext;

        public PopListViewAdapter(Context context, List<PostCategoryResponse.PostCategory> categoryList) {
            mContext = context;
            mCategory = categoryList;
        }

        @Override
        public int getCount() {
            return mCategory.size();
        }

        @Override
        public Object getItem(int position) {
            return mCategory.get(position);
        }

        @Override
        public long getItemId(int position) {
            return position;
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {
            ViewHolder holder;
            if (convertView == null) {
                convertView = LayoutInflater.from(mContext).inflate(R.layout.listview_dialog_pop_item, parent, false);
                holder = new ViewHolder();
                holder.mTextView = (TextView) convertView.findViewById(R.id.tv_lv_pop_item);
                convertView.setTag(holder);
            } else {
                holder = (ViewHolder) convertView.getTag();
            }
            if (TextUtils.equals(mCategory.get(position).getName(), "跳过")) {
                holder.mTextView.setTextColor(ContextCompat.getColor(App.getInstance(), R.color.grey_150_color_code));
            }
            holder.mTextView.setText(mCategory.get(position).getName());
            return convertView;
        }

        class ViewHolder {
            TextView mTextView;
        }
    }

}
