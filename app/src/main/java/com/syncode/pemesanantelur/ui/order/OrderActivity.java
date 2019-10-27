package com.syncode.pemesanantelur.ui.order;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.model.product.ProductEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.home.HomeActivity;
import com.syncode.pemesanantelur.ui.order.viewmodel.OrderViewModel;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.Formula;
import com.syncode.pemesanantelur.utils.SwitchActivity;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;


public class OrderActivity extends AppCompatActivity implements Observer<MessageOnly> {


    private TextView txtAddress, txtName, txtPrice, txtPriceAll, txtCountPrice, txtCount, txtPriceSend;

    private Button btnBuy, btnPlus, btnMinus;
    private ImageView imgProduct;
    private AlertDialog alertDialog;
    private OrderViewModel orderViewModel;
    private Spinner spinnerQuality;

    private String [] listQuality = {"Bagus","Retak Ringan","Retak Sedang","Pecah Kulit"};

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
        txtPriceSend = findViewById(R.id.txtPriceSend);
        btnBuy = findViewById(R.id.btn_buy);
        btnPlus = findViewById(R.id.btnPlus);
        btnMinus = findViewById(R.id.btnMinus);
        imgProduct = findViewById(R.id.imgProduct);
        spinnerQuality = findViewById(R.id.spinnerQuality);
        Intent getProduct = getIntent();
        SystemDataLocal systemDataLocal = new SystemDataLocal(this);
        ProductEntity product = getProduct.getParcelableExtra("product");
        if (product != null) {
            txtAddress.setText(systemDataLocal.getLoginData().getAddress());
            txtName.setText(product.getProductName());
            txtPrice.setText("Rp." + String.format("%,d", product.getHarga()) + "/Peti");
            Glide.with(this).load(ApiClient.BASE_URL_IMAGE + product.getImage()).into(imgProduct);
            txtCount.setText("1");
            txtPriceAll.setText("Rp." + String.format("%,d", product.getHarga()));
            txtPriceSend.setText("Rp.0,0");
            txtCountPrice.setText("Rp." + String.format("%,d", product.getHarga()));
            orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
            String username = systemDataLocal.getLoginData().getUsername();
            String idAddress = systemDataLocal.getLoginData().getIdAddr();
            String idAgent = systemDataLocal.getLoginData().getIdAgent();
            String idProduct = product.getIdProduct();
            int countOrder = Integer.valueOf(txtCount.getText().toString());
            @SuppressLint("SimpleDateFormat") SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
            Date objDate = new Date();
            String dateOrd = formatter.format(objDate);
            btnBuy.setOnClickListener(view -> {
                Date date = java.util.Calendar.getInstance().getTime();
                String idOrder = "ORD-" + (date.getTime() + new Random().nextInt());
                @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
                alertDialog = DialogClass.dialog(this, v).create();
                alertDialog.show();
                orderViewModel.getOrderLiveData(idOrder, username, idAddress, idAgent, idProduct, countOrder, dateOrd).observe(this, this);
                double distance = Formula.haversineFormula(107.618633, 107.618279, -6.901361, -6.811771);
                System.out.println(String.format("%.2f", distance) + " KM");
            });
        }

        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setTitle("Atur Pesanan");
        }

        ArrayAdapter<String> arrayAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, listQuality);
        spinnerQuality.setAdapter(arrayAdapter);

    }

    @Override
    public void onChanged(MessageOnly messageOnly) {
        alertDialog.dismiss();
        Toast.makeText(this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
        SwitchActivity.mainSwitch(OrderActivity.this, HomeActivity.class);
        finish();
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

}
