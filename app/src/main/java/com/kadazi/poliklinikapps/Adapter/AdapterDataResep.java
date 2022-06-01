package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelResep;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataResep extends RecyclerView.Adapter<AdapterDataResep.HolderDataResep>{

    private Context ctx;
    private List<DataModelResep> list;

    public AdapterDataResep(Context ctx, List<DataModelResep> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public HolderDataResep onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_resep, parent, false);
        AdapterDataResep.HolderDataResep holder = new AdapterDataResep.HolderDataResep(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull HolderDataResep holder, int position) {
        DataModelResep dm =list.get(position);
        holder.nama_dokter.setText(dm.getNama());
        holder.nama_poliklinik.setText(dm.getPoli());
        holder.tgl.setText(dm.getTanggal());
        if(dm.getTanggal().equals("tidak ada")){
            holder.btn.setVisibility(View.GONE);
        };
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataResep extends RecyclerView.ViewHolder{
        TextView nama_dokter;
        TextView nama_poliklinik;
        TextView tgl;
        Button btn;

        public HolderDataResep(@NonNull View itemView) {
            super(itemView);
            nama_dokter = itemView.findViewById(R.id.resep_nama_dokter);
            nama_poliklinik = itemView.findViewById(R.id.resep_nama_poliklinik);
            tgl = itemView.findViewById(R.id.resep_nama_tgl);
            btn = itemView.findViewById(R.id.resep_btn_details);
        }

    }
}
