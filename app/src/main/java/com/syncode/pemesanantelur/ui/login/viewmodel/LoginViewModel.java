package com.syncode.pemesanantelur.ui.login.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.login.Login;
import com.syncode.pemesanantelur.data.network.repository.login.UsersRepository;

public class LoginViewModel extends ViewModel {

    private LiveData<Login> userEntityLiveData;
    private UsersRepository usersRepository;


    public LoginViewModel() {
        usersRepository = new UsersRepository();
    }


    public void setLogin(String username, String password) {
        userEntityLiveData = usersRepository.getLoginResponse(username, password);
    }


    public LiveData<Login> getUserEntityLiveData() {
        return userEntityLiveData;
    }
}
