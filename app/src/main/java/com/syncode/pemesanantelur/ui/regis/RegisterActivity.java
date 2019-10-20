package com.syncode.pemesanantelur.ui.regis;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import android.app.AlertDialog;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.register.ResponRegister;
import com.syncode.pemesanantelur.ui.regis.viewmodel.RegisterViewModel;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class RegisterActivity extends AppCompatActivity {


    private EditText edtUsername, edtPassword, edtEmail, edtLname, edtFname;
    private RegisterViewModel registerViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        edtUsername = findViewById(R.id.edt_username);
        edtPassword = findViewById(R.id.edt_password);
        edtEmail = findViewById(R.id.edt_email);
        Button btnRegister = findViewById(R.id.btn_sign_up);
        edtFname = findViewById(R.id.edtFirstName);
        edtLname = findViewById(R.id.edtLastName);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setTitle(getResources().getString(R.string.register));
        }
        registerViewModel = ViewModelProviders.of(this).get(RegisterViewModel.class);
        edtPassword.setOnKeyListener((view, i, keyEvent) -> {
            if ((keyEvent.getAction() == KeyEvent.ACTION_DOWN) &&
                    (i == KeyEvent.KEYCODE_ENTER)) {
                register();
                return true;
            }
            return false;
        });
        btnRegister.setOnClickListener(view -> {
            register();
        });
    }


    private void register() {
        View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
        AlertDialog alertDialog = DialogClass.dialog(this, v).create();
        alertDialog.show();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        String email = edtEmail.getText().toString().trim();
        String lname = edtLname.getText().toString().trim();
        String fname = edtFname.getText().toString().trim();
        registerViewModel.getRegisterRespon(username, email, password, fname, lname).observe(this, responRegister -> {
            if (responRegister.getUserData() != null) {
                alertDialog.dismiss();
                Toast.makeText(RegisterActivity.this, responRegister.getMessage(), Toast.LENGTH_LONG).show();
                SwitchActivity.mainSwitch(RegisterActivity.this,ReRegisterActivity.class,username,"username");
            } else {
                alertDialog.dismiss();
                Toast.makeText(RegisterActivity.this, responRegister.getMessage(), Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
