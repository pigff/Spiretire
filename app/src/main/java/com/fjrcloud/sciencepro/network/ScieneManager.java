package com.fjrcloud.sciencepro.network;

import com.fjrcloud.sciencepro.data.net.AdEntity;
import com.fjrcloud.sciencepro.data.net.EnterpriseEntity;
import com.fjrcloud.sciencepro.data.net.FileEntity;
import com.fjrcloud.sciencepro.data.net.ManagementEntity;
import com.fjrcloud.sciencepro.data.net.ScienceDyEntity;

import java.util.List;

import rx.Subscriber;
import rx.Subscription;
import rx.android.schedulers.AndroidSchedulers;
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
}
