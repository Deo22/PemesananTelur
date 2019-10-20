package com.syncode.pemesanantelur.data.local.sqlite;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.syncode.pemesanantelur.data.model.cart.Cart;

import java.util.List;

@Dao
public interface CartDao {


    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertCart(Cart cart);

    @Query("Select * from tb_cart where id_user LIKE :id_user")
    LiveData<List<Cart>> readCart(String id_user);

    @Query("SELECT COUNT(*) FROM tb_cart where id_user LIKE :id_user")
    LiveData<Integer> readCount(String id_user);

    @Delete
    void deleteCart(Cart cart);

    @Query("Delete from tb_cart")
    void deleteAll();

}
