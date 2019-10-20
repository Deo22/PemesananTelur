package com.syncode.pemesanantelur.data.network.repository.product;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.product.Product;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ProductRepository  {


    private MutableLiveData<Product> productData = new MutableLiveData<>();
    private ApiInterface apiInterface;
    private final String DEBUG_POS = this.getClass().getSimpleName();

    public ProductRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<Product> getProductData() {
        Call<Product> requestOrder = apiInterface.getProduct();
        requestOrder.enqueue(new Callback<Product>() {
            @Override
            public void onResponse(@NonNull Call<Product> call, @NonNull Response<Product> response) {
                if (response.body() != null) {
                    productData.postValue(response.body());
                } else {
                    Log.d(DEBUG_POS, "No Respon");
                }

            }

            @Override
            public void onFailure(@NonNull Call<Product> call, @NonNull Throwable t) {
                Log.d(DEBUG_POS, t.getMessage());
            }
        });

        return productData;
    }
}
