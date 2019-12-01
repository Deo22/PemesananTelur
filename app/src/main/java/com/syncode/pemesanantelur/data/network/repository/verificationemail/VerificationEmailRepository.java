package com.syncode.pemesanantelur.data.network.repository.verificationemail;

import androidx.annotation.NonNull;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.MutableLiveData;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.api.ApiClient;
import com.syncode.pemesanantelur.data.network.api.ApiInterface;

import java.io.IOException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class VerificationEmailRepository {


    private MutableLiveData<MessageOnly> verificationEmailLiveData = new MutableLiveData<>();
    private ApiInterface apiInterface;


    public VerificationEmailRepository() {
        apiInterface = ApiClient.getClient().create(ApiInterface.class);
    }

    public MutableLiveData<MessageOnly> getTokenVerification(String email) {
        Call<MessageOnly> requestToken = apiInterface.getTokenVerification(email);
        requestToken.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    verificationEmailLiveData.postValue(response.body());
                }
            }

            @Override
            public void onFailure(Call<MessageOnly> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });


        return verificationEmailLiveData;
    }

    public MutableLiveData<MessageOnly> verificationEmail(String email, String username, String otp) {
        Call<MessageOnly> verificationEmail = apiInterface.verificationEmail(email, otp,username);
        verificationEmail.enqueue(new Callback<MessageOnly>() {
            @Override
            public void onResponse(@NonNull Call<MessageOnly> call, @NonNull Response<MessageOnly> response) {
                if (response.body() != null) {
                    verificationEmailLiveData.postValue(response.body());
                    System.out.println(response.body().getMessage());
                }
            }

            @Override
            public void onFailure(Call<MessageOnly> call, Throwable t) {
                System.out.println(t.getMessage());
            }
        });

        return verificationEmailLiveData;
    }


    public void destroyObserver(LifecycleOwner lifecycleOwner) {
        verificationEmailLiveData.removeObservers(lifecycleOwner);
    }
}
