package com.syncode.pemesanantelur.ui.home.fragment.accountfragment.tabfragment;


import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.os.Bundle;
import android.text.InputType;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.user.User;
import com.syncode.pemesanantelur.data.network.repository.verificationemail.VerificationEmailRepository;
import com.syncode.pemesanantelur.utils.DialogClass;

public class ProfileFragment extends Fragment implements Observer<MessageOnly> {


    private EditText edtEmail, edtUsername;

    private SystemDataLocal systemDataLocal;
    private VerificationEmailRepository verificationEmailRepository;
    private AlertDialog alertDialog;

    private Button btnUbah;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        EditText edtFname = view.findViewById(R.id.edtFirstName);
        EditText edtlName = view.findViewById(R.id.edtLastName);
        EditText edtAddress = view.findViewById(R.id.edt_address);
        EditText edtShop = view.findViewById(R.id.edt_shop);
        edtEmail = view.findViewById(R.id.edt_email);
        edtUsername = view.findViewById(R.id.edt_username);
        systemDataLocal = new SystemDataLocal(getContext());
        btnUbah = view.findViewById(R.id.btnUbah);
        User user = systemDataLocal.getLoginData();
        edtUsername.setText(user.getUsername());
        edtEmail.setText(user.getEmail());
        edtShop.setText(user.getNameShop());
        edtAddress.setText(user.getAddress());
        edtFname.setText(user.getfName());
        edtlName.setText(user.getlName());
        edtEmail.setInputType(InputType.TYPE_NULL);
        verificationEmailRepository = new VerificationEmailRepository();
        edtEmail.setOnClickListener(view1 -> alertDialogEmailVerify());
        btnUbah.setOnClickListener(view12 -> {
            edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
        });
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_profile, container, false);
    }


    @Override
    public void onResume() {
        super.onResume();
        if (systemDataLocal.getLoginData().getIsVerified() == 0) {
            edtEmail.setError("Verifikasi Email,Klik Disini !!");
            edtEmail.setEnabled(true);
            edtEmail.requestFocus();
        }
    }


    private void loadingDialog() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
        alertDialog = DialogClass.dialog(getContext(), v).create();
        alertDialog.show();
    }


    private void alertDialogEmailVerify() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.verification_email_dialog, null, false);
        AlertDialog.Builder builderVerify = DialogClass.dialog(getContext(), v);
        builderVerify.setNegativeButton("Nanti", (dialogInterface, i) -> {
            dialogInterface.dismiss();
        }).setPositiveButton("Verifikasi", (dialogInterface, i) -> {
            loadingDialog();
            verificationEmailRepository.getTokenVerification(systemDataLocal.getLoginData().getEmail()).observe(this, this);
        });
        alertDialog = builderVerify.create();
        alertDialog.show();
    }


    private void inputTokenDialog() {
        @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.dialog_input_token, null, false);
        AlertDialog.Builder inputTokenBuilder = DialogClass.dialog(getContext(), v);
        EditText edtToken = v.findViewById(R.id.edtToken);
        inputTokenBuilder.setNegativeButton("Keluar", (dialogInterface, i) -> {
            dialogInterface.dismiss();
            alertDialog.dismiss();
        }).setPositiveButton("Verifikasi", (dialogInterface, i) -> {
            loadingDialog();
            String token = edtToken.getText().toString().trim();
            verificationEmailRepository.verificationEmail(edtEmail.getText().toString(), edtUsername.getText().toString(), token).observe(this, this);
            systemDataLocal.editEmailIsVerified(1);
        });
        alertDialog = inputTokenBuilder.create();
        alertDialog.show();
    }

    @Override
    public void onChanged(MessageOnly messageOnly) {
        alertDialog.dismiss();
        inputTokenDialog();
        Toast.makeText(getContext(), messageOnly.getMessage(), Toast.LENGTH_LONG).show();
    }

}
