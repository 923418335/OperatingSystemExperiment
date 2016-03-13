package com.example.operatingsystemexperiment.ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.operatingsystemexperiment.adapter.ShowPCBDetailAdapter;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.ui.base.AbstractShowFragment;
import com.example.operatingsystemexperiment.util.Constant;
import com.example.operatingsystemexperiment.util.MemoryManageUtil;

import java.util.List;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class ShowPCBFragment extends AbstractShowFragment implements ShowPCBDetailAdapter.OnClickListener {
    private String title;
    private int state;
    private RecyclerView mListView;
    private List<PCB> pcbs;
    private ShowPCBDetailAdapter mAdapter;

    public ShowPCBFragment(int state) {
        this.state = state;
        switch (state){
            case Constant.READY:
                title = "就绪进程";
                break;
            case Constant.RUNING:
                title = "运行进程";
                break;
            case Constant.BLOCK:
                title = "阻塞进程";
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
        mAdapter.setOnClickListener(this);
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

    @Override
    public void updata() {
        mAdapter.notifyDataSetChanged();
    }


    String[] items = new String[]{"时间片到", "进程阻塞", "唤醒进程", "结束进程"};
    @Override
    public void onClick(View v, final int position) {
        new AlertDialog.Builder(getActivity()).setTitle("请选择事件").setItems(items, new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                switch (which) {
                    case 0:
                        if (state != Constant.RUNING) {
                            toast("时间片到");
                            return;
                        }
                        ((MainActivity)getActivity()).timeSliceOverPCB(pcbs.get(position));
                        break;
                    case 1:
                        if (state != Constant.RUNING) {
                            toast("此进程未运行,无法堵塞");
                            return;
                        }
                        ((MainActivity)getActivity()).blockPCB(pcbs.get(position));
                        break;
                    case 2:
                        if (state != Constant.BLOCK) {
                            toast("此进程未堵塞,无法唤醒");
                            return;
                        }
                        ((MainActivity)getActivity()).awaken(pcbs.get(position));
                        break;
                    case 3:
                        toast("次进程已经结束");
                        MemoryManageUtil.getSingle().recoveryPCB(pcbs.get(position));
                        pcbs.remove(position);
                        ((MainActivity) getActivity()).dispatchPCB();
                        mAdapter.notifyDataSetChanged();
                        break;
                }
            }
        }).show();
    }
}
