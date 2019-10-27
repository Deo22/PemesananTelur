package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.model.user.User;

import java.util.ArrayList;
import java.util.List;


public class TransactionFragment extends Fragment {


    public TransactionFragment() {

    }


    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private AdapterPurchasing adapterPurchasing;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        TransactionViewModel transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        SystemDataLocal dataLocal = new SystemDataLocal(this.getContext());
        User user = dataLocal.getLoginData();
        progressBar.setVisibility(View.VISIBLE);
        transactionViewModel.getDataOrder(user.getUsername()).observe(this, orderEntity -> {
            if (orderEntity.getDataOrder().size() > 0 || orderEntity.getDataTransaction().size() > 0) {
                List<Order> listOrder = orderEntity.getDataOrder();
                List<Order> listTransaction = orderEntity.getDataTransaction();
                listTransaction.addAll(listOrder);
                adapterPurchasing = new AdapterPurchasing(listTransaction);
                recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                recyclerView.setAdapter(adapterPurchasing);
                progressBar.setVisibility(View.GONE);
            }else{
                progressBar.setVisibility(View.GONE);
            }
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        System.out.println("onResume");
    }
}
