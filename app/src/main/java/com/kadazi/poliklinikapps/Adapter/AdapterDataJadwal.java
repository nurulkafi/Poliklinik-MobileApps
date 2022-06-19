package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DetailJadwalDokter;
import com.kadazi.poliklinikapps.Model.DataModelJadwal;
import com.kadazi.poliklinikapps.Model.DataModelPoli;
import com.kadazi.poliklinikapps.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDataJadwal extends RecyclerView.Adapter<AdapterDataJadwal.HolderDataJadwal> {

    private Context ctx;
    private List<DataModelJadwal> list;

    public AdapterDataJadwal(Context ctx, List<DataModelJadwal> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataJadwal.HolderDataJadwal onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_jadwal_dokter, parent, false);
        AdapterDataJadwal.HolderDataJadwal holder = new AdapterDataJadwal.HolderDataJadwal(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataJadwal.HolderDataJadwal holder, int position) {
        DataModelJadwal dm = list.get(position);
        holder.dokter.setText(dm.getDokter());
        holder.jam.setText(dm.getJam_mulai().substring(0,5) + " - " +dm.getJam_selesai().substring(0,5));
        holder.hari.setText(dm.getHari());

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataJadwal extends RecyclerView.ViewHolder {
        TextView dokter,jam,hari;

        public HolderDataJadwal(@NonNull View itemView) {
            super(itemView);
            dokter = itemView.findViewById(R.id.dokter);
            jam = itemView.findViewById(R.id.jam);
            hari = itemView.findViewById(R.id.hari);
        }

    }
}
