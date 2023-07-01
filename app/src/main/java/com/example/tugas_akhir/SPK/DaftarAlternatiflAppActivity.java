package com.example.tugas_akhir.SPK;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;


import com.example.tugas_akhir.CLASS.PrefKriteria;
import com.example.tugas_akhir.CLASS.Preferensi;
import com.example.tugas_akhir.MOBIL.CLASS.NewMobil;
import com.example.tugas_akhir.R;
import com.example.tugas_akhir.REKOMENDASI.DaftarAlternatifActivity;
import com.example.tugas_akhir.REKOMENDASI.PemilihanAlternatifActivity;
import com.example.tugas_akhir.SPK.ADAPTER.DaftarAlternatifAppAdapter;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.CollectionReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.DocumentSnapshot;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;

public class DaftarAlternatiflAppActivity extends AppCompatActivity implements View.OnClickListener {


    private ImageView imageViewBack, imageViewTambahAlternatif;
    private RecyclerView recyclerViewAlternatif;
    private ArrayList<NewMobil> mobils;
    private ArrayList<PrefKriteria> prefKriterias;
    private Button buttonRekomendasi;

    private DaftarAlternatifAppAdapter daftarAlternatifAppAdapter;
    private String idPelanggan;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_daftar_alternatif_app);

        //Image View
        imageViewBack = (ImageView) findViewById(R.id.imageView_back);
        imageViewTambahAlternatif = (ImageView) findViewById(R.id.imageView_tambahAlternatif);

        //Button
        buttonRekomendasi = (Button) findViewById(R.id.btnRekomendasi);

        //RecyClerView
        recyclerViewAlternatif = (RecyclerView) findViewById(R.id.recycler_alternatif);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(DaftarAlternatiflAppActivity.this, LinearLayoutManager.HORIZONTAL, false);
        recyclerViewAlternatif.setLayoutManager(linearLayoutManager);


        //ArrayList
        mobils = new ArrayList<>();

        //get intent
        idPelanggan = getIntent().getStringExtra("idPelanggan");

        //get preferensi pelanggan
        getPreferensiPelanggan(idPelanggan);

        //setOnClickListener
        imageViewBack.setOnClickListener(this);
        imageViewTambahAlternatif.setOnClickListener(this);
        buttonRekomendasi.setOnClickListener(this);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == 1 && resultCode == RESULT_OK) {
            NewMobil alternatif = (NewMobil) data.getSerializableExtra("alt");
            mobils.add(alternatif);
            daftarAlternatifAppAdapter = new DaftarAlternatifAppAdapter(DaftarAlternatiflAppActivity.this, mobils);
            recyclerViewAlternatif.setAdapter(daftarAlternatifAppAdapter);
        }
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
            case R.id.imageView_tambahAlternatif: {
                try {
                    Intent intent = new Intent(DaftarAlternatiflAppActivity.this, DaftarPilihanAlternatifAppActivity.class);
                    intent.putExtra("alternatif", mobils);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
                break;
            }
            case R.id.btnRekomendasi: {
                try {
                    if (mobils.size() < 2) {
                        Toast.makeText(getApplicationContext(), "Pilih Alternatif Minimal 2 Mobil", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    Intent intent = new Intent(DaftarAlternatiflAppActivity.this, DaftarRekomendasiMobilAppActivity.class);
                    intent.putExtra("alternatif", mobils);
                    intent.putExtra("preferensi", prefKriterias);
                    startActivityForResult(intent, 1);
                } catch (Exception e) {
                    Log.e("ErrorMsg", e.getMessage());
                    Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
                }
            }
        }
    }

    public void getPreferensiPelanggan(String getIdPelanggan) {
        try {
            FirebaseFirestore firebaseFirestore = FirebaseFirestore.getInstance();
            CollectionReference preferensiCollections = firebaseFirestore.collection("preferensi");
            DocumentReference preferensiDb = preferensiCollections.document(getIdPelanggan);
            prefKriterias = new ArrayList<>();
            preferensiDb.get().addOnSuccessListener(documentSnapshot -> {
                if (!documentSnapshot.exists()) {
                    Toast.makeText(getApplicationContext(), "Tidak ada data preferensi", Toast.LENGTH_SHORT).show();
                    return;
                }
                Preferensi data = documentSnapshot.toObject(Preferensi.class);
                prefKriterias = data.getPreferensi_kriterias();
            });
        } catch (Exception e) {
            Log.e("ErrorMsg", e.getMessage());
            Toast.makeText(getApplicationContext(), e.getMessage(), Toast.LENGTH_SHORT).show();
        }
    }
}