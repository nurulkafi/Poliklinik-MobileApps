package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DetailJadwalDokter;
import com.kadazi.poliklinikapps.Activity.DetailResepActivity;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPoli;
import com.kadazi.poliklinikapps.Model.DataModelResep;
import com.kadazi.poliklinikapps.Model.DataModelResepDetails;
import com.kadazi.poliklinikapps.Model.ResponseModelResepDetails;
import com.kadazi.poliklinikapps.R;
import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataPoli extends RecyclerView.Adapter<AdapterDataPoli.HolderDataPoli> {

    private Context ctx;
    private List<DataModelPoli> list;

    public AdapterDataPoli(Context ctx, List<DataModelPoli> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataPoli.HolderDataPoli onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_poli, parent, false);
        AdapterDataPoli.HolderDataPoli holder = new AdapterDataPoli.HolderDataPoli(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataPoli.HolderDataPoli holder, int position) {
        DataModelPoli dm = list.get(position);
        holder.poli.setText(dm.getNama());
        Picasso.with(ctx).load("https://kadazi-klinik.herokuapp.com/" + dm.getImage()  ).into(holder.img);
        holder.img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailJadwalDokter.class);
                intent.putExtra("kode_poli",dm.getKode_poli());
                ctx.startActivity(intent);
            }
        });
        holder.poli.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ctx, DetailJadwalDokter.class);
                intent.putExtra("kode_poli",dm.getKode_poli());
                ctx.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class HolderDataPoli extends RecyclerView.ViewHolder {
        TextView poli;
        ImageButton img;

        public HolderDataPoli(@NonNull View itemView) {
            super(itemView);
            poli = itemView.findViewById(R.id.poli_nama);
            img = itemView.findViewById(R.id.poli_icon);
        }

    }
}
