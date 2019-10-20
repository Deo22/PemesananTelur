package com.syncode.pemesanantelur.data.model.login;

import com.google.gson.annotations.SerializedName;

public class ResponseLogin {
    @SerializedName("username")
    private String username;
    @SerializedName("fname")
    private String fname;
    @SerializedName("lname")
    private String lname;
    @SerializedName("email")
    private String email;
    @SerializedName("password")
    private String password;
    @SerializedName("type")
    private String type;
    @SerializedName("id_address")
    private String idAddr;
    @SerializedName("street")
    private String street;
    @SerializedName("kordinat")
    private String coordinate;

    public String getUsername() {
        return username;
    }

    public String getFname() {
        return fname;
    }

    public String getLname() {
        return lname;
    }

    public String getEmail() {
        return email;
    }

    public String getPassword() {
        return password;
    }

    public String getType() {
        return type;
    }

    public String getIdAddr() {
        return idAddr;
    }

    public String getStreet() {
        return street;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public ResponseLogin(String username, String fname, String lname, String email, String password, String type, String idAddr, String street, String coordinate) {
        this.username = username;
        this.fname = fname;
        this.lname = lname;
        this.email = email;
        this.password = password;
        this.type = type;
        this.idAddr = idAddr;
        this.street = street;
        this.coordinate = coordinate;
    }
}
