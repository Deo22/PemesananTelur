package com.syncode.pemesanantelur.data.network.repository.order;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.ResponseChangeCount;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class OrderRepository {



    private ApiInterface apiInterface;


    public OrderRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> sendOrderData(String idOrder, String username, String idAddress, String idAgent, String idProduct, int jumlahOrder, String date) {
         MutableLiveData<MessageOnly> order = new MutableLiveData<>();
        Call<MessageOnly> requestOrder = apiInterface.order(idOrder, username, idAddress, idAgent, idProduct, jumlahOrder, date);
        requestOrder.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    order.postValue(response.body());
                }else{
                    order.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {
                order.postValue(null);
            }
        });

        return order;
    }

    public MutableLiveData<ResponseChangeCount> getTotalWithCountBuy(String id_product, int count) {
        MutableLiveData<ResponseChangeCount> responseChangeCountMutableLiveData = new MutableLiveData<>();
        Call<ResponseChangeCount> responseChangeCountCall = apiInterface.changeCountTotal(id_product, count);
        responseChangeCountCall.enqueue(new Callback<ResponseChangeCount>() {
            @Override
            public void onResponse(@NonNull Call<ResponseChangeCount> call, @NonNull Response<ResponseChangeCount> response) {
                if (response.body() != null) {
                    responseChangeCountMutableLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseChangeCount> call, @NonNull Throwable t) {
                responseChangeCountMutableLiveData.postValue(null);

            }
        });
        return responseChangeCountMutableLiveData;
    }

    public MutableLiveData<MessageOnly> removeOrder(String idOrder) {
        MutableLiveData<MessageOnly> removeOrderLiveData = new MutableLiveData<>();
        Call<MessageOnly> messageOnlyCall = apiInterface.removeOrder(idOrder);
        messageOnlyCall.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    removeOrderLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {
                removeOrderLiveData.postValue(null);
            }
        });

        return removeOrderLiveData;
    }
}
