package com.syncode.pemesanantelur.ui.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.login.ResponseLogin;
import com.syncode.pemesanantelur.data.network.repository.login.UsersRepository;

public class LoginViewModel extends ViewModel {

    private LiveData<ResponseLogin> userEntityLiveData;
    private UsersRepository usersRepository;


    public LoginViewModel() {
        usersRepository = new UsersRepository();
    }


    public void setLogin(String username, String password) {
        userEntityLiveData = usersRepository.getLoginResponse(username, password);
    }


    public LiveData<ResponseLogin> getUserEntityLiveData() {
        return userEntityLiveData;
    }
}
