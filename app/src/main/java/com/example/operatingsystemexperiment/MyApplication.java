package com.example.operatingsystemexperiment;

import android.app.Application;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class MyApplication extends Application {
    private static int MaxPCBRuningNumber = 1;
    private static int MaxMemorySize = 64;

    public static int getMaxMemorySize() {
        return MaxMemorySize;
    }

    public static void setMaxMemorySize(int maxMemorySize) {
        MaxMemorySize = maxMemorySize;
    }

    public static void setMaxPCBRuning(int number){
        MaxPCBRuningNumber = number;
    }

    public static int getMaxPcbRuning(){
        return MaxPCBRuningNumber;
    }
}
