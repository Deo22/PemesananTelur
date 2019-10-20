package com.syncode.pemesanantelur.ui.home;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.user.User;
import com.syncode.pemesanantelur.ui.home.fragment.aboutfragment.AboutFragment;
import com.syncode.pemesanantelur.ui.home.fragment.accountfragment.AccountFragment;
import com.syncode.pemesanantelur.ui.home.fragment.homefragment.HomeFragment;
import com.syncode.pemesanantelur.ui.home.fragment.transactionfragment.TransactionFragment;
import com.syncode.pemesanantelur.ui.login.LoginActivity;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class HomeActivity extends AppCompatActivity implements BottomNavigationView.OnNavigationItemSelectedListener {


    final Fragment homeFragment = new HomeFragment();
    final Fragment accountFragment = new AccountFragment();
    final Fragment transactionFragment = new TransactionFragment();
    final Fragment aboutFragment = new AboutFragment();

    final FragmentManager fm = getSupportFragmentManager();

    private SystemDataLocal systemDataLocal;

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
        User user = systemDataLocal.getLoginData();
        System.out.println(user.getCoordinate());
        System.out.println(user.getAddress());
        System.out.println(user.getPassword());
        System.out.println(user.getEmail());
        System.out.println(user.getUsername());

        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setTitle(getResources().getString(R.string.produk));
        }
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
}
