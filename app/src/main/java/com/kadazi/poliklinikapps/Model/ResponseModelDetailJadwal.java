package com.kadazi.poliklinikapps.Model;

import java.util.List;

public class ResponseModelDetailJadwal {
    private boolean success;
    private String message;
    private String poli;
    private List<DataModelJadwal> data;

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

    public List<DataModelJadwal> getData() {
        return data;
    }

    public void setData(List<DataModelJadwal> data) {
        this.data = data;
    }

    public String getPoli() {
        return poli;
    }

    public void setPoli(String poli) {
        this.poli = poli;
    }
}
