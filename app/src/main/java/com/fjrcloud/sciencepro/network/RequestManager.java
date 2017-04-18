package com.fjrcloud.sciencepro.network;

import android.util.Log;

import com.fjrcloud.sciencepro.App;
import com.fjrcloud.sciencepro.data.api.ScienceApi;
import com.fjrcloud.sciencepro.utils.Constants;
import com.franmontiel.persistentcookiejar.ClearableCookieJar;
import com.franmontiel.persistentcookiejar.PersistentCookieJar;
import com.franmontiel.persistentcookiejar.cache.SetCookieCache;
import com.franmontiel.persistentcookiejar.persistence.SharedPrefsCookiePersistor;

import java.io.IOException;

import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.logging.HttpLoggingInterceptor;
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
                Request newRequest = chain.request().newBuilder()
                        .header("Accept", "application/json")
                        .build();
                return chain.proceed(newRequest);
            }
        };

        // okhttp3打印请求log的拦截器
        HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor(new HttpLoggingInterceptor.Logger() {
            @Override
            public void log(String message) {
                if (message.startsWith("{")) {
                    Log.d(TAG, message);
                } else {
                    if (message.contains("-->") || message.contains("<--")) {
                        Log.d(TAG, message);
                    }
                }
            }
        });
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BASIC);

        ClearableCookieJar cookieJar =
                new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.getInstance()));

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .cookieJar(cookieJar)
                .addNetworkInterceptor(interceptor)
                .addInterceptor(httpLoggingInterceptor)
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


    public ScienceApi getScienceApi() {
        return mScienceApi;
    }
}
