package com.sdh.smartdecorationhome.utils;

import android.content.Context;
import android.text.TextUtils;

import cn.droidlover.xdroidbase.cache.SharedPref;
import cn.droidlover.xdroidbase.kit.Codec;

/**
 * 全局缓存
 * Created by xulih on 2017/4/11.
 */

public class SharePreferenceStorageService {
    private static SharePreferenceStorageService preferenceStorageService;
    private Context context;

    public SharePreferenceStorageService(Context context) {
        this.context = context;
    }

    public static SharePreferenceStorageService newInstance(Context context) {

        if (preferenceStorageService == null) {
            preferenceStorageService = new SharePreferenceStorageService(context);
        }

        return preferenceStorageService;
    }

    /**
     * 是否安装后的第一次运行
     *
     * @param versionName
     */
    public boolean isFirstLaunch(String versionName) {
        return SharedPref.getInstance(context).getBoolean("FirstLaunch" + versionName, true);
    }

    public void setFirstLaunch(String versionName) {
        SharedPref.getInstance(context).putBoolean("FirstLaunch" + versionName, false);
    }

    public String getPassword() {
        return SharedPref.getInstance(context).getString("pwd", "");
    }

    /**
     * 密码
     *
     * @param pwd
     */
    public void setPassword(String pwd) {
        SharedPref.getInstance(context).putString("pwd", Codec.MD5.getMD5(pwd));
    }

    /**
     * 登录信息
     *
     * @param isLogin
     * @param name
     * @param displayName
     * @param userId
     * @param mobile
     * @param token
     */
    public void setLogin(boolean isLogin, String name, String displayName, String userId, String mobile, String token) {
        SharedPref.getInstance(context).putBoolean("LoginStatus", isLogin);
        SharedPref.getInstance(context).putString("UserId", userId);
        SharedPref.getInstance(context).putString("Name", name);
        SharedPref.getInstance(context).putString("DisplayName", displayName);
        SharedPref.getInstance(context).putString("Mobile", mobile);
        SharedPref.getInstance(context).putString("AccessToken", token);
    }

    public boolean isLogin() {
        if (TextUtils.isEmpty(getUserId())) return false;
        return true;
    }

    public String getUserId() {
        return SharedPref.getInstance(context).getString("UserId", "");
    }

    public String getUserName() {
        return SharedPref.getInstance(context).getString("Name", "");
    }

    public String getUserDisplayName() {
        return SharedPref.getInstance(context).getString("DisplayName", "");
    }

    public String getMobile() {
        return SharedPref.getInstance(context).getString("Mobile", "");
    }

    public String getToken() {
        return SharedPref.getInstance(context).getString("AccessToken", "");
    }

    /**
     * 访问TOKEN
     */
    public void setToken(String access_token) {
        SharedPref.getInstance(context).putString("AccessToken", access_token);
    }

}
