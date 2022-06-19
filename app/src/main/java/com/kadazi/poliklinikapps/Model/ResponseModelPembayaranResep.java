package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelPembayaranResep {
    private boolean success;
    private String message;
    private List<DataModelPembayaranResep> data;

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

    public List<DataModelPembayaranResep> getData() {
        return data;
    }

    public void setData(List<DataModelPembayaranResep> data) {
        this.data = data;
    }
}
