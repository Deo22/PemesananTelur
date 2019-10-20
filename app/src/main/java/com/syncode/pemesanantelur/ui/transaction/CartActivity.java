package com.syncode.pemesanantelur.ui.transaction;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.ui.transaction.adapter.TabAdapter;
import com.syncode.pemesanantelur.ui.transaction.fragment.cart.CartFragment;
import com.syncode.pemesanantelur.ui.transaction.fragment.purchasing.PurchasingFragment;

import java.util.ArrayList;
import java.util.List;

public class CartActivity extends AppCompatActivity {


    private ViewPager viewPager;
    private TabLayout tabLayout;
    private TabAdapter tabAdapter;

    private List<Fragment> fragmentList = new ArrayList<>();
    private List<String> titleList = new ArrayList<>();

    final CartFragment cartFragment = new CartFragment();
    final PurchasingFragment purchasingFragment = new PurchasingFragment();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cart);
        tabLayout = findViewById(R.id.tabBar);
        viewPager = findViewById(R.id.viewPager);
        addFragment("Cart", cartFragment);
        addFragment("Purchasing", purchasingFragment);
        tabAdapter = new TabAdapter(getSupportFragmentManager(), 0, fragmentList, titleList);
        viewPager.setAdapter(tabAdapter);
        tabLayout.setupWithViewPager(viewPager);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setTitle("Cart Purchase");
        }
    }


    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }

    private void addFragment(String title, Fragment fragment) {
        fragmentList.add(fragment);
        titleList.add(title);
    }

}
