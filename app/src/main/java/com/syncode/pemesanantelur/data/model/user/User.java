package com.syncode.pemesanantelur.data.model.user;

public class User {

    private String username;
    private String password;
    private String email;
    private String address;
    private String lName;
    private String fName;
    private String coordinate;
    private String nameShop;
    private String idAddr;
    private int isVerified;
    private String idAgent;
    private String phone;

    public User(String username, String password, String email, String address, String lName, String fName, String coordinate, String nameShop, String idAddr, int isVerified, String idAgent, String phone) {
        this.username = username;
        this.password = password;
        this.idAgent = idAgent;
        this.email = email;
        this.address = address;
        this.lName = lName;
        this.isVerified = isVerified;
        this.idAddr = idAddr;
        this.fName = fName;
        this.coordinate = coordinate;
        this.nameShop = nameShop;
        this.phone = phone;
    }

    public String getIdAddr() {
        return idAddr;
    }

    public int getIsVerified() {
        return isVerified;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getEmail() {
        return email;
    }

    public String getAddress() {
        return address;
    }

    public String getlName() {
        return lName;
    }

    public String getfName() {
        return fName;
    }

    public String getIdAgent() {
        return idAgent;
    }

    public String getPhone() {
        return phone;
    }

    public String getCoordinate() {
        return coordinate;
    }

    public String getNameShop() {
        return nameShop;
    }
}
