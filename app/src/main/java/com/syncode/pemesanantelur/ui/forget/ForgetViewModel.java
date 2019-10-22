package com.syncode.pemesanantelur.ui.forget;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.repository.forget.ForgetPasswordRepository;

public class ForgetViewModel extends ViewModel {


    private ForgetPasswordRepository forgetPasswordRepository;

    public ForgetViewModel() {
        forgetPasswordRepository = new ForgetPasswordRepository();
    }

    public LiveData<MessageOnly> messageOnlyLiveData(String email) {
        return forgetPasswordRepository.getForget(email);
    }

    public LiveData<MessageOnly> messageOnlyLiveData(String email, String otp) {
        return forgetPasswordRepository.sendToken(email, otp);
    }

    public LiveData<MessageOnly> getResetPassword(String email, String password) {
        return forgetPasswordRepository.getResetPassword(email, password);
    }
}
