package com.android.animation;

import android.os.Environment;
import android.util.Log;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

/**
 * Created by lbb on 2016/7/28.
 */
public class MIUIUtils {
    private static final String KEY_MIUI_VERSION_CODE = "ro.miui.ui.version.code";
    private static final String KEY_MIUI_VERSION_NAME = "ro.miui.ui.version.name";
    private static final String KEY_MIUI_INTERNAL_STORAGE = "ro.miui.internal.storage";
    public static boolean isMIUI() {
        /*if(SPUtils.getInstance().getCacheDataSP().contains("isMIUI"))
        {
            return SPUtils.getInstance().getCacheDataSP().getBoolean("isMIUI",false);
        }*/
        Properties prop= new Properties();
        boolean isMIUI;
        try {
            prop.load(new FileInputStream(new File(Environment.getRootDirectory(), "build.prop")));
        } catch (IOException e)
        {
            e.printStackTrace();
            return false;
        }
        isMIUI= prop.getProperty(KEY_MIUI_VERSION_CODE, null) != null
                || prop.getProperty(KEY_MIUI_VERSION_NAME, null) != null
                || prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null) != null;
        Log.e("1111111111111111",""+prop.getProperty(KEY_MIUI_VERSION_CODE, null));
        Log.e("2222222222222222",""+prop.getProperty(KEY_MIUI_VERSION_NAME, null));
        Log.e("3333333333333333",""+prop.getProperty(KEY_MIUI_INTERNAL_STORAGE, null));
        /*SPUtils.getInstance().putCacheData("isMIUI",isMIUI);//保存是否MIUI*/
        return isMIUI;
    }
}
