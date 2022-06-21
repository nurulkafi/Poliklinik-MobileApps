package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelPemeriksaanResep {
    private boolean success;
    private String message;
    private List<DataModelPemeriksaanResep> data;

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<DataModelPemeriksaanResep> getData() {
        return data;
    }

    public void setData(List<DataModelPemeriksaanResep> data) {
        this.data = data;
    }
}
