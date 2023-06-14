package com.example.tugas_akhir.PELANGGAN;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.tugas_akhir.CLASS.Firestore;
import com.example.tugas_akhir.PELANGGAN.PREFERENSI.PreferensiPelangganAppActivity;
import com.example.tugas_akhir.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class UbahPelangganAppActivity extends AppCompatActivity implements View.OnClickListener {

    private FirebaseFirestore firebaseFirestore;
    private Firestore firestore;

    private ImageView imageViewBack;
    private Button btnUbahDataPelanggan;
    private TextInputEditText txtInputNamaPelanggan, txtInputAlamatPelanggan;
    private String idPelanggan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ubah_pelanggan_app);

        //TextView
        txtInputNamaPelanggan = (TextInputEditText) findViewById(R.id.inputNamaUser);
        txtInputAlamatPelanggan = (TextInputEditText) findViewById(R.id.inputAlamatUser);

        //imageView
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);

        //button
        btnUbahDataPelanggan = (Button) findViewById(R.id.btnUbahDataPelanggan);

        //setOnClickListener
        imageViewBack.setOnClickListener(this);
        btnUbahDataPelanggan.setOnClickListener(this);

        //get intent
        try {
            idPelanggan = (String) getIntent().getStringExtra("idPelanggan");
            fetchData(idPelanggan);
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }


    }

    private void fetchData(String idPelanggan) {
        try {
            firebaseFirestore = FirebaseFirestore.getInstance();
            firestore = new Firestore();
            CollectionReference collection = firebaseFirestore.collection("pelanggan");
            DocumentReference pelangganDb = collection.document(idPelanggan);

            pelangganDb.get().addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
                @Override
                public void onSuccess(DocumentSnapshot documentSnapshot) {
                    if (documentSnapshot == null && !documentSnapshot.exists()) {
                        return;
                    }
                    String namaPelanggan = documentSnapshot.getString("namaPelanggan");
                    String alamatPelanggan = documentSnapshot.getString("alamatPelanggan");

                    txtInputNamaPelanggan.setText(namaPelanggan);
                    txtInputAlamatPelanggan.setText(alamatPelanggan);

                }
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }

    private void ubahDataPelanggan(String idPelanggan) {
        try {
            String namaPelanggan = txtInputNamaPelanggan.getText().toString();
            String alamatPelanggan = txtInputAlamatPelanggan.getText().toString();
            if (TextUtils.isEmpty(namaPelanggan)) {
                Toast.makeText(getApplicationContext(), "Nama Pelanggan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            } else if (TextUtils.isEmpty(alamatPelanggan)) {
                Toast.makeText(getApplicationContext(), "Alamat Pelanggan Tidak Boleh Kosong", Toast.LENGTH_SHORT).show();
                return;
            }

            firebaseFirestore = FirebaseFirestore.getInstance();
            firestore = new Firestore();
            CollectionReference collection = firebaseFirestore.collection("pelanggan");
            DocumentReference pelangganDb = collection.document(idPelanggan);

            Map<String, Object> pelanggan = new HashMap<>();
            pelanggan.put("namaPelanggan", namaPelanggan);
            pelanggan.put("alamatPelanggan", alamatPelanggan);

            pelangganDb.update(pelanggan).addOnSuccessListener(v -> {
                Toast.makeText(getApplicationContext(), "Data Pelanggan Berhasil di ubah", Toast.LENGTH_SHORT).show();
                finish();
            }).addOnFailureListener(error -> {
                Log.e("ErrorMsg", error.getMessage());
                Toast.makeText(getApplicationContext(), error.getMessage(), Toast.LENGTH_SHORT).show();

            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }


    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                this.finish();
                break;
            }
            case R.id.btnUbahDataPelanggan: {
                ubahDataPelanggan(idPelanggan);
                break;
            }
        }
    }
}