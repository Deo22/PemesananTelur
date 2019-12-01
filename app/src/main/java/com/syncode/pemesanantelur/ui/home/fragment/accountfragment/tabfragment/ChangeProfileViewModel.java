package com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.ViewModel;

import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.repository.changeaccount.ChangeProfileRepository;

public class ChangeProfileViewModel extends ViewModel {

    private ChangeProfileRepository changeProfileRepository;


    public ChangeProfileViewModel() {
        this.changeProfileRepository = new ChangeProfileRepository();
    }

    public LiveData<MessageOnly> changeProfile(String username,
                                               String lName,
                                               String fName,
                                               String nameShop,
                                               String email,
                                               String address,
                                               String phone,String coordinate) {
        return changeProfileRepository.changeProfile(username, lName, fName, nameShop, email, address, phone,coordinate);
    }
}
