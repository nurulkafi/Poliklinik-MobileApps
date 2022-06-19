package com.kadazi.poliklinikapps.Model;

public class ResponseModel {
    private boolean success;
    private String message;
    private String id;
    private String pasiend_id;

    public boolean isSuccess() {
        return success;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public String getPasiend_id() {
        return pasiend_id;
    }

    public void setPasiend_id(String pasiend_id) {
        this.pasiend_id = pasiend_id;
    }
}
