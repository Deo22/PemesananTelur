package com.syncode.pemesanantelur.data.viewmodel.order;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.network.repository.order.OrderRepository;

public class OrderViewModel extends ViewModel {

    private LiveData<OrderEntity> orderLiveData;
    private OrderRepository orderRepository;


    public OrderViewModel() {
        orderRepository = new OrderRepository();
    }


    public void setOrder(String idOrder, String namaProduct, String namaAgent, String namaKurir, String ket, String ulasan, String alamat, int harga, int jumlah, int status, String tanggal) {
        orderLiveData = orderRepository.getOrderData(idOrder, namaProduct, namaAgent, namaKurir, ket, ulasan, alamat, harga, jumlah, status, tanggal);
    }

    public LiveData<OrderEntity> getOrderLiveData() {
        return orderLiveData;
    }


    public void setOrderAllData() {
        orderLiveData = orderRepository.getAllOrderData();
    }
}
