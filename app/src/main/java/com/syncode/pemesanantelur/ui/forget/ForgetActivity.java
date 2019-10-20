package com.syncode.pemesanantelur.ui.forget;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.syncode.pemesanantelur.R;
import com.syncode.pemesanantelur.data.model.MessageOnly;
import com.syncode.pemesanantelur.utils.DialogClass;
import com.syncode.pemesanantelur.utils.SwitchActivity;

public class ForgetActivity extends AppCompatActivity {


    private EditText edtEmail;

    private Button btnForget;
    private android.app.AlertDialog alertDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget);
        btnForget = findViewById(R.id.btnForget);
        edtEmail = findViewById(R.id.edt_email);
        if (getSupportActionBar() != null) {
            getSupportActionBar().setElevation(0f);
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        }
        ForgetViewModel forgetViewModel = ViewModelProviders.of(this).get(ForgetViewModel.class);
        btnForget.setOnClickListener(view -> {
            @SuppressLint("InflateParams") View v = getLayoutInflater().inflate(R.layout.loading_alert, null, false);
            alertDialog = DialogClass.dialog(this, v).create();
            alertDialog.show();
            String email = edtEmail.getText().toString().trim();

            forgetViewModel.messageOnlyLiveData(email).observe(this, forgetObserver);
        });


    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }


    private Observer<MessageOnly> forgetObserver = new Observer<MessageOnly>() {
        @Override
        public void onChanged(MessageOnly messageOnly) {
            if (messageOnly.isStatus()) {
                Toast.makeText(ForgetActivity.this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
                SwitchActivity.mainSwitch(ForgetActivity.this, InputOTPActivity.class, edtEmail.getText().toString().trim(),"email");
            } else {
                Toast.makeText(ForgetActivity.this, messageOnly.getMessage(), Toast.LENGTH_LONG).show();
                alertDialog.dismiss();
            }
        }
    };
}
