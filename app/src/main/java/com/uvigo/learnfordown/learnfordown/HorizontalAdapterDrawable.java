package com.uvigo.learnfordown.learnfordown;

import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.LinearLayout;

import java.util.List;


public class HorizontalAdapterDrawable extends RecyclerView.Adapter<HorizontalAdapterDrawable.MyViewHolder> {

    private static OnItemClickListener onItemClickListener;

    private List<Integer> horizontalList;
    private int numeroletras;
    DisplayMetrics metrics;
    LinearLayout llImg;


    public static interface OnItemClickListener {
        public void onItemClick(View view, int position);
    }
    public void setOnItemClickListener(OnItemClickListener listener) {
        onItemClickListener = listener;
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public ImageButton button;

        public MyViewHolder(View view) {
            super(view);
            button = (ImageButton) view.findViewById(R.id.button4);
            button.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int position  = MyViewHolder.super.getAdapterPosition();
                    onItemClickListener.onItemClick(view,position);
                }
            });

        }
    }

    public HorizontalAdapterDrawable(List<Integer> horizontalList, int numeroletras, DisplayMetrics metrics) {
        this.horizontalList = horizontalList;
        this.numeroletras =numeroletras;
        this.metrics =metrics;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;

        itemView = LayoutInflater.from(parent.getContext()).inflate(R.layout.horizontal_item_view_drawable, parent, false);
        llImg = (LinearLayout) itemView.findViewById(R.id.linearLayout);



        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels


        llImg.getLayoutParams().width = width/ numeroletras;
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        // holder.button.setText(horizontalList.get(position));
        // holder.button.setImageDrawable(horizontalList.get(position));
        holder.button.setImageResource(horizontalList.get(position));
    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}