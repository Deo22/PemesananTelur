package com.syncode.pemesanantelur.data.network.repository.transaction;

import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.order.OrderEntity;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class TransactionRepository {


    private MutableLiveData<OrderEntity> orderEntityMutableLiveData = new MutableLiveData<>();
    private ApiInterface apiInterface;


    public TransactionRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }


    public MutableLiveData<OrderEntity> getOrderEntityMutableLiveData(String username) {
        Call<OrderEntity> requestDataOrder = apiInterface.getOrder(username);
        requestDataOrder.enqueue(new Callback<OrderEntity>() {
            @Override
            public void onResponse(Call<OrderEntity> call, Response<OrderEntity> response) {
                if (response.body() != null) {
                    orderEntityMutableLiveData.postValue(response.body());
                }else{
                    System.out.println("error");
                }
            }

            @Override
            public void onFailure(Call<OrderEntity> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return orderEntityMutableLiveData;
    }
}
