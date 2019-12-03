package com.syncode.pemesanantelur.ui.home;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.lifecycle.Observer;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.repository.verificationemail.VerificationEmailRepository;
import com.syncode.pemesanantelur.ui.home.fragment.aboutfragment.AboutFragment;
import com.syncode.pemesanantelur.ui.home.fragment.accountfragment.AccountFragment;
import com.syncode.pemesanantelur.ui.home.fragment.homefragment.HomeFragment;
import com.syncode.pemesanantelur.ui.home.fragment.transactionfragment.TransactionFragment;
import com.syncode.pemesanantelur.ui.login.LoginActivity;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener, Observer<MessageOnly> {


    final Fragment homeFragment = new HomeFragment();
    final Fragment accountFragment = new AccountFragment();
    final Fragment transactionFragment = new TransactionFragment();
    final Fragment aboutFragment = new AboutFragment();

    final FragmentManager fm = getSupportFragmentManager();

    private SystemDataLocal systemDataLocal;

    private VerificationEmailRepository verificationEmailRepository;
    private String email, username;
    private AlertDialog alertDialog;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(this);
        fm.beginTransaction().add(R.id.containerFragment, homeFragment, homeFragment.getClass().getSimpleName()).commit();
        fm.beginTransaction().add(R.id.containerFragment, transactionFragment, transactionFragment.getClass().getSimpleName()).commit();
        fm.beginTransaction().add(R.id.containerFragment, accountFragment, accountFragment.getClass().getSimpleName()).commit();
        fm.beginTransaction().add(R.id.containerFragment, aboutFragment, aboutFragment.getClass().getSimpleName()).commit();
        fm.beginTransaction().show(homeFragment).commit();
        fm.beginTransaction().hide(transactionFragment).commit();
        fm.beginTransaction().hide(accountFragment).commit();
        fm.beginTransaction().hide(aboutFragment).commit();
        systemDataLocal = new SystemDataLocal(this);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setTitle(getResources().getString(R.string.produk));
        }
        verificationEmailRepository = new VerificationEmailRepository();
        if (systemDataLocal.getLoginData().getIsVerified() == 0) {
            alertDialogEmailVerify().show();
        }
        username = systemDataLocal.getLoginData().getUsername();
        email = systemDataLocal.getLoginData().getEmail();
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (!systemDataLocal.getCheckLogin()) {
            SwitchActivity.mainSwitch(this, LoginActivity.class);
            finish();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.top_nav, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        if (item.getItemId() == R.id.m_logout) {
            systemDataLocal.destroySessionLogin();
            SwitchActivity.mainSwitch(this, LoginActivity.class);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        String title = getResources().getString(R.string.produk);
        switch (item.getItemId()) {
            case R.id.m_home:
                fm.beginTransaction().show(homeFragment).commit();
                fm.beginTransaction().hide(transactionFragment).commit();
                fm.beginTransaction().hide(accountFragment).commit();
                fm.beginTransaction().hide(aboutFragment).commit();
                title = getResources().getString(R.string.produk);
                break;
            case R.id.m_transaksi:
                fm.beginTransaction().show(transactionFragment).commit();
                fm.beginTransaction().hide(homeFragment).commit();
                fm.beginTransaction().hide(accountFragment).commit();
                fm.beginTransaction().hide(aboutFragment).commit();
                title = getResources().getString(R.string.transaksi);
                break;
            case R.id.m_account:
                fm.beginTransaction().show(accountFragment).commit();
                fm.beginTransaction().hide(homeFragment).commit();
                fm.beginTransaction().hide(transactionFragment).commit();
                fm.beginTransaction().hide(aboutFragment).commit();
                title = getResources().getString(R.string.akun);
                break;
            case R.id.m_about:
                fm.beginTransaction().show(aboutFragment).commit();
                fm.beginTransaction().hide(homeFragment).commit();
                fm.beginTransaction().hide(transactionFragment).commit();
                fm.beginTransaction().hide(accountFragment).commit();
                title = getResources().getString(R.string.tentang);
                break;
            default:
                break;
        }
        if (getSupportActionBar() != null) {
            getSupportActionBar().setTitle(title);
        }
        return true;
    }


    private void loadingDialog() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
        alertDialog = DialogClass.dialog(this, v).create();
        alertDialog.show();
    }


    private AlertDialog alertDialogEmailVerify() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.verification_email_dialog, null, false);
        AlertDialog.Builder builderVerify = DialogClass.dialog(this, v);
        builderVerify.setNegativeButton("Nanti", (dialogInterface, i) -> dialogInterface.dismiss()).setPositiveButton("Verifikasi", (dialogInterface, i) -> {
            loadingDialog();
            verificationEmailRepository.getTokenVerification(systemDataLocal.getLoginData().getEmail()).observe(this, this);
        });
        return builderVerify.create();
    }


    private void inputTokenDialog() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.dialog_input_token, null, false);
        AlertDialog.Builder inputTokenBuilder = DialogClass.dialog(this, v);
        EditText edtToken = v.findViewById(R.id.edtToken);
        inputTokenBuilder.setNegativeButton("Keluar", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            alertDialog.dismiss();
        }).setPositiveButton("Verifikasi", (dialogInterface, i) -> {
            loadingDialog();
            String token = edtToken.getText().toString().trim();
            verificationEmailRepository.verificationEmail(email, username, token).observe(HomeActivity.this, HomeActivity.this);
            systemDataLocal.editEmailIsVerified(1);
        });
       alertDialog = inputTokenBuilder.create();
       alertDialog.show();
    }

    @Override
    public void onChanged(MessageOnly messageOnly) {
        if(messageOnly.isStatus()) {
            inputTokenDialog();
            alertDialog.dismiss();
        }
        Toast.makeText(HomeActivity.this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
    }
}
