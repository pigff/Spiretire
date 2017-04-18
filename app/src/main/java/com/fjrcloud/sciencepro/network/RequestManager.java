package com.fjrcloud.sciencepro.network;

import com.fjrcloud.sciencepro.data.api.ScienceApi;
import com.fjrcloud.sciencepro.utils.AppUtils;
import com.fjrcloud.sciencepro.utils.Constants;

import java.io.File;
import java.io.IOException;

import okhttp3.Cache;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by lin on 2017/2/24.
 */

public class RequestManager {

    private static final String TAG = "RequestManager";
    private final static int MAX_AGE = 2 * 60 * 60;  //缓存两个小时
    private final static int CACHE_SIZE = 10 * 1024 * 1024; // 缓存10m

    private ScienceApi mScienceApi;
    private static RequestManager sRequestManager;

    public RequestManager() {
        Interceptor interceptor = new Interceptor() {
            @Override
            public Response intercept(Chain chain) throws IOException {
                Request request = chain.request();
                Response response = null;
                try {
                    response = chain.proceed(request);
                } catch (IOException e) {
                    e.printStackTrace();
                }
                if (response != null) {
                    return response.newBuilder()
                            .header("Cache-Control", "max-age=" + MAX_AGE)
                            .build();
                }
                return response;
            }
        };

        File cacheDirectory = new File(AppUtils.getCacheDir(), "response");
        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cache(new Cache(cacheDirectory, CACHE_SIZE))
                .addNetworkInterceptor(interceptor)
                .build();

        Retrofit retrofit = new Retrofit.Builder()
                .client(okHttpClient)
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();

        mScienceApi = retrofit.create(ScienceApi.class);
    }

    public static RequestManager getInstance() {
        if (sRequestManager == null) {
            synchronized (RequestManager.class) {
                if (sRequestManager == null) {
                    sRequestManager = new RequestManager();
                }
            }
        }
        return sRequestManager;
    }

}
