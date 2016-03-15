package com.example.operatingsystemexperiment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.operatingsystemexperiment.MyApplication;
import com.example.operatingsystemexperiment.R;
import com.example.operatingsystemexperiment.bean.PCB;
import com.example.operatingsystemexperiment.util.BitGraphManageUtil;

import java.util.List;

/**
 * Created by 山东娃 on 2016/3/13.
 */
public class ShowMemoryAdapter extends RecyclerView.Adapter<ShowMemoryAdapter.MyViewHolder> {
    private Context mContext;
    private List<PCB> mPcbs;

    public ShowMemoryAdapter(Context mContext, List<PCB> mPcbs) {
        this.mContext = mContext;
        this.mPcbs = mPcbs;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new MyViewHolder(LayoutInflater.from(mContext).inflate(R.layout.cell_show_memory, parent, false));
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.setIsRecyclable(false);
        if(BitGraphManageUtil.getSingle().isSystemMemory(position)){
            holder.rootView.setBackgroundResource(R.color.black);
            return;
        }
        for(PCB pcb : mPcbs){
            if(pcb.getBitGraph().contains(position)){
                holder.rootView.setBackgroundResource(pcb.getColor());
                break;
            }
//                        if(pcb.getAdress() <= position && position < pcb.getAdress() + pcb.getSize()){
//                            holder.rootView.setBackgroundResource(pcb.getColor());
//                break;
//            }
        }
    }

    @Override
    public int getItemCount() {
        return MyApplication.getMaxMemorySize();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{

        View rootView;
        public MyViewHolder(View itemView) {
            super(itemView);
            rootView = itemView;
        }
    }
}
