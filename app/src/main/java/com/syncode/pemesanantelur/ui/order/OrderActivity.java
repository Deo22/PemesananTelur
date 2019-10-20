package com.syncode.pemesanantelur.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.cart.Cart;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.viewmodel.order.OrderViewModel;

import java.sql.Date;
import java.text.SimpleDateFormat;


public class OrderActivity extends AppCompatActivity implements Observer<OrderEntity> {


    private OrderViewModel orderViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        Intent product = getIntent();
        Cart cart = product.getParcelableExtra("product");
        SystemDataLocal systemDataLocal = new SystemDataLocal(this);
        @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
        Date date = new Date(System.currentTimeMillis());
        String dateStr = formatter.format(date);
        String orderId = "ORD" + date.getTime();
        if (cart != null) {
            orderViewModel.setOrder(orderId,
                    cart.getProductName(),
                    systemDataLocal.getLoginData().getName(),
                    " ",
                    " woy coeg",
                    "jdksfjklds",
                    systemDataLocal.getLoginData().getAddress(),
                    cart.getPrice(),
                    cart.getAmmount(),
                    0,
                    dateStr);
            orderViewModel.getOrderLiveData().observe(this, this);
        }
    }

    @Override
    public void onChanged(OrderEntity orderEntity) {
        if (orderEntity != null) {
            Toast.makeText(this,orderEntity.getMessage(),Toast.LENGTH_LONG).show();
        }
    }
}
