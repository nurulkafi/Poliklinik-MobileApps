package com.kadazi.poliklinikapps.Adapter;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.kadazi.poliklinikapps.Activity.DaftarActivity;
import com.kadazi.poliklinikapps.Activity.DaftarBaruActivity;
import com.kadazi.poliklinikapps.Activity.PengaturanActivity;
import com.kadazi.poliklinikapps.Activity.ResepActivity;
import com.kadazi.poliklinikapps.Activity.RiwayatPemeriksaanActivity;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelPendaftaranBaru;
import com.kadazi.poliklinikapps.Model.ResponseModel;
import com.kadazi.poliklinikapps.Model.ResponseModelPendaftaranBaru;
import com.kadazi.poliklinikapps.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

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
        holder.pilih.setOnClickListener(new View.OnClickListener() {
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

    public void showDialog(final String id) throws Exception {
        AlertDialog.Builder builder = new AlertDialog.Builder(ctx);

        builder.setMessage("Apakah Anda Yakin?");

        builder.setPositiveButton("Ya", new DialogInterface.OnClickListener(){

            @Override
            public void onClick(DialogInterface dialog, int which) {
                    APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
                    Call<ResponseModel> tampilData = arData.Pendaftaran(id,2);

                    tampilData.enqueue(new Callback<ResponseModel>() {
                        @Override
                        public void onResponse(Call<ResponseModel> call, Response<ResponseModel> response) {
                            boolean status = response.body().isSuccess();
                            String message = response.body().getMessage();

                            Log.d("true", message);
                            Toast toast = Toast.makeText(ctx, "Berhasil", Toast.LENGTH_SHORT);
                            toast.show();
                            Intent pindah = new Intent(ctx.getApplicationContext(),DaftarActivity.class);
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
