package com.syncode.pemesanantelur.data.model;

import com.google.gson.annotations.SerializedName;

public class MessageOnly {

    @SerializedName("message")
    private String message;

    @SerializedName("status")
    private boolean status;

    public MessageOnly(String message,boolean status) {
        this.message = message;
        this.status = status;
    }

    public boolean isStatus() {
        return status;
    }

    public String getMessage() {
        return message;
    }
}
