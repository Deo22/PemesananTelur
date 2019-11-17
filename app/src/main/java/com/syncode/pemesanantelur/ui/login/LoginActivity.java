package com.syncode.pemesanantelur.ui.login;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.login.ResponseLogin;
import com.syncode.pemesanantelur.ui.login.viewmodel.LoginViewModel;
import com.syncode.pemesanantelur.ui.forget.ForgetActivity;
import com.syncode.pemesanantelur.ui.home.HomeActivity;
import com.syncode.pemesanantelur.ui.regis.RegisterActivity;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener, Observer<ResponseLogin> {


    private EditText edtPassword, edtUsername;
    private LoginViewModel homeViewModel;
    private SystemDataLocal systemDataLocal;
    private android.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        setContentView(R.layout.activity_login);
        edtPassword = findViewById(R.id.edt_password);
        edtUsername = findViewById(R.id.edt_username);
        Button btnSignUp = findViewById(R.id.btn_sign_up);
        TextView txtForget = findViewById(R.id.txtForget);
        homeViewModel = ViewModelProviders.of(this).get(LoginViewModel.class);
        Button btnLogin = findViewById(R.id.btn_login);
        btnLogin.setOnClickListener(this);
        btnSignUp.setOnClickListener(this);
        edtPassword.setOnEditorActionListener((textView, i, keyEvent) -> {
            if (i == EditorInfo.IME_ACTION_DONE) {
                InputMethodManager imm = (InputMethodManager) this.getSystemService(Context.INPUT_METHOD_SERVICE);
                if (imm != null) {
                    imm.hideSoftInputFromWindow(edtPassword.getWindowToken(), 0);
                }
                login();
                return true;
            }
            return false;
        });

        txtForget.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_login) {
            login();
        } else if (view.getId() == R.id.btn_sign_up) {
            SwitchActivity.mainSwitch(this, RegisterActivity.class);
        } else if (view.getId() == R.id.txtForget) {
            SwitchActivity.mainSwitch(this, ForgetActivity.class);
        }
    }

    @Override
    public void onChanged(ResponseLogin login) {
        if (login.getuserData() != null) {
            alertDialog.dismiss();
            System.out.println(login.getuserData().getIsVerified());
            systemDataLocal = new SystemDataLocal(this, login.getuserData());
            systemDataLocal.setSessionLogin();
            alertDialog.dismiss();
            SwitchActivity.mainSwitch(this, HomeActivity.class);
            finish();
        } else {
            alertDialog.dismiss();
            Toast.makeText(this, login.getMessage(), Toast.LENGTH_LONG).show();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        systemDataLocal = new SystemDataLocal(this);
        if (systemDataLocal.getCheckLogin()) {
            SwitchActivity.mainSwitch(this, HomeActivity.class);
            finish();
        }
    }

    private void login() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
        alertDialog = DialogClass.dialog(this, v).create();
        alertDialog.show();
        String username = edtUsername.getText().toString().trim();
        String password = edtPassword.getText().toString().trim();
        homeViewModel.setLogin(username, password);
        homeViewModel.getUserEntityLiveData().observe(this, this);

    }
}
