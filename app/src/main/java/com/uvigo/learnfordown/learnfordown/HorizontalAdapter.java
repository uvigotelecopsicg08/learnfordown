package com.uvigo.learnfordown.learnfordown;

import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

/**
 * Created by zadak on 06/02/2017.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<String> horizontalList;
    private int numeroletras;
    DisplayMetrics metrics;
    LinearLayout llImg;
    String Tipo;
    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.button4);

        }
    }


    public HorizontalAdapter(List<String> horizontalList,int numeroletras,DisplayMetrics metrics,String Tipo) {
        this.horizontalList = horizontalList;
        this.numeroletras =numeroletras;
        this.metrics =metrics;
        this.Tipo = Tipo;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = null;
        if (Tipo.equals("escritura")) {
            itemView = LayoutInflater.from(parent.getContext())
                    .inflate(R.layout.horizontal_item_view, parent, false);
        } else {
            if (Tipo.equals("lectura")) {
                itemView = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.horizontal_item_view, parent, false);
            }
        }
        llImg = (LinearLayout) itemView.findViewById(R.id.linearLayout);



        int width = metrics.widthPixels; // ancho absoluto en pixels
        int height = metrics.heightPixels; // alto absoluto en pixels


        llImg.getLayoutParams().width = width/ numeroletras;
        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, final int position) {
        holder.button.setText(horizontalList.get(position));

    }

    @Override
    public int getItemCount() {
        return horizontalList.size();
    }
}
