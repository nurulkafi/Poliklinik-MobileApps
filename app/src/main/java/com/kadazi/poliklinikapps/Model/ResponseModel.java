package com.kadazi.poliklinikapps.Model;

public class ResponseModel {
    private boolean success;
    private String message;
    private String name;
    private String id;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String pasien_id;

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

    public String getPasien_id() {
        return pasien_id;
    }

    public void setPasien_id(String pasien_id) {
        this.pasien_id = pasien_id;
    }
}
