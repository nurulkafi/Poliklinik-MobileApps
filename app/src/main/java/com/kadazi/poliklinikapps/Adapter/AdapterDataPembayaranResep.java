package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelPembayaran;
import com.kadazi.poliklinikapps.Model.DataModelPembayaranResep;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPembayaranResep extends RecyclerView.Adapter<AdapterDataPembayaranResep.Holder> {

    private Context ctx;
    private List<DataModelPembayaranResep> list;

    public AdapterDataPembayaranResep(Context ctx, List<DataModelPembayaranResep> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPembayaranResep.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_data_obat, parent, false);
        AdapterDataPembayaranResep.Holder holder = new AdapterDataPembayaranResep.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaranResep.Holder holder, int position) {
        DataModelPembayaranResep dm = list.get(position);
        holder.nama.setText(dm.getNama());
        holder.harga.setText(dm.getHarga_jual());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView nama,harga;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nama = itemView.findViewById(R.id.ObatResep);
            harga = itemView.findViewById(R.id.ObatHarga);
        }

    }
}
