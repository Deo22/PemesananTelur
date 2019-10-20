package com.syncode.pemesanantelur.ui.transaction.fragment.purchasing;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.viewmodel.order.OrderViewModel;


public class PurchasingFragment extends Fragment implements Observer<OrderEntity> {


    private OrderViewModel orderViewModel;

    private ProgressBar progressBar;
    private SwipeRefreshLayout swipeRefreshLayout;
    private RecyclerView recyclerView;
    private AdapterPurchasing adapterPurchasing;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        orderViewModel = ViewModelProviders.of(this).get(OrderViewModel.class);
        loadData();
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_purchasing, container, false);
    }

    @Override
    public void onChanged(OrderEntity orderEntity) {
        if (orderEntity.getRowCount() > 0) {
            adapterPurchasing = new AdapterPurchasing(orderEntity.getDataOrder());
            recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
            recyclerView.setAdapter(adapterPurchasing);
            progressBar.setVisibility(View.GONE);
            swipeRefreshLayout.setRefreshing(false);
        } else {
            System.out.println("data not found");
        }
    }

    private void loadData() {
        orderViewModel.setOrderAllData();
        orderViewModel.getOrderLiveData().observe(PurchasingFragment.this, PurchasingFragment.this);
    }
}
