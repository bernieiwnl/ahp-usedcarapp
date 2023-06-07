package com.example.tugas_akhir.REGISTER;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;
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
                    try {
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

                        // registration using firestore
                        registerData(inputNamaUser, inputEmail, inputPassword);

                    }catch (Exception e){
                           Log.e("ErrorMsg", e.getMessage());
                    }

            }
        }
    }

    private void registerData(String namaUser, String email, String password){
        try{
            firebaseAuth.createUserWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                @Override
                public void onComplete(@NonNull Task<AuthResult> task) {
                    if(task.isSuccessful()) {
                        Map<String, Object> user = new HashMap<>();
                        user.put("idUser", firebaseAuth.getCurrentUser().getUid());
                        user.put("namaUser", namaUser);
                        user.put("emailUser", email);
                        user.put("passwordUser", password);

                        firebaseFirestore.collection("user").document(firebaseAuth.getCurrentUser().getUid()).set(user).addOnCompleteListener(new OnCompleteListener<Void>() {
                            @Override
                            public void onComplete(@NonNull Task<Void> task) {
                                if(task.isSuccessful()){
                                    Toast.makeText(getApplicationContext(), "Register Sukses", Toast.LENGTH_SHORT).show();
                                    finish();
                                }
                                else{
                                    Toast.makeText(getApplicationContext(), "Register Gagal", Toast.LENGTH_SHORT).show();
                                }
                            }
                        });
                    }
                }
            });
        }catch (Exception e){
            Log.e("ErrorMsg", e.getMessage());
        }
    }

}