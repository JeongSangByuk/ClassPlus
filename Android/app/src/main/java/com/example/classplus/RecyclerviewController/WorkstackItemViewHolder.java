package com.example.classplus.RecyclerviewController;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.classplus.R;

public class WorkstackItemViewHolder extends RecyclerView.ViewHolder {

    protected ImageView img;
    protected TextView name;
    protected TextView description;
    protected TextView time;
    protected TextView title;


    public WorkstackItemViewHolder(@NonNull View itemView) {
        super(itemView);

        name = itemView.findViewById(R.id.tv_itemworkstack_name);
        img = itemView.findViewById(R.id.iv_itemworkstack_image);
        description = itemView.findViewById(R.id.tv_itemworkstack_description);
        time = itemView.findViewById(R.id.tv_itemworkstack_time);
        title = itemView.findViewById(R.id.tv_itemworkstack_title);
    }

}
