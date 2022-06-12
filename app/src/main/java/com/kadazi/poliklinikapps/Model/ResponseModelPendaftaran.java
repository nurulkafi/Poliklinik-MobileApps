package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelPendaftaran {
    private boolean success;
    private String message;
    private List<DataModelPendaftaran> data;

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

    public List<DataModelPendaftaran> getData() {
        return data;
    }

    public void setData(List<DataModelPendaftaran> data) {
        this.data = data;
    }

}
