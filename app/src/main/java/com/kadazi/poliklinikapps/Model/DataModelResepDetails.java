package com.kadazi.poliklinikapps.Model;

public class DataModelResepDetails {
    String id;
    String nama;
    String poli;
    String tgl_pendaftaran;
    String obat;
    String dosis;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getNama() {
        return nama;
    }

    public void setNama(String nama) {
        this.nama = nama;
    }

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

    public String getObat() {
        return obat;
    }

    public void setObat(String obat) {
        this.obat = obat;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }

}
