package com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.network.repository.changeaccount.ChangePasswordRepository;
import com.syncode.pemesanantelur.utils.DialogClass;


public class PasswordFragment extends Fragment {

    private EditText edtOldPassword;
    private EditText edtNewPassword;
    private ChangePasswordRepository changePasswordRepository;
    private SystemDataLocal systemDataLocal;
    private AlertDialog alertDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        edtNewPassword = view.findViewById(R.id.edtNewPassword);
        edtOldPassword = view.findViewById(R.id.edtOldPassword);
        Button btnChangePassword = view.findViewById(R.id.btnSubmit);
        changePasswordRepository = new ChangePasswordRepository();
        systemDataLocal = new SystemDataLocal(this.getContext());
        btnChangePassword.setOnClickListener(view1 -> {
            @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
            alertDialog = DialogClass.dialog(getContext(), v).create();
            alertDialog.show();
            String oldPass = edtOldPassword.getText().toString().trim();
            String newPass = edtNewPassword.getText().toString().trim();
            changePasswordRepository.changePassword(systemDataLocal.getLoginData().getUsername(), oldPass, newPass).observe(this, new Observer<MessageOnly>() {
                @Override
                public void onChanged(MessageOnly messageOnly) {
                    if (messageOnly.isStatus()) {
                        Toast.makeText(getContext(), messageOnly.getMessage(), Toast.LENGTH_LONG).show();

                        alertDialog.dismiss();
                    } else {
                        Toast.makeText(getContext(), messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                        alertDialog.dismiss();
                    }
                }
            });
        });

    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        return inflater.inflate(R.layout.fragment_password, container, false);
    }

}
