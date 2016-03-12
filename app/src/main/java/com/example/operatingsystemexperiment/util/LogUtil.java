package com.example.operatingsystemexperiment.util;

import android.util.Log;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class LogUtil {
    public static void i(String tag, String line){
        if(Constant.DEBUG){
            Log.i(tag, line);
        }
    }
}
