package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelResepDetails {
    private boolean success;
    private String message;
    private List<DataModelResepDetails> data;

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

    public List<DataModelResepDetails> getData() {
        return data;
    }

    public void setData(List<DataModelResepDetails> data) {
        this.data = data;
    }
}

