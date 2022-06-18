package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelPoli {
    private boolean success;
    private String message;
    private List<DataModelPoli> data;

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

    public List<DataModelPoli> getData() {
        return data;
    }

    public void setData(List<DataModelPoli> data) {
        this.data = data;
    }
}
