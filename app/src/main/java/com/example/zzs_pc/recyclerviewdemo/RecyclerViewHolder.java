package com.example.zzs_pc.recyclerviewdemo;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

/**
 * Created by ZZS_PC on 2017/5/11.
 */
public class RecyclerViewHolder extends RecyclerView.ViewHolder {
    public TextView textView;

    public RecyclerViewHolder(View itemView) {
        super(itemView);
        textView= (TextView) itemView.findViewById(R.id.textView);
    }
}
