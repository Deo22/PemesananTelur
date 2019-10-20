package com.syncode.pemesanantelur.data.local.sharepref;

import android.content.Context;
import android.content.SharedPreferences;

import com.syncode.pemesanantelur.data.model.login.ResponseLogin;
import com.syncode.pemesanantelur.data.model.user.User;

public class SystemDataLocal {

    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String KEY_USER = "User";
    private static final String KEY_ADDR = "address";
    private ResponseLogin usersData;


    public SystemDataLocal(Context context, ResponseLogin usersData) {
        this.context = context;
        this.usersData = usersData;
    }

    public SystemDataLocal(Context context) {
        this.context = context;
    }

    public void setSessionLogin() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("coordinate", usersData.getCoordinate());
        editor.putString("username", usersData.getUsername());
        editor.putString("password", usersData.getPassword());
        editor.putString("lname", usersData.getFname());
        editor.putString("fname", usersData.getLname());
        editor.putString("address", usersData.getStreet());
        editor.putString("email", usersData.getEmail());
        editor.putString("shop", usersData.getNameShop());
        editor.putBoolean("login", true);
        editor.apply();
    }

    public User getLoginData() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String password = sharedPreferences.getString("password", "");
        String address = sharedPreferences.getString("address", "");
        String email = sharedPreferences.getString("email", "");
        String lName = sharedPreferences.getString("lname", "");
        String fName = sharedPreferences.getString("fname", "");
        String coordinate = sharedPreferences.getString("coordinate", "");
        String nameShop = sharedPreferences.getString("shop", "");
        return new User(username, password, email, address, lName, fName, coordinate, nameShop);
    }

    public boolean getCheckLogin() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);
    }


    public void destroySessionLogin() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public void setCoordinate(String alamat, String coordinate) {
        sharedPreferences = context.getSharedPreferences(KEY_ADDR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("addr", alamat);
        editor.putString("coordinate", coordinate);
        editor.apply();
    }

    public SharedPreferences getCoordinate() {
        sharedPreferences = context.getSharedPreferences(KEY_ADDR, Context.MODE_PRIVATE);
        return sharedPreferences;
    }

    public void destroyCoordinate() {
        sharedPreferences = context.getSharedPreferences(KEY_ADDR, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }
}
