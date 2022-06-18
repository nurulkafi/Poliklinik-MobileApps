package com.kadazi.poliklinikapps.Model;

public class DataModelPembayaran {
    String id_pendaftran ,tanggal_pendaftaran,total_pembayaran,status;

    public String getId_pendaftran() {
        return id_pendaftran;
    }

    public void setId_pendaftran(String id_pendaftran) {
        this.id_pendaftran = id_pendaftran;
    }

    public String getTanggal_pendaftaran() {
        return tanggal_pendaftaran;
    }

    public void setTanggal_pendaftaran(String tanggal_pendaftaran) {
        this.tanggal_pendaftaran = tanggal_pendaftaran;
    }

    public String getTotal_pembayaran() {
        return total_pembayaran;
    }

    public void setTotal_pembayaran(String total_pembayaran) {
        this.total_pembayaran = total_pembayaran;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
