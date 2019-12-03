package com.syncode.pemesanantelur.data.network.repository.changeaccount;


import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangePasswordRepository {


    private ApiInterface apiInterface;

    public ChangePasswordRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    public MutableLiveData<MessageOnly> changePassword(String username, String oldPassword, String newPassword) {
        MutableLiveData<MessageOnly> changePass = new MutableLiveData<>();
        Call<MessageOnly> requestChangePassword = apiInterface.changePassword(username, oldPassword, newPassword);
        requestChangePassword.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    changePass.postValue(response.body());
                } else {
                    changePass.postValue(null);
                }

            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {
                changePass.postValue(null);
            }
        });

        return changePass;
    }
}
