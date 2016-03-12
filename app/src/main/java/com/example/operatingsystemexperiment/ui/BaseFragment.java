package com.example.operatingsystemexperiment.ui;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.widget.Toast;

import com.example.operatingsystemexperiment.util.LogUtil;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class BaseFragment extends Fragment {
    private String TAG;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        TAG = getClass().getSimpleName();
    }

    protected void log(String line){
        LogUtil.i(TAG, line);
    }

    protected void toast(String line){
        Toast.makeText(getActivity(), line, Toast.LENGTH_SHORT).show();
    }

}
