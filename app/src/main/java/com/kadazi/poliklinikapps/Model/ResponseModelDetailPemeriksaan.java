package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelDetailPemeriksaan {
    private boolean success;
    private String message;

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

    public List<DataModelDetailPemeriksaan> getData() {
        return data;
    }

    public void setData(List<DataModelDetailPemeriksaan> data) {
        this.data = data;
    }

    private List<DataModelDetailPemeriksaan> data;
}
