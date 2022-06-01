package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelRiwayat;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataRiwayat extends RecyclerView.Adapter<AdapterDataRiwayat.HolderDataRiwayat>{

    private Context ctx;
    private List<DataModelRiwayat> list;

    public AdapterDataRiwayat(Context ctx, List<DataModelRiwayat> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataRiwayat.HolderDataRiwayat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_riwayat_pemeriksaan, parent, false);
        AdapterDataRiwayat.HolderDataRiwayat holder = new AdapterDataRiwayat.HolderDataRiwayat(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataRiwayat.HolderDataRiwayat holder, int position) {
        DataModelRiwayat dm =list.get(position);
        holder.nama_dokter.setText("Nama Dokter :" + dm.getNama());
        holder.nama_poliklinik.setText("Nama Poliklinik : "+dm.getPoli());
        holder.tgl.setText("Tanggal Pemeriksaan : "+dm.getTgl_pendaftaran());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataRiwayat extends RecyclerView.ViewHolder{
        TextView nama_dokter;
        TextView nama_poliklinik;
        TextView tgl;
        Button btn;

        public HolderDataRiwayat(@NonNull View itemView) {
            super(itemView);
            nama_dokter = itemView.findViewById(R.id.riwayat_nama_dokter);
            nama_poliklinik = itemView.findViewById(R.id.riwayat_nama_poliklinik);
            tgl = itemView.findViewById(R.id.riwayat_nama_tgl);
            btn = itemView.findViewById(R.id.riwayat_btn_details);
        }

    }
}
