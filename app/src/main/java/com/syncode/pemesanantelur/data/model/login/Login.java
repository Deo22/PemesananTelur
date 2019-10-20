package com.syncode.pemesanantelur.data.model.login;

import com.google.gson.annotations.SerializedName;

public class Login {


    @SerializedName("message")
    private String message;

    @SerializedName("userdata")
    private ResponseLogin userData;


    public Login(String message, ResponseLogin userData) {
        this.message = message;
        this.userData = userData;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public ResponseLogin getuserData() {
        return userData;
    }

    public void setuserData(ResponseLogin userData) {
        this.userData = userData;
    }
}
