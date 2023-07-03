package com.example.tugas_akhir.LOGIN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.tugas_akhir.HOME.HomeActivity;
import com.example.tugas_akhir.HOME.HomeAppActivty;
import com.example.tugas_akhir.MainActivity;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REGISTER.RegisterActivity;
import com.example.tugas_akhir.REGISTER.RegisterAppActivity;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener {
    private TextInputEditText txtEmail, txtPassword;
    private Button btnLogin;
    private TextView txtBuatAkun;
    FirebaseFirestore firebaseFirestore;
    FirebaseAuth firebaseAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            getWindow().setStatusBarColor(Color.parseColor("#3F51B5"));
        }

        txtEmail = (TextInputEditText) findViewById(R.id.inputEmail);
        txtPassword = (TextInputEditText) findViewById(R.id.inputPassword);
        btnLogin = (Button) findViewById(R.id.btnLogin);
        txtBuatAkun = (TextView) findViewById(R.id.textBuatAkun);

        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = FirebaseFirestore.getInstance();

        btnLogin.setOnClickListener(this);
        txtBuatAkun.setOnClickListener(this);

        if (firebaseAuth.getCurrentUser() != null) {
            startActivity(new Intent(LoginActivity.this, HomeAppActivty.class));
            this.finish();
        }
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.btnLogin:
                // define input
                String inputEmail = txtEmail.getText().toString().trim();
                String inputPassword = txtPassword.getText().toString().trim();

                // check if input is null than return a message
                if (TextUtils.isEmpty(inputEmail)) {
                    Toast.makeText(getApplicationContext(), "Email Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    return;
                } else if (TextUtils.isEmpty(inputPassword)) {
                    Toast.makeText(getApplicationContext(), "Password Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                    return;
                }

                login(inputEmail, inputPassword);
                break;
            case R.id.textBuatAkun:
                startActivity(new Intent(LoginActivity.this, RegisterAppActivity.class));
                break;
        }
    }

    public void login(final String email, String password) {
        firebaseFirestore = FirebaseFirestore.getInstance();
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseAuth.signInWithEmailAndPassword(email, password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
            @Override
            public void onComplete(@NonNull Task<AuthResult> task) {
                if (task.isSuccessful()) {
                    startActivity(new Intent(LoginActivity.this, HomeAppActivty.class));
                    finish();
                } else {
                    Toast.makeText(LoginActivity.this, task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}