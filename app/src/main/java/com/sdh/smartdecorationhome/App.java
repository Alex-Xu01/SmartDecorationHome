package com.sdh.smartdecorationhome;

import android.app.Application;
import android.util.Log;

import com.blankj.utilcode.util.CrashUtils;
import com.blankj.utilcode.util.Utils;
import com.sdh.smartdecorationhome.utils.SharePreferenceStorageService;
import com.zhy.http.okhttp.OkHttpUtils;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;

/**
 * Created by Alex on 2017/4/11.
 */

public class App extends Application {

    private static App sInstance;
    private SharePreferenceStorageService sharePreference;


    @Override
    public void onCreate() {
        super.onCreate();
        sInstance = this;

        OkHttpClient okHttpClient = new OkHttpClient.Builder()
                .connectTimeout(10000L, TimeUnit.MILLISECONDS)
                .readTimeout(10000L, TimeUnit.MILLISECONDS)
                //其他配置
                .build();

        OkHttpUtils.initClient(okHttpClient);

        //初始化全局缓存
        sharePreference = SharePreferenceStorageService.newInstance(sInstance);

        //初始化AndroidUtilCode
        Utils.init(sInstance);

        //捕获崩溃异常
        CrashUtils.getInstance().init();
    }

    /**
     * @return ApplicationController singleton instance
     */
    public static synchronized App getInstance() {
        return sInstance;
    }

    @Override
    public void onLowMemory() {
        super.onLowMemory();
        Log.d("Application", "Memory is low!");
    }
}
