package com.example.operatingsystemexperiment.adapter;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.operatingsystemexperiment.R;
import com.example.operatingsystemexperiment.bean.PCB;

import java.util.List;

/**
 * Created by 山东娃 on 2016/3/12.
 */
public class ShowPCBDetailAdapter extends RecyclerView.Adapter<ShowPCBDetailAdapter.MyViewHolder> {
    private List<PCB> pcbs;
    private Context mContext;
    private OnClickListener onClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ShowPCBDetailAdapter(List<PCB> pcbs, Context mContext) {
        this.pcbs = pcbs;
        this.mContext = mContext;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View rootView = LayoutInflater.from(mContext).inflate(R.layout.cell_pcb_detail, parent, false);
        return new MyViewHolder(rootView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.position = position;
        Log.i("fuck", "position" + position);
        Log.i("fuck", String.valueOf(pcbs.get(position) == null));
        holder.showTextView.setText(pcbs.get(position).toString());
        holder.showTextView.setTextColor(mContext.getResources().getColor(pcbs.get(position).getColor()));
    }

    @Override
    public int getItemCount() {
        return pcbs.size();
    }

    class MyViewHolder extends RecyclerView.ViewHolder{
        int position;
        TextView showTextView;
        public MyViewHolder(View itemView) {
            super(itemView);
            showTextView = (TextView) itemView.findViewById(R.id.mShowPCB);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (onClickListener != null) {
                        onClickListener.onClick(v, position);
                    }
                }
            });
        }
    }

    public interface OnClickListener{
        void onClick(View v, int position);
    }

}
