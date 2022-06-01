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

import com.kadazi.poliklinikapps.Activity.DetailPemeriksaanActivity;
import com.kadazi.poliklinikapps.Api.APIRequestData;
import com.kadazi.poliklinikapps.Api.RetroServer;
import com.kadazi.poliklinikapps.Model.DataModelDetailPemeriksaan;
import com.kadazi.poliklinikapps.Model.DataModelRiwayat;
import com.kadazi.poliklinikapps.Model.ResponseModelDetailPemeriksaan;
import com.kadazi.poliklinikapps.R;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class AdapterDataRiwayat extends RecyclerView.Adapter<AdapterDataRiwayat.HolderDataRiwayat>{

    private Context ctx;
    private List<DataModelRiwayat> list;
    private List<DataModelDetailPemeriksaan> list2;

    public AdapterDataRiwayat(Context ctx, List<DataModelRiwayat> list) {
        this.ctx = ctx;
        this.list = list;
    }

    @NonNull
    @Override
    public AdapterDataRiwayat.HolderDataRiwayat onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View layout = LayoutInflater.from(ctx).inflate(R.layout.list_riwayat_pemeriksaan, parent, false);
        AdapterDataRiwayat.HolderDataRiwayat holder = new AdapterDataRiwayat.HolderDataRiwayat(layout);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterDataRiwayat.HolderDataRiwayat holder, int position) {
        DataModelRiwayat dm =list.get(position);
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

    public static class HolderDataRiwayat extends RecyclerView.ViewHolder{
        TextView nama_dokter;
        TextView nama_poliklinik;
        TextView tgl;
        Button btn;

        public HolderDataRiwayat(@NonNull View itemView) {
            super(itemView);
            nama_dokter = itemView.findViewById(R.id.riwayat_nama_dokter);
            nama_poliklinik = itemView.findViewById(R.id.riwayat_nama_poliklinik);
            tgl = itemView.findViewById(R.id.riwayat_nama_tgl);
            btn = itemView.findViewById(R.id.riwayat_btn_details);
        }

    }
    public void tampilData(int id){
        APIRequestData arData = RetroServer.konekRetrofit().create(APIRequestData.class);
        Call<ResponseModelDetailPemeriksaan> tampilData = arData.Detail(id);

        tampilData.enqueue(new Callback<ResponseModelDetailPemeriksaan>() {
            @Override
            public void onResponse(Call<ResponseModelDetailPemeriksaan> call, Response<ResponseModelDetailPemeriksaan> response) {
                boolean status = response.body().isSuccess();
                String message  = response.body().getMessage();

                Log.d("true",message);
                list2 = response.body().getData();
                Log.d("berhasil","berhasil");
                String id,nama,poli,tgl_pendaftaran,keluhan,diagnosa,perawatan,tindakan,bb,tensi;
                nama = list2.get(0).getNama();
                poli = list2.get(0).getPoli();
                tgl_pendaftaran = list2.get(0).getTgl_pendaftaran();
                keluhan = list2.get(0).getKeluhan();
                diagnosa = list2.get(0).getDiagnosa();
                perawatan = list2.get(0).getPerawatan();
                tindakan = list2.get(0).getTindakan();
                bb = list2.get(0).getBb();
                tensi = list2.get(0).getTensi();
                Intent intent = new Intent(ctx, DetailPemeriksaanActivity.class);
                intent.putExtra("nama",nama);
                intent.putExtra("poli",poli);
                intent.putExtra("tgl_pendaftaran",tgl_pendaftaran);
                intent.putExtra("keluhan",keluhan);
                intent.putExtra("diagnosa",diagnosa);
                intent.putExtra("perawatan",perawatan);
                intent.putExtra("tindakan",tindakan);
                intent.putExtra("bb",bb);
                intent.putExtra("tensi",tensi);
                ctx.startActivity(intent);
            }

            @Override
            public void onFailure(Call<ResponseModelDetailPemeriksaan> call, Throwable t){
                Log.d("ggl",t.getMessage());

            }
        });
    }
}
