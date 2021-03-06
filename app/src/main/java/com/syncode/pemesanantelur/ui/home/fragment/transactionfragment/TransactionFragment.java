package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.swiperefreshlayout.widget.SwipeRefreshLayout;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.order.Order;
import com.syncode.pemesanantelur.data.model.user.User;

import java.util.ArrayList;
import java.util.List;


public class TransactionFragment extends Fragment {


    public TransactionFragment() {

    }


    private RecyclerView recyclerView;

    private ProgressBar progressBar;
    private AdapterTransaction adapterTransaction;
    private TransactionViewModel transactionViewModel;
    private User user;
    private List<Order> tmpAllTransaction = new ArrayList<>();
    private LinearLayout errorLayout;
    private SwipeRefreshLayout swipeRefreshLayout;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        recyclerView = view.findViewById(R.id.recycleView);
        progressBar = view.findViewById(R.id.progressBar);
        errorLayout = view.findViewById(R.id.errorLayout);
        swipeRefreshLayout = view.findViewById(R.id.swipeRefresh);
        SystemDataLocal dataLocal = new SystemDataLocal(this.getContext());
        user = dataLocal.getLoginData();
        progressBar.setVisibility(View.VISIBLE);
        swipeRefreshLayout.setOnRefreshListener(this::loadData);

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_transaction, container, false);
    }

    @Override
    public void onResume() {
        super.onResume();
        loadData();
    }


    private  void loadData(){
        transactionViewModel = ViewModelProviders.of(this).get(TransactionViewModel.class);
        tmpAllTransaction.clear();
        transactionViewModel.getDataOrder(user.getUsername()).observe(this,orderEntity -> {
            tmpAllTransaction.clear();
            if(orderEntity !=null) {
                if (orderEntity.getDataTransaction() != null) {
                    tmpAllTransaction.addAll(orderEntity.getDataTransaction());
                }
                if (orderEntity.getRowCount() > 0) {
                    tmpAllTransaction.addAll(orderEntity.getDataOrder());
                }
                if (tmpAllTransaction.size() > 0) {
                    progressBar.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.GONE);
                    swipeRefreshLayout.setVisibility(View.VISIBLE);
                    adapterTransaction = new AdapterTransaction(tmpAllTransaction);
                    recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
                    recyclerView.setAdapter(adapterTransaction);
                } else {
                    progressBar.setVisibility(View.GONE);
                    errorLayout.setVisibility(View.VISIBLE);
                    swipeRefreshLayout.setVisibility(View.GONE);
                }
                swipeRefreshLayout.setRefreshing(false);
            }else{
                progressBar.setVisibility(View.GONE);
                errorLayout.setVisibility(View.VISIBLE);
                swipeRefreshLayout.setVisibility(View.GONE);
            }
        });
    }
}
