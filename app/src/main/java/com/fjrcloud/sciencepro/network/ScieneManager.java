package com.fjrcloud.sciencepro.network;

import android.text.TextUtils;

import com.fjrcloud.sciencepro.data.base.HttpResult;
import com.fjrcloud.sciencepro.data.net.AdEntity;
import com.fjrcloud.sciencepro.data.net.DepartmentEntity;
import com.fjrcloud.sciencepro.data.net.EnterpriseEntity;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.data.net.LeaderEntity;
import com.fjrcloud.sciencepro.data.net.ManagementEntity;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;
import com.fjrcloud.sciencepro.data.net.StaffEntity;

import java.util.List;

import rx.Observable;
import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Func1;
import rx.schedulers.Schedulers;

/**
 * Created by greedy on 2017/4/18.
 */

public class ScieneManager {

    private ScieneManager() {

    }

    public static Subscription getManagement(Subscriber<ManagementEntity> subscriber) {
        return RequestManager.getInstance()
                .getScienceApi().getManagement()
                .map(new HttpResultFunc<ManagementEntity>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription departmentFindAll(Subscriber<List<ManagementEntity>> subscriber) {
        return RequestManager.getInstance()
                .getScienceApi().departmentFindAll()
                .map(new HttpResultFunc<List<ManagementEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription adFind(Subscriber<List<AdEntity>> subscriber) {
        return RequestManager.getInstance()
                .getScienceApi().adFind("AppIndex")
                .map(new HttpResultFunc<List<AdEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription affairFindAll(Subscriber<List<ScienceDyEntity>> subscriber, Integer pageNum, Integer pageSize) {
        return RequestManager.getInstance()
                .getScienceApi().affairFindAll(pageNum, pageSize)
                .map(new HttpListResultFunc<List<ScienceDyEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription affairFindById(Subscriber<ScienceDyEntity> subscriber, Integer id) {
        return RequestManager.getInstance()
                .getScienceApi().affairFindById(id)
                .map(new HttpResultFunc<ScienceDyEntity>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription downloadFindAll(Subscriber<List<FileEntity>> subscriber, Integer pageNum, Integer pageSize) {
        return RequestManager.getInstance()
                .getScienceApi().downloadFindAll(pageNum, pageSize)
                .map(new HttpListResultFunc<List<FileEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription downloadSearchByName(Subscriber<List<FileEntity>> subscriber, String keyWord) {
        return RequestManager.getInstance()
                .getScienceApi().downloadSearchByName(keyWord)
                .map(new HttpResultFunc<List<FileEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription enterpriseFindAll(Subscriber<List<EnterpriseEntity>> subscriber, Integer pageNum, Integer pageSize) {
        return RequestManager.getInstance()
                .getScienceApi().enterpriseFindAll(pageNum, pageSize)
                .map(new HttpListResultFunc<List<EnterpriseEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription findLeaders(Subscriber<List<LeaderEntity>> subscriber) {
        return RequestManager.getInstance()
                .getScienceApi().findByOneScopes("S")
                .map(new HttpResultFunc<List<DepartmentEntity>>())
                .flatMap(new Func1<List<DepartmentEntity>, Observable<HttpResult<StaffEntity>>>() {
                    @Override
                    public Observable<HttpResult<StaffEntity>> call(List<DepartmentEntity> departmentEntities) {
                        int id = 0;
                        for (DepartmentEntity entity : departmentEntities) {
                            if (TextUtils.equals("领导", entity.getName())) {
                                id = entity.getId();
                                break;
                            }
                        }
                        return RequestManager.getInstance().getScienceApi().deparmentFind(id);
                    }
                })
                .map(new HttpResultFunc<StaffEntity>())
                .map(new Func1<StaffEntity, List<LeaderEntity>>() {
                    @Override
                    public List<LeaderEntity> call(StaffEntity staffEntity) {
                        return staffEntity.getStaff();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription findByOneScopes(Subscriber<List<DepartmentEntity>> subscriber) {
        return RequestManager.getInstance()
                .getScienceApi().findByOneScopes("S")
                .map(new HttpResultFunc<List<DepartmentEntity>>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

    public static Subscription deparmentFind(Subscriber<StaffEntity> subscriber, Integer id) {
        return RequestManager.getInstance()
                .getScienceApi().deparmentFind(id)
                .map(new HttpResultFunc<StaffEntity>())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);
    }

}
