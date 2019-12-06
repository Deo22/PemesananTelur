package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.ui.maps.MapsActivity;
import com.syncode.pemesanantelur.ui.order.viewmodel.OrderViewModel;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class TransactionDetailActivity extends AppCompatActivity implements Observer<MessageOnly> {

    private final String[] status = {"Lacak Pemesanan", "Batalkan Pemesanan", "Orderan Selesai", "Menunggu"};

    @SuppressLint({"SetTextI18n", "DefaultLocale"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order_detail);
        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
            actionBar.setTitle("Detail Transaksi");
            actionBar.setElevation(0f);
        }
        ImageView imgProduct = findViewById(R.id.imgProduct);
        TextView txtProductName = findViewById(R.id.txtProductName);
        TextView txtAmmount = findViewById(R.id.txtAmount);
        TextView txtQuality = findViewById(R.id.txtQuality);
        TextView txtAddress = findViewById(R.id.txtAddress);
        TextView txtDate = findViewById(R.id.txtDate);
        TextView txtNumberPhone = findViewById(R.id.txtNumberPhone);
        TextView txtNumberTransaction = findViewById(R.id.txtOrderNumber);
        TextView txtAllPrice = findViewById(R.id.txtAllPrice);
        TextView txtStatus = findViewById(R.id.txtStatus);
        Button btnCancel = findViewById(R.id.btnCancel);
        SystemDataLocal systemDataLocal = new SystemDataLocal(this);
        Intent intent = getIntent();
        Order order = intent.getParcelableExtra("order");
        if (order != null) {
            txtProductName.setText(order.getProductName());
            txtAmmount.setText(String.valueOf(order.getCountOrder()));
            txtQuality.setText(String.valueOf(order.getQuality()));
            txtAddress.setText(systemDataLocal.getLoginData().getAddress());
            txtDate.setText(order.getDate());
            txtNumberPhone.setText(systemDataLocal.getLoginData().getPhone());
            txtNumberTransaction.setText(order.getIdOrder());
            txtAllPrice.setText("Rp." + String.format("%,d", order.getPriceAll()));
            Glide.with(this).load(ApiClient.BASE_URL_IMAGE + order.getImage()).into(imgProduct);
            switch (order.getStatus()) {
                case 0:
                    txtStatus.setText("Belum di proses");
                    btnCancel.setText(status[1]);
                    break;
                case 1:
                    txtStatus.setText("Sedang di proses");
                    btnCancel.setText(status[3]);
                    break;
                case 2:
                    txtStatus.setText("Sedang di Kirim");
                    btnCancel.setText(status[0]);
                    break;
                case 3:
                    txtStatus.setText("Orderan Selesai");
                    btnCancel.setText(status[2]);
                    break;

            }
            OrderViewModel orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
            btnCancel.setOnClickListener(view -> {
                if (btnCancel.getText().equals(status[0])) {
                    SwitchActivity.mainSwitch(this, MapsActivity.class, order, "order");
                } else {
                    orderViewModel.removeOrder(order.getIdOrder()).observe(this, this);
                }
            });
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onChanged(MessageOnly messageOnly) {
        if (messageOnly != null) {
            Toast.makeText(this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
            onBackPressed();
        }
    }
}
