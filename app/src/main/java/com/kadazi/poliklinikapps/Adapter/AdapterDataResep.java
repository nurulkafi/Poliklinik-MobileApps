package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DetailResepActivity;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelResep;
import com.kadazi.poliklinikapps.Model.DataModelResepDetails;
import com.kadazi.poliklinikapps.Model.ResponseModelResepDetails;
import com.kadazi.poliklinikapps.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataResep extends RecyclerView.Adapter<AdapterDataResep.HolderDataResep>{

    private Context ctx;
    private List<DataModelResep> list;
    private List<DataModelResepDetails> list2;

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
        holder.tgl.setText(dm.getTgl_pendaftaran());
        holder.btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tampilData(Integer.parseInt(dm.getId()));
            }
        });
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
    public void tampilData(int id){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelResepDetails> tampilData = arData.DetailS_Resep(id);

        tampilData.enqueue(new Callback<ResponseModelResepDetails>() {
            @Override
            public void onResponse(Call<ResponseModelResepDetails> call, Response<ResponseModelResepDetails> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                list2 = response.body().getData();
                Log.d("berhasil","berhasil");
                String id,nama,poli,tgl_pendaftaran;
                nama = list2.get(0).getNama();
                poli = list2.get(0).getPoli();
                id   = list2.get(0).getId();
                tgl_pendaftaran = list2.get(0).getTgl_pendaftaran();
                Intent intent = new Intent(ctx, DetailResepActivity.class);
                intent.putExtra("nama",nama);
                intent.putExtra("poli",poli);
                intent.putExtra("tgl_pendaftaran",tgl_pendaftaran);
                intent.putExtra("id",id);
                ctx.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModelResepDetails> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}

