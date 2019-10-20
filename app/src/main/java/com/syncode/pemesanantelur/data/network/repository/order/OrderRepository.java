package com.syncode.pemesanantelur.data.network.repository.order;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {


    private MutableLiveData<OrderEntity> orderData = new MutableLiveData<>();
    private ApiInterface apiInterface;
    private final String DEBUG_POS = this.getClass().getSimpleName();

    public OrderRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<OrderEntity> getOrderData(String idOrder, String namaProduct, String namaAgent, String namaKurir, String ket, String ulasan, String alamat, int harga, int jumlah, int status, String tanggal) {
        Call<OrderEntity> requestOrder = apiInterface.order(idOrder, namaProduct, jumlah, harga, tanggal, status, namaAgent, alamat, namaKurir, ket, ulasan);
        requestOrder.enqueue(new Callback<OrderEntity>() {
            @Override
            public void onResponse(@NonNull Call<OrderEntity> call, @NonNull Response<OrderEntity> response) {
                if (response.body() != null) {
                    orderData.postValue(response.body());
                } else {
                    Log.d(DEBUG_POS, "No Respon");
                }
            }

            @Override
            public void onFailure(@NonNull Call<OrderEntity> call, @NonNull Throwable t) {
                Log.d(DEBUG_POS, t.getMessage());
            }
        });

        return orderData;
    }

    public MutableLiveData<OrderEntity> getAllOrderData() {
        Call<OrderEntity> requestGetOrder = apiInterface.getOrder();
        requestGetOrder.enqueue(new Callback<OrderEntity>() {
            @Override
            public void onResponse(Call<OrderEntity> call, Response<OrderEntity> response) {
                if (response.body() != null) {
                    orderData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<OrderEntity> call, Throwable t) {

            }
        });
        return orderData;
    }
}
