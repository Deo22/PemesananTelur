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

    public User(String username, String password, String email, String address, String lName, String fName, String coordinate, String nameShop) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.lName = lName;
        this.fName = fName;
        this.coordinate = coordinate;
        this.nameShop = nameShop;
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

    public String getCoordinate() {
        return coordinate;
    }

    public String getNameShop() {
        return nameShop;
    }
}
