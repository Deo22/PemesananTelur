package com.syncode.pemesanantelur.data.network.repository.forget;

import androidx.annotation.NonNull;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class ForgetPasswordRepository {


    private MutableLiveData<MessageOnly> forget = new MutableLiveData<>();
    private ApiInterface apiInterface;

    public ForgetPasswordRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);

    }

    public MutableLiveData<MessageOnly> getForget(String email) {
        Call<MessageOnly> requestForget = apiInterface.forgetPassword(email);
        requestForget.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    forget.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, @NonNull Throwable t) {

            }
        });
        return forget;
    }

    public MutableLiveData<MessageOnly> sendToken(String email, String otp) {
        Call<MessageOnly> requestNewPassword = apiInterface.tokenPassword(email, otp);
        requestNewPassword.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    forget.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, Throwable t) {

            }
        });
        return forget;
    }

    public MutableLiveData<MessageOnly> getResetPassword(String email, String password) {
        Call<MessageOnly> requestNewPassword = apiInterface.resetPassword(email, password);
        requestNewPassword.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    forget.setValue(response.body());
                }
            }

            @Override
            public void onFailure(@NonNull Call<MessageOnly> call, Throwable t) {

            }
        });
        return forget;
    }
}
