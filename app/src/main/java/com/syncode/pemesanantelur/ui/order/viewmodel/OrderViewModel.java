package com.syncode.pemesanantelur.ui.order.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.ResponseChangeCount;
import com.syncode.pemesanantelur.data.network.repository.order.OrderRepository;

public class OrderViewModel extends ViewModel {

    private OrderRepository orderRepository;


    public OrderViewModel() {
        orderRepository = new OrderRepository();
    }


    public LiveData<MessageOnly> getOrderLiveData(String idOrder, String username, String idAddres, String idAgent, String idProduct, int jumlahOrder, String date) {
        return orderRepository.sendOrderData(idOrder, username, idAddres, idAgent, idProduct, jumlahOrder, date);
    }

    public LiveData<ResponseChangeCount> getTotalWithCountBuy(String id_product, int count) {
        return orderRepository.getTotalWithCountBuy(id_product, count);
    }

    public LiveData<MessageOnly> removeOrder(String idOrder) {
        return orderRepository.removeOrder(idOrder);
    }
}

