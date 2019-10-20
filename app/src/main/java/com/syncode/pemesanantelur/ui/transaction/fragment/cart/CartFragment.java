package com.syncode.pemesanantelur.ui.transaction.fragment.cart;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.local.sqlite.CartDatabase;
import com.syncode.pemesanantelur.data.model.cart.Cart;
import com.syncode.pemesanantelur.data.viewmodel.cart.CartViewModel;

import java.util.List;


public class CartFragment extends Fragment implements Observer<List<Cart>> {


    public CartFragment() {

    }

    private RecyclerView recyclerView;
    private CartViewModel cartViewModel;
    private TextView txtNote;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        txtNote = view.findViewById(R.id.txtNote);
        CartDatabase cartDatabase = Room.databaseBuilder(view.getContext(), CartDatabase.class, Cart.DB_NAME).build();
        cartViewModel = ViewModelProviders.of(this).get(CartViewModel.class);
        cartViewModel = new CartViewModel(cartDatabase);
        SystemDataLocal systemDataLocal = new SystemDataLocal(this.getContext());
        cartViewModel.readAllCart(systemDataLocal.getLoginData().getUsername()).observe(this, this);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_cart, container, false);
    }

    @Override
    public void onChanged(List<Cart> carts) {
        if (carts.size() > 0) {
            AdapterCart adapterCart = new AdapterCart(carts, cartViewModel);
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(adapterCart);
        } else {
            txtNote.setVisibility(View.VISIBLE);
            recyclerView.setVisibility(View.GONE);
        }
    }
}
