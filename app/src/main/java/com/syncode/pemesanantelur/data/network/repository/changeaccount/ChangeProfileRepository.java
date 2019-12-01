package com.syncode.pemesanantelur.data.network.repository.changeaccount;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ChangeProfileRepository {


    private ApiInterface apiInterface;

    public ChangeProfileRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }


    public MutableLiveData<MessageOnly> changeProfile(String username,
                                                      String lName,
                                                      String fName,
                                                      String nameShop,
                                                      String email,
                                                      String address,
                                                      String phone,String coordinate) {
        MutableLiveData<MessageOnly> changeProfile = new MutableLiveData<>();
        Call<MessageOnly> requestChangePassword = apiInterface.changeProfile(username, lName, fName, nameShop, email, address, phone,coordinate);
        requestChangePassword.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null)
                    changeProfile.postValue(response.body());

            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {
                changeProfile.postValue(null);

            }
        });

        return changeProfile;
    }

}
