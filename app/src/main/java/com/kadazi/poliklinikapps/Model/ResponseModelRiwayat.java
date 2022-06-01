package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelRiwayat {
    private boolean success;
    private String message;
    private List<DataModelRiwayat> data;

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

    public List<DataModelRiwayat> getData() {
        return data;
    }

    public void setData(List<DataModelRiwayat> data) {
        this.data = data;
    }
}
