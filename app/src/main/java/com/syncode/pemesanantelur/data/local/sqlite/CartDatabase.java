package com.syncode.pemesanantelur.data.local.sqlite;


import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.syncode.pemesanantelur.data.model.cart.Cart;

@Database(entities = {Cart.class}, version = 1, exportSchema = false)
public abstract class CartDatabase extends RoomDatabase {
    public abstract CartDao movieDAO();
}

