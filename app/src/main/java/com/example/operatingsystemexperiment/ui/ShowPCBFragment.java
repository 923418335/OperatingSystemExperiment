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

import java.util.ArrayList;
import java.util.List;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class ShowPCBFragment extends AbstractShowFragment {
    String title;
    private RecyclerView mListView;
    private List<PCB> pcbs;
    private ShowPCBDetailAdapter mAdapter;

    public ShowPCBFragment(String title) {
        this.title = title;
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        mListView = new RecyclerView(getActivity());
        pcbs = new ArrayList<>();
        mAdapter = new ShowPCBDetailAdapter(pcbs, getActivity());
        mListView.setLayoutManager(new LinearLayoutManager(getActivity()));
        mListView.setAdapter(mAdapter);
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
