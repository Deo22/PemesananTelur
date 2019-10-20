package com.syncode.pemesanantelur.data.viewmodel.cart;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.local.sqlite.CartDatabase;
import com.syncode.pemesanantelur.data.model.cart.Cart;

import java.util.List;

public class CartViewModel extends ViewModel {

    private CartDatabase cartDatabase;

    public CartViewModel(CartDatabase cartDatabase) {
        this.cartDatabase = cartDatabase;
    }

    public CartViewModel() {
    }

    public LiveData<List<Cart>> readAllCart(String idUser) {
        return cartDatabase.movieDAO().readCart(idUser);
    }

    public void deleteCart(Cart cart) {
        cartDatabase.movieDAO().deleteCart(cart);

    }

    public void insertCart(Cart cart) {
        cartDatabase.movieDAO().insertCart(cart);
    }

    public LiveData<Integer> getCount(String idUser) {
        return cartDatabase.movieDAO().readCount(idUser);
    }

    public void deleteAll() {
        cartDatabase.movieDAO().deleteAll();
    }
}
