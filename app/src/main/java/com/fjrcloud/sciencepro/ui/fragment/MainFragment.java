package com.fjrcloud.sciencepro.ui.fragment;


import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.listener.OnItemChildClickListener;
import com.fjrcloud.sciencepro.R;
import com.fjrcloud.sciencepro.adapter.MainMultiAdapter;
import com.fjrcloud.sciencepro.data.api.ScienceApi;
import com.fjrcloud.sciencepro.data.multi.MainMulti;
import com.fjrcloud.sciencepro.data.net.AdEntity;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.network.HttpListResultFunc;
import com.fjrcloud.sciencepro.network.HttpResultFunc;
import com.fjrcloud.sciencepro.network.RequestManager;
import com.fjrcloud.sciencepro.subscribers.SimpleSubListener;
import com.fjrcloud.sciencepro.subscribers.SimpleSubscriber;
import com.fjrcloud.sciencepro.ui.activity.ArticleDetailedActivity;
import com.fjrcloud.sciencepro.ui.activity.DownloadFileActivity;
import com.fjrcloud.sciencepro.ui.activity.ScienceActivity;
import com.fjrcloud.sciencepro.ui.activity.ScienceDynamicActivity;
import com.fjrcloud.sciencepro.ui.activity.SciencePolicyActivity;
import com.fjrcloud.sciencepro.ui.activity.WorkActivity;
import com.fjrcloud.sciencepro.ui.base.BaseFragment;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import rx.Observable;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func2;
import rx.schedulers.Schedulers;

public class MainFragment extends BaseFragment {

    private RecyclerView mRecyclerView;
    private List<MainMulti> mMainMultis;
    private MainMultiAdapter mAdapter;
    private Map<String, Integer> mMap;

    public MainFragment() {
        // Required empty public constructor
    }

    public static MainFragment newInstance() {
        MainFragment fragment = new MainFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    protected View getFragmentView(LayoutInflater inflater, ViewGroup container) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void initListener() {
        mRecyclerView.addOnItemTouchListener(new OnItemChildClickListener() {
            @Override
            public void onSimpleItemChildClick(BaseQuickAdapter adapter, View view, int position) {
                int type = adapter.getItemViewType(position);
                MainMulti item = (MainMulti) baseQuickAdapter.getItem(position);
                switch (type) {
                    case MainMulti.NEWS_RIGHT:
                        Bundle bundle = new Bundle();
                        bundle.putSerializable(Constants.DATA, item.getNews());
                        openActivity(ArticleDetailedActivity.class, bundle);
                        break;
                    case MainMulti.TEXT_MORE:
                        Intent intent2ScienceDy = new Intent(getActivity(), ScienceDynamicActivity.class);
                        startActivity(intent2ScienceDy);
                        break;
                    default:
                        break;
                }
            }
        });

        mAdapter.setClickInterface(new MainMultiAdapter.IndexClickInterface() {
            @Override
            public void click(int positon) {
                switch (positon) {
                    case 0:
                        openActivity(ScienceActivity.class);
                        break;
                    case 1:
                        openActivity(ScienceDynamicActivity.class);
                        break;
                    case 2:
                        openActivity(SciencePolicyActivity.class);
                        break;
                    case 3:
                        openActivity(WorkActivity.class);
                        break;
                    case 4:
                        showShortToast("该功能开发中");
                        break;
                    case 5:
//                        openActivity(MailboxManagerActivity.class);
                        showShortToast("该功能开发中");
//                        openActivity(MailboxUserActivity.class);
                        break;
                    case 6:
                        openActivity(DownloadFileActivity.class);
                        break;
                    case 7:
                        showShortToast("该功能开发中");
//                        openActivity(FilePushActivity.class);
                        break;
                    default:
                        break;
                }
            }
        });
    }

    @Override
    public void initData() {
        mMap = new LinkedHashMap<>();
        mMap.put("科技局", R.drawable.img_science);
        mMap.put("科技动态", R.drawable.img_science_dynamic);
        mMap.put("科技政策", R.drawable.img_science_policy);
        mMap.put("办事指南", R.drawable.img_work_guide);
        mMap.put("在线交流", R.drawable.img_talk);
        mMap.put("公众信箱", R.drawable.img_affair_public);
        mMap.put("文件下载", R.drawable.img_download_file);
        mMap.put("文件推送", R.drawable.img_download_file);
        mMainMultis = new ArrayList<>();
//        List<AdResponse.Ad> ads = new ArrayList<>();
//        List<String> oneUrl = new ArrayList<>();
//        oneUrl.add("https://ss2.bdstatic.com/70cFvnSh_Q1YnxGkpoWK1HF6hhy/it/u=1812573004,1419340744&fm=23&gp=0.jpg");
//        ads.add(new AdResponse.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
//        ads.add(new AdResponse.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
//        ads.add(new AdResponse.Ad("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
//        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE,
//                ads, MainMulti.BANNER));
////        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.DIVIDING));
////        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.FILP));
//        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.DIVIDING));
//        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.GRID, map));
//        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.DIVIDING));
//        mMainMultis.add(new MainMulti(MainMulti.NORMAL_SIZE, "科技动态", MainMulti.TEXT_MORE));

    }

    @Override
    public void initAdapter() {
        mAdapter = new MainMultiAdapter(mMainMultis);
    }

    @Override
    public void initView() {
        mRecyclerView = (RecyclerView) findViewById(R.id.rv_main_fragment);
        mRecyclerView.setHasFixedSize(true);
        mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    protected void onLazyLoad() {
        SimpleSubListener<List<MainMulti>> listener = new SimpleSubListener<List<MainMulti>>() {
            @Override
            public void onNext(List<MainMulti> mainMultis) {
                mAdapter.setNewData(mainMultis);
            }

            @Override
            public void onError(Throwable throwable) {

            }
        };
        ScienceApi api = RequestManager.getInstance().getScienceApi();
        Observable.zip(api.adFind("AppIndex").map(new HttpResultFunc<List<AdEntity>>()),
                api.affairFindAll(0, 6).map(new HttpListResultFunc<List<ScienceDyEntity>>()),
                new Func2<List<AdEntity>, List<ScienceDyEntity>, List<MainMulti>>() {
                    @Override
                    public List<MainMulti> call(List<AdEntity> adEntities, List<ScienceDyEntity> scienceDyEntities) {
                        List<MainMulti> multis = new ArrayList<>();
                        if (adEntities.size() <= 0) {
                            adEntities.add(new AdEntity("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
                            adEntities.add(new AdEntity("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
                            adEntities.add(new AdEntity("广告位招租", "http://www.baosteelresources.com/baogang/new_web/images/top_yewu_02.jpg"));
                        }
                        multis.add(new MainMulti(MainMulti.NORMAL_SIZE,
                                adEntities, MainMulti.BANNER));
                        multis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.DIVIDING));
                        multis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.GRID, mMap));
                        multis.add(new MainMulti(MainMulti.NORMAL_SIZE, MainMulti.DIVIDING));
                        multis.add(new MainMulti(MainMulti.NORMAL_SIZE, "科技动态", MainMulti.TEXT_MORE));
                        for (ScienceDyEntity entity : scienceDyEntities) {
                            multis.add(new MainMulti(MainMulti.NORMAL_SIZE, entity, MainMulti.NEWS_RIGHT));
                        }
                        return multis;
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SimpleSubscriber<>(listener));
    }
}
