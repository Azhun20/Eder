package com.azissulaiman.eder;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

// The adapter class which
// extends RecyclerView Adapter
public class Adapter
        extends RecyclerView.Adapter<Adapter.BukuViewHolder> {

    private ArrayList<Buku> dataList;
    private ItemClickListener clickListener;

    public Adapter(ArrayList<Buku> dataList) {

        this.dataList = dataList;
    }

    @Override
    public BukuViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item, parent, false);
        return new BukuViewHolder(view);

    }

    @Override
    public void onBindViewHolder(BukuViewHolder holder, int position) {
        holder.imgCover.setImageResource(dataList.get(position).getCover());
        holder.txtJudul.setText(dataList.get(position).getJudul());
        holder.txtPenulis.setText(dataList.get(position).getPenulis());
        holder.txtRating.setText(dataList.get(position).getRating());

    }

    @Override
    public int getItemCount() {
        return (dataList != null) ? dataList.size() : 0;
    }

    public void setClickListener(ExploreFragment itemClickListener) {
        this.clickListener = clickListener;
    }

    public class BukuViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        private ImageView imgCover;
        private TextView txtJudul, txtPenulis, txtRating;

        public BukuViewHolder(View itemView) {
            super(itemView);
            imgCover = (ImageView) itemView.findViewById(R.id.imgCoverBuku);
            txtJudul = (TextView) itemView.findViewById(R.id.txtJudul);
            txtPenulis = (TextView) itemView.findViewById(R.id.txtPenulis);
            txtRating = (TextView) itemView.findViewById(R.id.txtRating);
            itemView.setTag(itemView);
            itemView.setOnClickListener(this);
            imgCover.setOnClickListener(this::onClick);
        }

        @Override
        public void onClick(View v) {
            if (clickListener != null) clickListener.onClick(v, getAdapterPosition());
            Intent i = new Intent(v.getContext(), Map.class);
            v.getContext().startActivity(i);
        }
    }
}
