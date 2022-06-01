package com.kadazi.poliklinikapps.Model;

public class DataModelResep {
    private String id,nama,poli,tgl_pendaftaran;

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }

    public String getTgl_pendaftaran() {
        return tgl_pendaftaran;
    }

    public void setTgl_pendaftaran(String tgl_pendaftaran) {
        this.tgl_pendaftaran = tgl_pendaftaran;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }
}
