package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DetailPembayaran;
import com.kadazi.poliklinikapps.Model.DataModelJadwal;
import com.kadazi.poliklinikapps.Model.DataModelPembayaran;
import com.kadazi.poliklinikapps.R;

import java.util.List;

public class AdapterDataPembayaran extends RecyclerView.Adapter<AdapterDataPembayaran.Holder> {

    private Context ctx;
    private List<DataModelPembayaran> list;

    public AdapterDataPembayaran(Context ctx, List<DataModelPembayaran> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPembayaran.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_pembayaran, parent, false);
        AdapterDataPembayaran.Holder holder = new AdapterDataPembayaran.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPembayaran.Holder holder, int position) {
        DataModelPembayaran dm = list.get(position);

        holder.id.setText(dm.getId_pendaftran());
        holder.tanggal_pendaftaran.setText(dm.getTanggal_pendaftaran());
        holder.total_pembayaran.setText("Rp. " + dm.getTotal_pembayaran());
        holder.status.setText(dm.getStatus());
        if(dm.getStatus().equals("Belum Bayar")){
            holder.status.setBackgroundResource(R.drawable.background_merah);
            holder.btn.setText("Bayar");
            holder.btn.setBackgroundResource(R.drawable.background_birulangit);
            holder.status.setPadding(10,10,10,10);
        }else {
            holder.status.setBackgroundResource(R.drawable.background_birulangit);
            holder.status.setPadding(10,10,10,10);
            holder.btn.setTextColor(Color.parseColor("#575CE5"));
            holder.btn.setBackgroundResource(R.drawable.background_grey3);
            holder.btn.setText("Detail");
        }
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailPembayaran.class);
                intent.putExtra("id",dm.getId_pendaftran());
                intent.putExtra("total",dm.getTotal_pembayaran());
                intent.putExtra("status",dm.getStatus());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView id,tanggal_pendaftaran,status,total_pembayaran;
        Button btn;
        public Holder(@NonNull View itemView) {
            super(itemView);
            id = itemView.findViewById(R.id.no_pendaftraan);
            tanggal_pendaftaran = itemView.findViewById(R.id.tgl_pendaftaran);
            status = itemView.findViewById(R.id.status_pembayaran);
            total_pembayaran = itemView.findViewById(R.id.total);
            btn = itemView.findViewById(R.id.btn_pem);
        }

    }
}
