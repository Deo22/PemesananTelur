package com.syncode.pemesanantelur.ui.regis;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.register.ResponRegister;
import com.syncode.pemesanantelur.ui.login.LoginActivity;
import com.syncode.pemesanantelur.ui.maps.MapsActivity;
import com.syncode.pemesanantelur.ui.regis.viewmodel.RegisterViewModel;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

import java.util.Calendar;
import java.util.Random;

public class ReRegisterActivity extends AppCompatActivity {


    private ImageView pickAddres;
    private EditText edtNameShop;
    private Button btnSimpan;


    private AlertDialog alertDialog;
    private String coordinate, street, username;
    private RegisterViewModel registerViewModel;
    private SystemDataLocal systemDataLocal;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_re_register);
        pickAddres = findViewById(R.id.imgMaps);
        edtNameShop = findViewById(R.id.edtAgentName);
        btnSimpan = findViewById(R.id.btnSimpan);
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        pickAddres.setOnClickListener(view -> {
            SwitchActivity.mainSwitch(ReRegisterActivity.this, MapsActivity.class);
        });
        Intent intent = getIntent();
        username = intent.getStringExtra("username");
        systemDataLocal = new SystemDataLocal(this);
        btnSimpan.setOnClickListener(view -> {
            street = systemDataLocal.getCoordinate().getString("addr", "");
            coordinate = systemDataLocal.getCoordinate().getString("coordinate", "");
            Calendar calendar = Calendar.getInstance();
            int h = calendar.get(Calendar.HOUR);
            int m = calendar.get(Calendar.MINUTE);
            int s = calendar.get(Calendar.SECOND);
            String idAgent = "AGN-" + (h + m + s + new Random().nextInt(10));
            String nameAgent = edtNameShop.getText().toString().trim();
            @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
            alertDialog = DialogClass.dialog(ReRegisterActivity.this, v).create();
            alertDialog.show();
            registerViewModel.getReRegisterRespon(username, street, idAgent, nameAgent, coordinate).observe(ReRegisterActivity.this, new Observer<ResponRegister>() {
                @Override
                public void onChanged(ResponRegister responRegister) {
                    if (responRegister.getMessage() != null) {
                        Toast.makeText(ReRegisterActivity.this, responRegister.getMessage(), Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                        systemDataLocal.destroyCoordinate();
                        SwitchActivity.mainSwitch(ReRegisterActivity.this, LoginActivity.class);
                        finish();
                    }
                }
            });
        });

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.lengkapi_data));
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        systemDataLocal.destroyCoordinate();
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    @Override
    public void onBackPressed() {
        systemDataLocal.destroyCoordinate();
        super.onBackPressed();
    }
}
