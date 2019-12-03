package com.syncode.pemesanantelur.data.network.repository.register;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.register.ResponRegister;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterRepository {


    private ApiInterface apiInterface;

    public RegisterRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<ResponRegister> getResponRegister(String username, String email, String password, String fname, String lname, String phone) {
        MutableLiveData<ResponRegister> responseRegisterMutableLiveData = new MutableLiveData<>();
        Call<ResponRegister> requestOrder = apiInterface.registerUser(username, email, password, fname, lname, phone);
        requestOrder.enqueue(new Callback<ResponRegister>() {
            @Override
            public void onResponse(@NonNull Call<ResponRegister> call, @NonNull Response<ResponRegister> response) {
                if (response.body() != null) {
                    responseRegisterMutableLiveData.postValue(response.body());
                } else {
                    responseRegisterMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponRegister> call, @NonNull Throwable t) {
                responseRegisterMutableLiveData.postValue(null);
            }

        });

        return responseRegisterMutableLiveData;
    }

    public MutableLiveData<ResponRegister> getreResponRegister(String username, String street, String idAgent, String nameShop, String coordinate) {
        MutableLiveData<ResponRegister> responseReRegisterMutableLiveData = new MutableLiveData<>();
        Call<ResponRegister> responRegisterCall = apiInterface.reRegisterUser(username, street, idAgent, nameShop, coordinate);
        responRegisterCall.enqueue(new Callback<ResponRegister>() {
            @Override
            public void onResponse(@NonNull Call<ResponRegister> call, @NonNull Response<ResponRegister> response) {
                if (response.body() != null) {
                    responseReRegisterMutableLiveData.postValue(response.body());
                } else {
                    responseReRegisterMutableLiveData.postValue(null);
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponRegister> call, @NonNull Throwable t) {
                responseReRegisterMutableLiveData.postValue(null);
            }
        });

        return responseReRegisterMutableLiveData;
    }


}
