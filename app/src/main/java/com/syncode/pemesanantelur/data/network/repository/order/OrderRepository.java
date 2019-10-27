package com.syncode.pemesanantelur.data.network.repository.order;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {


    private MutableLiveData<MessageOnly> orderData = new MutableLiveData<>();
    private ApiInterface apiInterface;
    private final String DEBUG_POS = this.getClass().getSimpleName();

    public OrderRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> sendOrderData(String idOrder, String username, String idAddress, String idAgent, String idProduct, int jumlahOrder, String date) {
        Call<MessageOnly> requestOrder = apiInterface.order(idOrder, username, idAddress, idAgent, idProduct, jumlahOrder, date);
        requestOrder.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    orderData.postValue(response.body());
                } else {
                    Log.d(DEBUG_POS, String.valueOf(response.code()));
                }
                try {
                    if (response.errorBody() != null) {
                        System.out.println(response.errorBody().string());
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {
                Log.d(DEBUG_POS, t.getMessage());
            }
        });

        return orderData;
    }

}
