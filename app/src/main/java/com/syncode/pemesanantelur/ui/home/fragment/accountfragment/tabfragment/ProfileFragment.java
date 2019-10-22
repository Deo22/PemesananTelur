package com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment;


import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.user.User;

public class ProfileFragment extends Fragment {


    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        EditText edtFname = view.findViewById(R.id.edtFirstName);
        EditText edtlName = view.findViewById(R.id.edtLastName);
        EditText edtAddress = view.findViewById(R.id.edt_address);
        EditText edtShop = view.findViewById(R.id.edt_shop);
        EditText edtEmail = view.findViewById(R.id.edt_email);
        EditText edtUsername = view.findViewById(R.id.edt_username);
        SystemDataLocal systemDataLocal = new SystemDataLocal(getContext());
        User user = systemDataLocal.getLoginData();
        edtUsername.setText(user.getUsername());
        edtEmail.setText(user.getEmail());
        edtShop.setText(user.getNameShop());
        edtAddress.setText(user.getAddress());
        edtFname.setText(user.getfName());
        edtlName.setText(user.getlName());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_profile, container, false);
    }

}
