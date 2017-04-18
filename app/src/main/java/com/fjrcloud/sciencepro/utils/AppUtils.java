package com.fjrcloud.sciencepro.utils;

import android.os.Environment;

import com.fjrcloud.sciencepro.App;

import java.io.File;

/**
 * Created by lin on 2017/2/22.
 */
public class AppUtils {

    public static String getCacheDir() {
        if(Environment.MEDIA_MOUNTED.equals(Environment.getExternalStorageState()) || !Environment.isExternalStorageRemovable()) {
            File cacheFile = App.getInstance().getExternalCacheDir();
            if(null != cacheFile) {
                return cacheFile.getPath();
            }
        }
        return App.getInstance().getCacheDir().getPath();
    }
}
