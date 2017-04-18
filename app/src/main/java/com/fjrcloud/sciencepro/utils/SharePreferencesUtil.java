package com.fjrcloud.sciencepro.utils;

import android.content.Context;
import android.content.SharedPreferences;

/**
 * Created by lin on 2016/12/17.
 */
public class SharePreferencesUtil {

    private final SharedPreferences mSharedPreferences;

    private static SharePreferencesUtil sInstance;
    private SharePreferencesUtil(Context context) {
        mSharedPreferences = context.getSharedPreferences("user_data", Context.MODE_PRIVATE);
    }

    public static SharePreferencesUtil getInstance(Context context) {
        if (sInstance == null) {
            synchronized (SharePreferencesUtil.class) {
                if (sInstance == null) {
                    sInstance = new SharePreferencesUtil(context);
                }
            }
        }
        return sInstance;
    }

    public void putStringData(String key, String value) {
        SharedPreferences.Editor editor = mSharedPreferences.edit();
        editor.putString(key, value);
        editor.apply();
    }

    public String getStringData(String key) {
        return mSharedPreferences.getString(key, null);
    }
}
