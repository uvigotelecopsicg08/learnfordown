package com.uvigo.learnfordown.learnfordown;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import java.util.List;
import android.support.v7.widget.RecyclerView;

/**
 * Created by zadak on 06/02/2017.
 */
public class HorizontalAdapter extends RecyclerView.Adapter<HorizontalAdapter.MyViewHolder> {

    private List<String> horizontalList;

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public Button button;

        public MyViewHolder(View view) {
            super(view);
            button = (Button) view.findViewById(R.id.button4);

        }
    }


    public HorizontalAdapter(List<String> horizontalList) {
        this.horizontalList = horizontalList;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.horizontal_item_view, parent, false);

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
