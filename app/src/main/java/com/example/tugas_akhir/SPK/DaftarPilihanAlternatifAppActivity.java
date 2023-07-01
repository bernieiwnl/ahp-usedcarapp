package com.example.tugas_akhir.SPK;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;


import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.SPK.ADAPTER.DaftarPilihanAlternatifAppAdapter;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.bottomappbar.BottomAppBar;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class DaftarPilihanAlternatifAppActivity extends AppCompatActivity implements View.OnClickListener {

    private ImageView imageViewBack;
    private RecyclerView recyclerViewAlternatif;
    private ArrayList<NewMobil> mobils;
    private ArrayList<NewMobil> alternatifs;
    private DaftarPilihanAlternatifAppAdapter daftarPilihanAlternatifAppAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_pilihan_alternatif_app);

        //Image View
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);

        //RecyClerView
        recyclerViewAlternatif = (RecyclerView) findViewById(R.id.recycler_alternatif);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DaftarPilihanAlternatifAppActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAlternatif.setLayoutManager(linearLayoutManager);


        //ArrayList
        mobils = new ArrayList<>();

        //fetch data
        fetchData();

        //setOnClickListener
        imageViewBack.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.imageView_back: {
                try {
                    finish();
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
        }
    }

    private void fetchData() {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference mobilCollections = firebaseFirestore.collection("mobil");

            mobilCollections.get().addOnSuccessListener(documentSnapshots -> {
                mobils.clear();
                if (documentSnapshots.isEmpty()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada data mobil", Toast.LENGTH_SHORT).show();
                    return;
                }

                for (QueryDocumentSnapshot data : documentSnapshots) {
                    NewMobil dataMobil = data.toObject(NewMobil.class);
                    mobils.add(dataMobil);
                }

                daftarPilihanAlternatifAppAdapter = new DaftarPilihanAlternatifAppAdapter(DaftarPilihanAlternatifAppActivity.this, mobils);
                recyclerViewAlternatif.setAdapter(daftarPilihanAlternatifAppAdapter);

            }).addOnFailureListener(e -> {
                Log.e("ErrorMsg", e.getMessage());
                Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
            });

        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}