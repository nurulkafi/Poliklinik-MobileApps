package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelPembayaranResep;
import com.kadazi.poliklinikapps.Model.DataModelPemeriksaanResep;
import com.kadazi.poliklinikapps.Model.DataModelResepDetails;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPemeriksaanResep extends RecyclerView.Adapter<AdapterDataPemeriksaanResep.HolderPemeriksaanResep>{

    private Context ctx;
    private List<DataModelPemeriksaanResep> list;

    public AdapterDataPemeriksaanResep(Context ctx, List<DataModelPemeriksaanResep> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPemeriksaanResep.HolderPemeriksaanResep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_details, parent, false);
        AdapterDataPemeriksaanResep.HolderPemeriksaanResep holder = new AdapterDataPemeriksaanResep.HolderPemeriksaanResep(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPemeriksaanResep.HolderPemeriksaanResep holder, int position) {
        DataModelPemeriksaanResep dm =list.get(position);
        holder.obat.setText(dm.getObat());
        holder.dosis.setText(dm.getDosis());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderPemeriksaanResep extends RecyclerView.ViewHolder{
        TextView obat;
        TextView dosis;


        public HolderPemeriksaanResep(@NonNull View itemView) {
            super(itemView);
            obat = itemView.findViewById(R.id.TextObat);
            dosis = itemView.findViewById(R.id.ObatHarga);
        }
    }
}