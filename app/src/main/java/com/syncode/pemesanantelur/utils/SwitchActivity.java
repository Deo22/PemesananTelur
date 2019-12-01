package com.syncode.pemesanantelur.utils;

import android.content.Context;
import android.content.Intent;
import android.os.Parcelable;

public class SwitchActivity {


    public static void mainSwitch(Context from, Class to) {
        Intent intent = new Intent(from, to);
        from.startActivity(intent);
    }

    public static void mainSwitch(Context from, Class to, String value, String key) {
        Intent intent = new Intent(from, to);
        intent.putExtra(key, value);
        from.startActivity(intent);
    }

    public static void mainSwitch(Context from, Class to, boolean value, String key) {
        Intent intent = new Intent(from, to);
        intent.putExtra(key, value);
        from.startActivity(intent);
    }

    public static void mainSwitch(Context from, Class to, Parcelable parcel, String key) {
        Intent intent = new Intent(from, to);
        intent.putExtra(key, parcel);
        from.startActivity(intent);
    }

}
