package com.example.operatingsystemexperiment.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.operatingsystemexperiment.adapter.ShowPCBDetailAdapter;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.util.Constant;

import java.util.List;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class ShowPCBFragment extends AbstractShowFragment {
    private String title;
    private int state;
    private RecyclerView mListView;
    private List<PCB> pcbs;
    private ShowPCBDetailAdapter mAdapter;

    public ShowPCBFragment(int state) {
        this.state = state;
        switch (state){
            case Constant.READY:
                title = "就绪";
                break;
            case Constant.RUNING:
                title = "运行";
                break;
            case Constant.BLOCK:
                title = "阻塞";
                break;
        }
    }


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        switch (state){
            case Constant.READY:
                pcbs = ((MainActivity)getActivity()).getReadyList();
                break;
            case Constant.RUNING:
                pcbs = ((MainActivity)getActivity()).getRuningList();
                break;
            case Constant.BLOCK:
                pcbs = ((MainActivity)getActivity()).getBlockList();
                break;
        }
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = new RecyclerView(getActivity());
        mAdapter = new ShowPCBDetailAdapter(pcbs, getActivity());
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mAdapter);
        mAdapter.notifyDataSetChanged();
        return mListView;
    }

    @Override
    public String getTitle() {
        return title;
    }

    @Override
    public void add(PCB pcb) {
        pcbs.add(pcb);
        mAdapter.notifyDataSetChanged();
    }

    @Override
    public void remove(PCB pcb) {
        pcbs.remove(pcb);
        mAdapter.notifyDataSetChanged();
    }
}
