package com.syncode.pemesanantelur.ui.home.fragment.accountfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.material.tabs.TabLayout;
import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment.PasswordFragment;
import com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment.ProfileFragment;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {


    private List<Fragment> listFragment = new ArrayList<>();
    private List<String> listTitle = new ArrayList<>();
    private TabLayout tabLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ViewPager viewPager = view.findViewById(R.id.viewPager);
        tabLayout = view.findViewById(R.id.tabLayout);
        if (getFragmentManager() != null) {
            setListFragment();
            TabAdapter tabAdapter = new TabAdapter(getFragmentManager(), 0, listFragment, listTitle);
            viewPager.setAdapter(tabAdapter);
            tabLayout.setupWithViewPager(viewPager);
        }

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_account, container, false);
    }

    private void setListFragment() {
        listFragment.add(new ProfileFragment());
        listFragment.add(new PasswordFragment());
        listTitle.add("Profile");
        listTitle.add("Password");

    }
}
