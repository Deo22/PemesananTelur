package com.syncode.pemesanantelur.ui.home.fragment.transactionfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.network.repository.transaction.TransactionRepository;

public class TransactionViewModel extends ViewModel {

    private TransactionRepository transactionRepository;

    public TransactionViewModel() {
        transactionRepository = new TransactionRepository();
    }


    public LiveData<OrderEntity> getDataOrder(String username) {
        return transactionRepository.getOrderEntityMutableLiveData(username);
    }
}
