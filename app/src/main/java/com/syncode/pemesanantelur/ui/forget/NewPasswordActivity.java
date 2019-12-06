package com.syncode.pemesanantelur.ui.forget;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.ui.login.LoginActivity;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class NewPasswordActivity extends AppCompatActivity {


    private EditText edtPassword, edtPasswordVer;
    private Button btnSubmit;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_password);
        edtPassword = findViewById(R.id.edtNewPassword);
        edtPasswordVer = findViewById(R.id.edt_password_ver);
        btnSubmit = findViewById(R.id.btn_submit);
        Intent intent = getIntent();
        ForgetViewModel forgetViewModel = ViewModelProviders.of(this).get(ForgetViewModel.class);
        btnSubmit.setOnClickListener(view -> {
            String password = edtPassword.getText().toString().trim();
            String passVer = edtPasswordVer.getText().toString().trim();
            String email = intent.getStringExtra("email");
            if (password.equals(passVer)) {
                forgetViewModel.getResetPassword(email, password).observe(this, messageOnly -> {
                    if (messageOnly.isStatus()) {
                        SwitchActivity.mainSwitch(NewPasswordActivity.this, LoginActivity.class);
                        finish();
                    } else {
                        Toast.makeText(NewPasswordActivity.this, "Gagal Ubah Password", Toast.LENGTH_LONG).show();
                    }
                });
            } else {
                Toast.makeText(NewPasswordActivity.this, "Password Tidak Sesuai", Toast.LENGTH_LONG).show();
            }
        });
    }
}
