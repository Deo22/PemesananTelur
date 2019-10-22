package com.syncode.pemesanantelur.ui.forget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class InputOTPActivity extends AppCompatActivity {


    private EditText edtToken;
    private Button btnVerify;
    private ForgetViewModel forgetViewModel;
    private AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_input_otp);
        forgetViewModel = ViewModelProviders.of(this).get(ForgetViewModel.class);
        edtToken = findViewById(R.id.edtToken);
        btnVerify = findViewById(R.id.btnVerify);
        Intent intent = getIntent();
        btnVerify.setOnClickListener(view -> {
            String email = intent.getStringExtra("email");
            String token = edtToken.getText().toString().trim();
            forgetViewModel.messageOnlyLiveData(email, token).observe(this, messageOnly -> {
                if (messageOnly.isStatus()) {
                    @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
                    alertDialog = DialogClass.dialog(InputOTPActivity.this, v).create();
                    alertDialog.show();
                    SwitchActivity.mainSwitch(InputOTPActivity.this, NewPasswordActivity.class, email, "email");
                } else {
                    Toast.makeText(InputOTPActivity.this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                    alertDialog.dismiss();
                }
            });

        });
    }
}
