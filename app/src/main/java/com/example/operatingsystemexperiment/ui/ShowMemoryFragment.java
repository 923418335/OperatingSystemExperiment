package com.example.operatingsystemexperiment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.operatingsystemexperiment.MyApplication;
import com.example.operatingsystemexperiment.R;
import com.example.operatingsystemexperiment.adapter.ShowMemoryAdapter;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.ui.base.AbstractShowFragment;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 山东娃 on 2016/3/13.
 */
public class ShowMemoryFragment extends AbstractShowFragment {
    private View mRootView;
    private TextView mUserMemoryView;
    private TextView mRemainderMemoryView;
    private RecyclerView mShowMemoryView;
    private List<PCB> mAllPCBs = new ArrayList<>();
    private int mUserMemory;
    private int mRemainderMemory;
    private ShowMemoryAdapter memoryAdapter;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mRootView = inflater.inflate(R.layout.fragment_show_memory, container, false);
        log("onCreatView");
        findView();
        initView();
        updata();
        return mRootView;
    }

    private void initView() {
        memoryAdapter = new ShowMemoryAdapter(getActivity(), mAllPCBs);
        mShowMemoryView.setLayoutManager(new StaggeredGridLayoutManager(8, StaggeredGridLayoutManager.VERTICAL));
        mShowMemoryView.setAdapter(memoryAdapter);
    }

    private void findView() {
        mUserMemoryView  = (TextView) mRootView.findViewById(R.id.mUserMemory);
        mRemainderMemoryView = (TextView) mRootView.findViewById(R.id.mRemainderMemory);
        mShowMemoryView = (RecyclerView) mRootView.findViewById(R.id.mShowMemoryView);
    }

    @Override
    public String getTitle() {
        return "存储管理";
    }

    @Override
    public void add(PCB pcb) {

    }

    @Override
    public void remove(PCB pcb) {

    }

    @Override
    public void updata() {
        mAllPCBs.clear();
        mUserMemory = 0;
        mAllPCBs.addAll(((MainActivity)getActivity()).getBlockList());
        mAllPCBs.addAll(((MainActivity) getActivity()).getReadyList());
        mAllPCBs.addAll(((MainActivity)getActivity()).getRuningList());
        for(PCB pcb : mAllPCBs){
            mUserMemory += pcb.getSize();
        }
        mRemainderMemory = MyApplication.getMaxMemorySize() - mUserMemory;
        mUserMemoryView.setText("占用:" + mUserMemory + "KB");
        mRemainderMemoryView.setText("空闲" + mRemainderMemory + "KB");
        memoryAdapter.notifyDataSetChanged();
    }

}
