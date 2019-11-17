package com.syncode.pemesanantelur.data.model.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {


    @SerializedName("message")
    private String message;

    @SerializedName("userdata")
    private Login userData;


    public ResponseLogin(String message, Login userData) {
        this.message = message;
        this.userData = userData;
    }


    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Login getuserData() {
        return userData;
    }

    public void setuserData(Login userData) {
        this.userData = userData;
    }
}
