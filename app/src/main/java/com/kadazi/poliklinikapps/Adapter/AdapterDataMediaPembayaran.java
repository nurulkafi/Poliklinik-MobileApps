package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DetailPembayaran;
import com.kadazi.poliklinikapps.Activity.UploadBuktiPembayaranActivity;
import com.kadazi.poliklinikapps.Model.DataModelMediaPembayaran;
import com.kadazi.poliklinikapps.Model.DataModelPembayaran;
import com.kadazi.poliklinikapps.Model.Temp;
import com.kadazi.poliklinikapps.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AdapterDataMediaPembayaran extends RecyclerView.Adapter<AdapterDataMediaPembayaran.Holder> {

    private Context ctx;
    private List<DataModelMediaPembayaran> list;

    public AdapterDataMediaPembayaran(Context ctx, List<DataModelMediaPembayaran> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataMediaPembayaran.Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_media_pembayaran, parent, false);
        AdapterDataMediaPembayaran.Holder holder = new AdapterDataMediaPembayaran.Holder(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataMediaPembayaran.Holder holder, int position) {
        DataModelMediaPembayaran dm = list.get(position);
        holder.nama_bank.setText(dm.getNama_bank());
        holder.no_rek.setText("Rp. " + dm.getNo_rekening());
        holder.atas_nama.setText(dm.getAtas_nama());
        Picasso.with(ctx).load("https://kadazi-klinik.herokuapp.com/" + dm.getLogo()  ).into(holder.logo);
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, UploadBuktiPembayaranActivity.class);
                ctx.startActivity(intent);

            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class Holder extends RecyclerView.ViewHolder {
        TextView nama_bank,no_rek,atas_nama;
        ImageView logo;
        Button btn;
        public Holder(@NonNull View itemView) {
            super(itemView);
            nama_bank = itemView.findViewById(R.id.nama_bank);
            no_rek = itemView.findViewById(R.id.no_rek);
            atas_nama = itemView.findViewById(R.id.atas_nama);
            btn = itemView.findViewById(R.id.btn_pilih_pembayaran);
            logo = itemView.findViewById(R.id.image_media_pembayaran);
        }

    }
}
