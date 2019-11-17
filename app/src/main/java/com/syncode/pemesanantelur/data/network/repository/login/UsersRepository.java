package com.syncode.pemesanantelur.data.network.repository.login;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.login.ResponseLogin;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class UsersRepository {

    private MutableLiveData<ResponseLogin> usersData = new MutableLiveData<>();
    private ApiInterface apiInterface;

    public UsersRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public MutableLiveData<ResponseLogin> getLoginResponse(String username, String password) {
        Call<ResponseLogin> requestLogin = apiInterface.login(username, password);
        requestLogin.enqueue(new Callback<ResponseLogin>() {
            @Override
            public void onResponse(@NonNull Call<ResponseLogin> call, @NonNull Response<ResponseLogin> response) {
                if (response.body() != null) {
                    usersData.postValue(response.body());
                } else {
                    System.out.println("Data Not Found");
                }
            }

            @Override
            public void onFailure(@NonNull Call<ResponseLogin> call, @NonNull Throwable t) {
                System.out.println(t.getMessage());
            }
        });
        return usersData;
    }
}
