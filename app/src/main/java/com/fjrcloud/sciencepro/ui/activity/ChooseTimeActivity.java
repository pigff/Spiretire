 package com.fjrcloud.sciencepro.ui.activity;

 import android.os.Bundle;
import android.support.annotation.NonNull;
import android.view.View;

import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.ui.base.BaseToolbarActivity;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.OnDateSelectedListener;

 /**
  * 选择资质时间
  */
 public class ChooseTimeActivity extends BaseToolbarActivity {

    private MaterialCalendarView mCalendarView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int provideContentView() {
        return R.layout.activity_choose_time;
    }

    @Override
    public void initData() {
    }

    @Override
    public void initAdapter() {

    }

    @Override
    public void initView() {
        setTitle("选择时间");
        setRightTv("确定", R.drawable.shape_hollow_black44_3dp, R.color.black_44_color_code, new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });

        mCalendarView = (MaterialCalendarView) findViewById(R.id.calendarView);
    }

    @Override
    public void initListener() {
        mCalendarView.setOnDateChangedListener(new OnDateSelectedListener() {
            @Override
            public void onDateSelected(@NonNull MaterialCalendarView materialCalendarView, @NonNull CalendarDay calendarDay, boolean b) {

            }
        });
    }

}
