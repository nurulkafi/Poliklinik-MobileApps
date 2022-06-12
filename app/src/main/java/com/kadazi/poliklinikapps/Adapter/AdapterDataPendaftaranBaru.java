package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Model.DataModelPendaftaranBaru;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPendaftaranBaru extends RecyclerView.Adapter<AdapterDataPendaftaranBaru.HolderDataPendaftaranBaru> {

    private Context ctx;
    private List<DataModelPendaftaranBaru> list;

    public AdapterDataPendaftaranBaru(Context ctx, List<DataModelPendaftaranBaru> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPendaftaranBaru.HolderDataPendaftaranBaru onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_daftar_baru, parent, false);
        AdapterDataPendaftaranBaru.HolderDataPendaftaranBaru holder = new AdapterDataPendaftaranBaru.HolderDataPendaftaranBaru(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPendaftaranBaru.HolderDataPendaftaranBaru holder, int position) {
        DataModelPendaftaranBaru dm = list.get(position);
        holder.dokter.setText(dm.getDokter());
        holder.nama_poli.setText(dm.getNama_poli());
        holder.jam_mulai.setText(dm.getJam_mulai());
        holder.jam_selesai.setText(dm.getJam_selesai());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataPendaftaranBaru extends RecyclerView.ViewHolder {
        TextView dokter;
        TextView nama_poli;
        TextView jam_mulai;
        TextView jam_selesai;
        Button pilih;

        public HolderDataPendaftaranBaru(@NonNull View itemView) {
            super(itemView);
            dokter = itemView.findViewById(R.id.daftar_baru_nama_dokter);
            nama_poli = itemView.findViewById(R.id.daftar_baru_poli);
            jam_mulai = itemView.findViewById(R.id.daftar_baru_mulai);
            jam_selesai = itemView.findViewById(R.id.daftar_baru_selesai);
            pilih = itemView.findViewById(R.id.daftar_baru_pilih);
        }

    }
}
