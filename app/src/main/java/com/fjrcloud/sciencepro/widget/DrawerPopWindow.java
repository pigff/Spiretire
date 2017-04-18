package com.fjrcloud.sciencepro.widget;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.graphics.drawable.ColorDrawable;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.PopupWindow;
import android.widget.RelativeLayout;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.DrawerMultiAdapter;
import com.fjrcloud.sciencepro.data.multi.DrawerMulti;

import java.util.List;


/**
 * Created by lin on 2016/8/10.
 */
public class DrawerPopWindow extends PopupWindow {

    private View contentView;
    private RecyclerView mRecyclerView;
    private Button mCancelBtn;
    private Button mConfirmBtn;
    private IndustryInterface mIndustryInterface;
    private NatureInterface mNatureInterface;
    private int mLastIndustryIndex;
    private int mLastNatureIndex;
    private final DrawerMultiAdapter mAdapter;
    private DrawerMulti.Category mIndustyCategory;
    private DrawerMulti.Category mNatureCategory;
    private ConfirmClick mConfirmClick;


    public DrawerPopWindow(Activity context, final List<DrawerMulti> multis, int lastIndustryIndex, int lastNatureIndex) {
        if (lastIndustryIndex != -1) {
            mLastIndustryIndex = lastIndustryIndex;
        } else {
            mLastIndustryIndex = -1;
        }
        if (lastNatureIndex != -1) {
            mLastNatureIndex = lastNatureIndex;
        } else {
            mLastNatureIndex = -1;
        }
        Point x = new Point();
        context.getWindowManager().getDefaultDisplay().getSize(x);
        int w = x.x;
        setHeight(RelativeLayout.LayoutParams.MATCH_PARENT);
        setWidth(w - w / 5);
        setFocusable(true);
        setOutsideTouchable(true);
        update();
        ColorDrawable dw = new ColorDrawable(00000000);
        setBackgroundDrawable(dw);
        setAnimationStyle(R.style.DrawerAnimationPreview);
        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        contentView = inflater.inflate(R.layout.recycler_drawer_pop_window, null);
        mRecyclerView = (RecyclerView) contentView.findViewById(R.id.right_pop_rv);
        mCancelBtn = (Button) contentView.findViewById(R.id.right_pop_cancel_btn);
        mConfirmBtn = (Button) contentView.findViewById(R.id.right_pop_confirm_btn);
        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                dismiss();
            }
        });
        setContentView(contentView);

        mAdapter = new DrawerMultiAdapter(multis);
        mAdapter.setSpanSizeLookup(new BaseQuickAdapter.SpanSizeLookup() {
            @Override
            public int getSpanSize(GridLayoutManager gridLayoutManager, int position) {
                return multis.get(position).getSpanSize();
            }
        });
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new GridLayoutManager(context, 3));
        mRecyclerView.setAdapter(mAdapter);

        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                List<DrawerMulti> multis = adapter.getData();
                switch (adapter.getItemViewType(position)) {
                    case DrawerMulti.INDUSTRY:
                        mIndustyCategory = getCategory(adapter, position, mLastIndustryIndex, multis);
                        mLastIndustryIndex = getPosition(mIndustyCategory, position);
                        if (mIndustryInterface != null) {
                            mIndustryInterface.click(mIndustyCategory, mLastIndustryIndex);
                        }
                        break;
                    case DrawerMulti.NATURE:
                        mNatureCategory = getCategory(adapter, position, mLastNatureIndex, multis);
                        mLastNatureIndex = getPosition(mNatureCategory, position);
                        if (mNatureInterface != null) {
                            mNatureInterface.click(mNatureCategory, mLastNatureIndex);
                        }
                        break;
                    default:
                        break;
                }
            }
        });

        mConfirmBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (mConfirmClick != null) {
                    mConfirmClick.click(v);
                }
            }
        });
    }

    private int getPosition(DrawerMulti.Category category, int position) {
        if (category != null) {
            return position;
        } else {
            return  -1;
        }
    }

    private DrawerMulti.Category getCategory(BaseQuickAdapter adapter, int indexPosition, int lastIndexPosition, List<DrawerMulti> multis) {
        DrawerMulti.Category category = multis.get(indexPosition).getCategory();
        if (lastIndexPosition == indexPosition) {
            category.setSelected(false);
            mAdapter.notifyItemChanged(indexPosition);
            return null;
        }
        if (lastIndexPosition != -1) {
            multis.get(lastIndexPosition).getCategory().setSelected(false);
            adapter.notifyItemChanged(lastIndexPosition);
        }
        category.setSelected(true);
        adapter.notifyItemChanged(indexPosition);
        return category;
    }

    public void showPoppupWindow(View parent) {
        if (!this.isShowing()) {
            this.showAtLocation(parent, Gravity.RIGHT, 0, 0);
        } else {
            this.dismiss();
        }
    }

    public void setIndustryClick(IndustryInterface industryInterface) {
        this.mIndustryInterface = industryInterface;
    }

    public void setNatureClick(NatureInterface natureInterface) {
        this.mNatureInterface = natureInterface;
    }

    public void setConfirmClick(ConfirmClick confirmClick) {
        this.mConfirmClick = confirmClick;
    }

    public interface IndustryInterface {
        void click(DrawerMulti.Category category, int position);
    }

    public interface NatureInterface {
        void click(DrawerMulti.Category category, int position);
    }

    public interface ConfirmClick {
        void click(View view);
    }
}
