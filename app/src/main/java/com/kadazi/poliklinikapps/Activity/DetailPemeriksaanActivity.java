package com.kadazi.poliklinikapps.Activity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.kadazi.poliklinikapps.R;

public class DetailPemeriksaanActivity extends AppCompatActivity {
    TextView Nama_dokter;
    TextView Nama_poli;
    TextView tgl_pendaftaran;
    TextView keluhan;
    TextView diagnosa;
    TextView perawatan;
    TextView tindakan;
    TextView bb;
    TextView tensi;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_pemeriksaan);

        Nama_dokter = findViewById(R.id.detail_pemeriksaan_dokter);
        Nama_poli = findViewById(R.id.detail_pemeriksaan_poliklinik);
        tgl_pendaftaran = findViewById(R.id.detail_pemeriksaan_tgl);
        keluhan = findViewById(R.id.keluhan_pemeriksaan);
        diagnosa = findViewById(R.id.diagnosa_pemeriksaan);
        perawatan = findViewById(R.id.perawatan_pemeriksaan);
        tindakan = findViewById(R.id.tindakan_pemeriksaan);
        bb = findViewById(R.id.bb_pemeriksaan);
        tensi = findViewById(R.id.tensi_pemeriksaan);

        Intent terima = getIntent();
        String xnama_dokter = terima.getStringExtra("nama");
        String xnama_poli = terima.getStringExtra("poli");
        String xtgl_pendaftaran = terima.getStringExtra("tgl_pendaftaran");
        String xkeluhan = terima.getStringExtra("keluhan");
        String xdiagnosa = terima.getStringExtra("diagnosa");
        String xperawatan = terima.getStringExtra("perawatan");
        String xtindakan = terima.getStringExtra("tindakan");
        String xbb = terima.getStringExtra("bb");
        String xtensi = terima.getStringExtra("tensi");

        Nama_dokter.setText(xnama_dokter);
        Nama_poli.setText(xnama_poli);
        tgl_pendaftaran.setText(xtgl_pendaftaran);
        keluhan.setText(xkeluhan);
        diagnosa.setText(xdiagnosa);
        perawatan.setText(xperawatan);
        tindakan.setText(xtindakan);
        bb.setText(xbb+"Kg");
        tensi.setText(xtensi+"mmHg");
    }

}