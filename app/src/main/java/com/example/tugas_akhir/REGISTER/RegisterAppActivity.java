package com.example.tugas_akhir.REGISTER;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.R;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.logging.Logger;

public class RegisterAppActivity extends AppCompatActivity implements View.OnClickListener {

    private Button btnRegister;
    private TextInputEditText txtNamaUser, txtEmail, txtPassword;
    private TextView txtViewLogin;
    private FirebaseAuth firebaseAuth;
    private FirebaseFirestore firebaseFirestore;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_app);

        //firebase
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        //input text
        txtNamaUser = (TextInputEditText) findViewById(R.id.inputNamaUser);
        txtEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.inputPassword);

        //button
        btnRegister = (Button) findViewById(R.id.btnRegister);

        //set event Click
        btnRegister.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnRegister: {

                    // define input
                    String inputNamaUser = txtNamaUser.getText().toString();
                    String inputEmail = txtEmail.getText().toString();
                    String inputPassword = txtPassword.getText().toString();

                    // check if input is null than return a message
                    if(TextUtils.isEmpty(inputNamaUser)){
                        Toast.makeText(getApplicationContext(), "Nama User Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    } else if (TextUtils.isEmpty(inputEmail)){
                        Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    }else if (TextUtils.isEmpty(inputPassword)){
                        Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                        return;
                    }

                    registerData(inputNamaUser, inputEmail, inputPassword);

            }
        }
    }

    private void registerData(String namaUser, String email, String password){
        Toast.makeText(getApplicationContext(), "Register Sukses", Toast.LENGTH_SHORT).show();
    }

}