package com.fjrcloud.sciencepro.network;

import com.fjrcloud.sciencepro.data.api.ScienceApi;
import com.fjrcloud.sciencepro.utils.Constants;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lin on 2017/3/4.
 * 下载的管理器 类
 * 感觉有更好的方案
 */

public class DownloadManager {

    private static final String TAG = "DownloadManager";
    private static final int DEFAULT_TIMEOUT = 15;
    private final ScienceApi mScienceApi;
    private static DownloadManager sDownloadManager;

    public DownloadManager(ProgressResponseListener listener) {
        DownloadProgressInterceptor interceptor = new DownloadProgressInterceptor(listener);

        OkHttpClient client = new OkHttpClient.Builder()
                .addInterceptor(interceptor)
                .retryOnConnectionFailure(true)
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(client)
                .baseUrl(Constants.BASE_URL)
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        mScienceApi = retrofit.create(ScienceApi.class);
    }

    public static DownloadManager getInstance(ProgressResponseListener listener) {
        if (sDownloadManager == null) {
            synchronized (DownloadManager.class) {
                if (sDownloadManager == null) {
                    sDownloadManager = new DownloadManager(listener);
                }
            }
        }
        return sDownloadManager;
    }
}
