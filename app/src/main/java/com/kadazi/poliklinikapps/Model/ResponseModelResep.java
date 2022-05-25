package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelResep {
    private boolean success;
    private String message;
    private List<DataModelResep> data;

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

    public List<DataModelResep> getData() {
        return data;
    }

    public void setData(List<DataModelResep> data) {
        this.data = data;
    }
}
