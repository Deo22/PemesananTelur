package com.syncode.pemesanantelur.data.model.user;

public class User {

    private String username;
    private String password;
    private String email;
    private String address;
    private String name;
    private String coordinate;

    public User(String username, String password, String email, String address, String name, String coordinate) {
        this.username = username;
        this.password = password;
        this.email = email;
        this.address = address;
        this.name = name;
        this.coordinate = coordinate;
    }

    public String getCoordinate() {
        return coordinate;
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


    public String getName() {
        return name;
    }

}
