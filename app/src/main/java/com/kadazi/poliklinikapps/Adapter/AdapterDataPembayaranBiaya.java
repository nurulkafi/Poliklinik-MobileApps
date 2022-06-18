package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelPembayaranBiaya;
import com.kadazi.poliklinikapps.Model.DataModelPembayaranResep;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPembayaranBiaya extends RecyclerView.Adapter<AdapterDataPembayaranBiaya.Holder> {

    private Context ctx;
    private List<DataModelPembayaranBiaya> list;

    public AdapterDataPembayaranBiaya(Context ctx, List<DataModelPembayaranBiaya> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPembayaranBiaya.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_detail_biaya, parent, false);
        AdapterDataPembayaranBiaya.Holder holder = new AdapterDataPembayaranBiaya.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaranBiaya.Holder holder, int position) {
        DataModelPembayaranBiaya dm = list.get(position);
        holder.nama.setText(dm.getNama());
        holder.harga.setText(dm.getTarif());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView nama,harga;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.BiayaNama);
            harga = itemView.findViewById(R.id.BiayaTarif);
        }

    }
}
