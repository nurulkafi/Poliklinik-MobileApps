package com.kadazi.poliklinikapps.Adapter;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.kadazi.poliklinikapps.Activity.DaftarActivity;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelAntrian;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaran;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.daftar_btn_hapus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try{
                    showDialog(String.valueOf(dm.getId()));
                } catch (Exception e){
                    e.printStackTrace();
                }
                /*Log.v("log softgain : ", String.valueOf(idData));*/
            }
        });

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

    public void showDialog(final String id) throws Exception {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage("Apakah Anda Yakin?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
                Call<ResponseModel> tampilData = arData.HapusDaftar(id);

                tampilData.enqueue(new Callback<ResponseModel>() {
                    @Override
                    public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                        boolean status = response.body().isSuccess();
                        String message = response.body().getMessage();

                        Log.d("true", message);
                        Toast toast = Toast.makeText(ctx, "Berhasil", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent pindah = new Intent(ctx.getApplicationContext(), DaftarActivity.class);
                        ctx.startActivity(pindah);
                    }

                    @Override
                    public void onFailure(Call<ResponseModel> call, Throwable t) {
                        Log.d("ggl", t.getMessage());
                    }
                });
            }
        });


        builder.setNegativeButton("Tidak", new DialogInterface.OnClickListener(){
            @Override
            public void onClick(DialogInterface dialog, int which){
                dialog.dismiss();
            }
        });
        builder.show();
    }
}
