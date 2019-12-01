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
import androidx.lifecycle.ViewModelProviders;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.local.sharepref.SystemDataLocal;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.data.model.user.User;
import com.syncode.pemesanantelur.data.network.repository.verificationemail.VerificationEmailRepository;
import com.syncode.pemesanantelur.ui.maps.MapsActivity;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class ProfileFragment extends Fragment implements Observer<MessageOnly> {


    private EditText edtEmail, edtUsername;

    private SystemDataLocal systemDataLocal;
    private VerificationEmailRepository verificationEmailRepository;
    private AlertDialog alertDialog;

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ChangeProfileViewModel changeProfileViewModel = ViewModelProviders.of(this).get(ChangeProfileViewModel.class);
        EditText edtFname = view.findViewById(R.id.edtFirstName);
        EditText edtlName = view.findViewById(R.id.edtLastName);
        EditText edtAddress = view.findViewById(R.id.edt_address);
        EditText edtShop = view.findViewById(R.id.edt_shop);
        EditText edtPhone = view.findViewById(R.id.edt_phone);
        edtEmail = view.findViewById(R.id.edt_email);
        edtUsername = view.findViewById(R.id.edt_username);
        systemDataLocal = new SystemDataLocal(getContext());
        Button btnUbah = view.findViewById(R.id.btnUbah);
        User user = systemDataLocal.getLoginData();
        edtUsername.setText(user.getUsername());
        edtEmail.setText(user.getEmail());
        edtShop.setText(user.getNameShop());
        edtAddress.setText(user.getAddress());
        edtFname.setText(user.getfName());
        edtlName.setText(user.getlName());
        edtPhone.setText(user.getPhone());
        verificationEmailRepository = new VerificationEmailRepository();
        edtEmail.setOnClickListener(view1 -> alertDialogEmailVerify());
        edtAddress.setOnClickListener((view13) -> SwitchActivity.mainSwitch(this.getActivity(), MapsActivity.class, true, "pick"));
        btnUbah.setOnClickListener(view12 -> {
            if (btnUbah.getText().toString().toLowerCase().equals("simpan")) {
                loadingDialog();
                edtEmail.setInputType(InputType.TYPE_TEXT_VARIATION_EMAIL_ADDRESS);
                String username = edtUsername.getText().toString().trim();
                String nameShop = edtShop.getText().toString().trim();
                String email = edtEmail.getText().toString().trim();
                String fName = edtFname.getText().toString().trim();
                String lName = edtlName.getText().toString().trim();
                String phone = edtPhone.getText().toString().trim();
                String newAddress;

                if (systemDataLocal.getCoordinate().getString("addr", "").isEmpty()) {
                    newAddress = systemDataLocal.getLoginData().getAddress();
                } else {
                    newAddress = systemDataLocal.getCoordinate().getString("addr", "");
                }
                String coordinate;
                if (systemDataLocal.getCoordinate().getString("coordinate", "").isEmpty()) {
                    coordinate = systemDataLocal.getLoginData().getCoordinate();
                } else {
                    coordinate = systemDataLocal.getCoordinate().getString("coordinate", "");
                }
                systemDataLocal.editAllSessionLogin(
                        username,
                        systemDataLocal.getLoginData().getPassword(),
                        lName,
                        fName,
                        newAddress,
                        email,
                        nameShop,
                        systemDataLocal.getLoginData().getIdAddr(),
                        systemDataLocal.getLoginData().getIsVerified(),
                        coordinate,
                        phone
                );

                changeProfileViewModel.changeProfile(
                        systemDataLocal.getLoginData().getUsername(),
                        systemDataLocal.getLoginData().getlName(),
                        systemDataLocal.getLoginData().getfName(),
                        systemDataLocal.getLoginData().getNameShop(),
                        systemDataLocal.getLoginData().getEmail(),
                        systemDataLocal.getLoginData().getAddress(),
                        systemDataLocal.getLoginData().getPhone(),
                        systemDataLocal.getLoginData().getCoordinate()
                ).observe(this, messageOnly -> {
                    if (messageOnly != null) {
                        alertDialog.dismiss();
                        Toast.makeText(getContext(), messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                        edtAddress.setText(systemDataLocal.getLoginData().getAddress());
                        systemDataLocal.destroyCoordinate();
                    } else {
                        alertDialog.dismiss();
                    }
                    btnUbah.setText("Ubah");
                    edtFname.setEnabled(false);
                    edtlName.setEnabled(false);
                    edtEmail.setEnabled(false);
                    edtAddress.setEnabled(false);
                    edtPhone.setEnabled(false);
                    edtShop.setEnabled(false);
                });
            } else {
                edtFname.setEnabled(true);
                edtlName.setEnabled(true);
                edtEmail.setEnabled(true);
                edtAddress.setEnabled(true);
                edtPhone.setEnabled(true);
                edtShop.setEnabled(true);
                btnUbah.setText("simpan");
                edtShop.setFocusable(true);
            }
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
        edtEmail.setInputType(InputType.TYPE_NULL);
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
