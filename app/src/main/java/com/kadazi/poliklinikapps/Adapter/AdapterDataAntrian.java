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
import com.kadazi.poliklinikapps.Model.DataModelDetailPemeriksaan;
import com.kadazi.poliklinikapps.Model.DataModelRiwayat;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataAntrian extends RecyclerView.Adapter<AdapterDataAntrian.HolderDataAntrian> {

    private Context ctx;
    private List<DataModelAntrian> list;

    public AdapterDataAntrian(Context ctx, List<DataModelAntrian> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataAntrian.HolderDataAntrian onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_antrian, parent, false);
        AdapterDataAntrian.HolderDataAntrian holder = new AdapterDataAntrian.HolderDataAntrian(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataAntrian.HolderDataAntrian holder, int position) {
        DataModelAntrian dm = list.get(position);
        holder.hari.setText(dm.getHari());
        holder.jam_mulai.setText(dm.getJam_mulai());
        holder.jam_selesai.setText(dm.getJam_selesai());
        holder.dokter.setText(dm.getDokter());
        holder.no_antrian.setText("Ke - "+dm.getNo_antrian());
        holder.nama_poli.setText(dm.getNama_poli());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataAntrian extends RecyclerView.ViewHolder {
        TextView hari;
        TextView jam_mulai;
        TextView jam_selesai;
        TextView dokter;
        TextView no_antrian;
        TextView nama_poli;

        public HolderDataAntrian(@NonNull View itemView) {
            super(itemView);
            hari = itemView.findViewById(R.id.hari);
            jam_mulai = itemView.findViewById(R.id.jam_mulai);
            jam_selesai = itemView.findViewById(R.id.jam_selesai);
            dokter = itemView.findViewById(R.id.dokter);
            no_antrian = itemView.findViewById(R.id.no_antrian);
            nama_poli = itemView.findViewById(R.id.nama_poli);
        }

    }
}
