package com.example.tugas_akhir;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.tugas_akhir.CLASS.User;
import com.example.tugas_akhir.LOGIN.LoginActivity;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.EventListener;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.FirebaseFirestoreException;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button btnlogout;
    private TextView txtHelloWorld;
    FirebaseAuth firebaseAuth;
    FirebaseFirestore firebaseFirestore;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btnlogout = (Button) findViewById(R.id.btnLogout);
        btnlogout.setOnClickListener(this);
        txtHelloWorld = (TextView) findViewById(R.id.txtHelloWorld);
        firebaseAuth = FirebaseAuth.getInstance();
        firebaseFirestore = firebaseFirestore.getInstance();
        firebaseFirestore.collection("user").document(firebaseAuth.getCurrentUser().getUid()).addSnapshotListener(new EventListener<DocumentSnapshot>() {
            @Override
            public void onEvent(@Nullable DocumentSnapshot value, @Nullable FirebaseFirestoreException error) {
                User user = value.toObject(User.class);
                txtHelloWorld.setText(user.getNama_lengkap());
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch(v.getId()){
            case R.id.btnLogout:
                firebaseAuth.signOut();
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
                break;
        }
    }
}