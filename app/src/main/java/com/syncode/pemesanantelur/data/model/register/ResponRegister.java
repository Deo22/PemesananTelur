package com.syncode.pemesanantelur.data.model.register;

import com.google.gson.annotations.SerializedName;

public class ResponRegister {


    @SerializedName("message")
    private
    String message;
    @SerializedName("user_data")
    private
    String userData;


    public ResponRegister(String message, String userData) {
        this.message = message;
        this.userData = userData;
    }

    public String getUserData() {
        return userData;
    }

    public String getMessage() {
        return message;
    }
}
