package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelPembayaranBiaya {
    private boolean success;
    private String message;
    private List<DataModelPembayaranBiaya> data;

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

    public List<DataModelPembayaranBiaya> getData() {
        return data;
    }

    public void setData(List<DataModelPembayaranBiaya> data) {
        this.data = data;
    }
}
