package com.syncode.pemesanantelur.ui.order;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.product.ProductEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.order.viewmodel.OrderViewModel;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.Formula;
import com.wang.avi.AVLoadingIndicatorView;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Random;


public class OrderActivity extends AppCompatActivity implements Observer<MessageOnly>, View.OnClickListener {


    private TextView txtAddress, txtName, txtPrice, txtPriceAll, txtCountPrice, txtCount;

    private Button btnBuy, btnPlus, btnMinus;
    private ImageView imgProduct;
    private AlertDialog alertDialog;
    private OrderViewModel orderViewModel;

    private int countBuy = 1;
    private String idProduct;

    private AVLoadingIndicatorView progressLoadTotal;
    private AVLoadingIndicatorView progressLoadTotalAll;

    @SuppressLint({"DefaultLocale", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        txtAddress = findViewById(R.id.txtAddress);
        txtName = findViewById(R.id.txtName);
        txtPrice = findViewById(R.id.txtPrice);
        txtPriceAll = findViewById(R.id.txtAllPrice);
        txtCountPrice = findViewById(R.id.txtCountPrice);
        txtCount = findViewById(R.id.txtCount);
        btnBuy = findViewById(R.id.btn_buy);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        imgProduct = findViewById(R.id.imgProduct);
        progressLoadTotal = findViewById(R.id.progressLoadTotal);
        progressLoadTotalAll = findViewById(R.id.loadingAllPrice);
        Intent getProduct = getIntent();
        SystemDataLocal systemDataLocal = new SystemDataLocal(this);

        ProductEntity product = getProduct.getParcelableExtra("product");
        if (product != null) {
            txtAddress.setText(systemDataLocal.getLoginData().getAddress());
            txtName.setText(product.getProductName());
            txtPrice.setText("Rp." + String.format("%,d", product.getHarga()) + "/Peti");
            Glide.with(this).load(ApiClient.BASE_URL_IMAGE + product.getImage()).into(imgProduct);
            txtCount.setText(String.valueOf(countBuy));
            txtPriceAll.setText("Rp." + String.format("%,d", product.getHarga()));
            txtCountPrice.setText("Rp." + String.format("%,d", product.getHarga()));
            orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
            btnMinus.setOnClickListener(this);
            btnPlus.setOnClickListener(this);
            String username = systemDataLocal.getLoginData().getUsername();
            String idAddress = systemDataLocal.getLoginData().getIdAddr();
            String idAgent = systemDataLocal.getLoginData().getIdAgent();
            idProduct = product.getIdProduct();
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date objDate = new Date();
            String dateOrd = formatter.format(objDate);
            btnBuy.setOnClickListener(view -> {
                Calendar calendar = Calendar.getInstance();
                int m = calendar.get(Calendar.MINUTE);
                int s = calendar.get(Calendar.SECOND);
                String idOrder = "ORD-" + ((new Random().nextInt(9) + m) + "" + s);
                @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
                alertDialog = DialogClass.dialog(this, v).create();
                alertDialog.show();
                orderViewModel.getOrderLiveData(idOrder, username, idAddress, idAgent, idProduct, countBuy, dateOrd).observe(this, this);
            });
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setTitle("Atur Pesanan");
        }
    }

    @Override
    public void onChanged(MessageOnly messageOnly) {
        alertDialog.dismiss();
        Toast.makeText(this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
        onBackPressed();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btnMinus) {
            if (countBuy != 0) {
                countBuy--;
            }
        } else if (view.getId() == R.id.btnPlus) {
            countBuy++;
        }
        txtPriceAll.setVisibility(View.GONE);
        txtCountPrice.setVisibility(View.GONE);
        progressLoadTotal.setVisibility(View.VISIBLE);
        progressLoadTotalAll.setVisibility(View.VISIBLE);
        orderViewModel.getTotalWithCountBuy(idProduct, countBuy).observe(this, responseChangeCount -> {
            if (responseChangeCount != null) {
                txtPriceAll.setVisibility(View.VISIBLE);
                txtPriceAll.setText("Rp.".concat(responseChangeCount.getTotal()));
                progressLoadTotal.setVisibility(View.GONE);
                progressLoadTotalAll.setVisibility(View.GONE);
                txtCountPrice.setVisibility(View.VISIBLE);
                txtCountPrice.setText("Rp.".concat(responseChangeCount.getTotal()));

            }
        });
        txtCount.setText(String.valueOf(countBuy));
    }
}
