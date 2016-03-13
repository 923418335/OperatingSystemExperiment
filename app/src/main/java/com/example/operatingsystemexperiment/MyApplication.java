package com.example.operatingsystemexperiment;

import android.app.Application;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class MyApplication extends Application {
    private static int MaxPCBRuningNumber = 1;
    public static void setMaxPCBRuning(int number){
        MaxPCBRuningNumber = number;
    }

    public static int getMaxPcbRuning(){
        return MaxPCBRuningNumber;
    }
}
