package com.fjrcloud.sciencepro;

import android.app.Application;

/**
 * Created by lin on 2017/2/24.
 */

public class App extends Application {

    private static App sApp;

    @Override
    public void onCreate() {
        super.onCreate();
        sApp = this;
    }

    public static App getInstance() {
        return sApp;
    }
}
