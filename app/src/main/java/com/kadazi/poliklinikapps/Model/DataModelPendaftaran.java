package com.kadazi.poliklinikapps.Model;

public class DataModelPendaftaran {
    private String id,dokter,nama_poli,tgl_pendaftaran,status;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDokter() {
        return dokter;
    }


    public void setDokter(String dokter) {
        this.dokter = dokter;
    }

    public String getNama_poli() {
        return nama_poli;
    }

    public void setNama_poli(String nama_poli) {
        this.nama_poli = nama_poli;
    }

    public String getTgl_pendaftaran() {
        return tgl_pendaftaran;
    }

    public void setTgl_pendaftaran(String tgl_pendaftaran) {
        this.tgl_pendaftaran = tgl_pendaftaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
