package com.syncode.pemesanantelur.ui.home.fragment.homefragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.product.Product;


public class HomeFragment extends Fragment implements Observer<Product> {


    private RecyclerView recyclerView;
    private ProgressBar progressBar;
    private SwipeRefreshLayout refreshLayout;
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        loadData();
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        progressBar.setVisibility(View.VISIBLE);
        refreshLayout = view.findViewById(R.id.swipeRefresh);
        refreshLayout.setOnRefreshListener(this::loadData);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }

    @Override
    public void onChanged(Product product) {
        if (product != null) {
            if (product.getListProduct().size() > 0) {
                HomeAdapter homeAdapter = new HomeAdapter(product.getListProduct(), getContext());
                recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
                recyclerView.setAdapter(homeAdapter);
                recyclerView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                refreshLayout.setRefreshing(false);
            }
        }
    }


    private void loadData(){
        ProductViewModel productViewModel = ViewModelProviders.of(this).get(ProductViewModel.class);
        productViewModel.getProductLiveData().observe(this, this);
    }
}
