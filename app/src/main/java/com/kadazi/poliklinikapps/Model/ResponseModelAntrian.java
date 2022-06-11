package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelAntrian {
    private boolean success;
    private String message;
    private List<DataModelAntrian> data;

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

    public List<DataModelAntrian> getData() {
        return data;
    }

    public void setData(List<DataModelAntrian> data) {
        this.data = data;
    }
}
