package com.syncode.pemesanantelur.data.local.sharepref;

import android.content.Context;
import android.content.SharedPreferences;

import com.syncode.pemesanantelur.data.model.login.Login;
import com.syncode.pemesanantelur.data.model.user.User;

public class SystemDataLocal {

    private SharedPreferences sharedPreferences;
    private Context context;
    private static final String KEY_USER = "User";
    private static final String KEY_ADDR = "address";
    private Login usersData;


    public SystemDataLocal(Context context, Login usersData) {
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
        editor.putString("idAgent", usersData.getIdAgent());
        editor.putString("username", usersData.getUsername());
        editor.putString("password", usersData.getPassword());
        editor.putString("lname", usersData.getFname());
        editor.putString("fname", usersData.getLname());
        editor.putString("address", usersData.getStreet());
        editor.putString("email", usersData.getEmail());
        editor.putString("shop", usersData.getNameShop());
        editor.putString("idAddres", usersData.getIdAddr());
        editor.putInt("isverified", usersData.getIsVerified());
        editor.putString("phone", usersData.getPhone());
        editor.putBoolean("login", true);
        editor.apply();
    }

    public void editAllSessionLogin(String username, String password, String lname, String fname, String address, String email, String shop, String idAddr, int isVerified, String coordinate, String phone) {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("coordinate", coordinate);
        editor.putString("username", username);
        editor.putString("password", password);
        editor.putString("lname", lname);
        editor.putString("fname", fname);
        editor.putString("address", address);
        editor.putString("email", email);
        editor.putString("shop", shop);
        editor.putString("idAddres", idAddr);
        editor.putString("phone", phone);
        editor.putBoolean("login", true);
        editor.apply();
    }

    public void editEmailIsVerified(int isVerified) {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt("isverified", isVerified);
        editor.apply();
    }

    public User getLoginData() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        String username = sharedPreferences.getString("username", "");
        String idAgent = sharedPreferences.getString("idAgent", "");
        String password = sharedPreferences.getString("password", "");
        String address = sharedPreferences.getString("address", "");
        String email = sharedPreferences.getString("email", "");
        String lName = sharedPreferences.getString("lname", "");
        String fName = sharedPreferences.getString("fname", "");
        String coordinate = sharedPreferences.getString("coordinate", "");
        String nameShop = sharedPreferences.getString("shop", "");
        int isVerified = sharedPreferences.getInt("isverified", 0);
        String idAddres = sharedPreferences.getString("idAddres", "");
        String phone = sharedPreferences.getString("phone", "");
        return new User(username, password, email, address, lName, fName, coordinate, nameShop, idAddres, isVerified, idAgent, phone);
    }

    public boolean getCheckLogin() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        return sharedPreferences.getBoolean("login", false);
    }


    public void destroySessionLogin() {
        sharedPreferences = context.getSharedPreferences(KEY_USER, Context.MODE_PRIVATE);
        sharedPreferences.edit().clear().apply();
    }

    public void setCoordinate(String address, String coordinate) {
        sharedPreferences = context.getSharedPreferences(KEY_ADDR, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putString("addr", address);
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
