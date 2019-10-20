package com.syncode.pemesanantelur.ui.regis.viewmodel;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.register.ResponRegister;
import com.syncode.pemesanantelur.data.network.repository.register.RegisterRepository;

public class RegisterViewModel extends ViewModel {

    private RegisterRepository registerRepository;

    public RegisterViewModel() {
        registerRepository = new RegisterRepository();
    }

    public LiveData<ResponRegister> getRegisterRespon(String username, String email, String password, String fname, String lname) {
        return registerRepository.getResponRegister(username, email, password, fname, lname);
    }


    public LiveData<ResponRegister> getReRegisterRespon(String username, String street, String idAgent, String nameShop, String coordinate) {
        return registerRepository.getreResponRegister(username, street, idAgent, nameShop, coordinate);
    }

}
