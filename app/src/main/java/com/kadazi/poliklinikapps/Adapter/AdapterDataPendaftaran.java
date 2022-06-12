package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelAntrian;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaran;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPendaftaran extends RecyclerView.Adapter<AdapterDataPendaftaran.HolderDataPendaftaran> {

    private Context ctx;
    private List<DataModelPendaftaran> list;

    public AdapterDataPendaftaran(Context ctx, List<DataModelPendaftaran> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPendaftaran.HolderDataPendaftaran onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_daftar, parent, false);
        AdapterDataPendaftaran.HolderDataPendaftaran holder = new AdapterDataPendaftaran.HolderDataPendaftaran(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPendaftaran.HolderDataPendaftaran holder, int position) {
        DataModelPendaftaran dm = list.get(position);
        holder.dokter.setText(dm.getDokter());
        holder.tgl_pendaftaran.setText(dm.getTgl_pendaftaran());
        holder.nama_poli.setText(dm.getNama_poli());
        holder.status.setText(dm.getStatus());
        if(dm.getStatus().equals("Sudah Diperiksa")){
            holder.status.setBackgroundResource(R.drawable.background_birulangit);
            holder.daftar_btn_hapus.setVisibility(View.GONE);
            holder.status.setPadding(6,4,6,4);
        }else {
            holder.status.setBackgroundResource(R.drawable.background_merah);
            holder.status.setPadding(6,4,6,4);
        }

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataPendaftaran extends RecyclerView.ViewHolder {
        TextView dokter;
        TextView tgl_pendaftaran;
        TextView nama_poli;
        TextView status;
        Button daftar_btn_hapus;

        public HolderDataPendaftaran(@NonNull View itemView) {
            super(itemView);
            dokter = itemView.findViewById(R.id.daftar_dokter);
            tgl_pendaftaran = itemView.findViewById(R.id.daftar_tgl_pendaftaran);
            nama_poli = itemView.findViewById(R.id.daftar_nama_poli);
            status = itemView.findViewById(R.id.status);
            daftar_btn_hapus = itemView.findViewById(R.id.daftar_btn_hapus);
        }

    }
}
